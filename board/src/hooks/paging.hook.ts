import {useState,useEffect} from 'react';
import { IpreviewItem } from 'src/interfaces';
import { BOARD_LIST } from 'src/mock';



// ! use 가 붙은 함수가 custom Hook 함수이다 
// !  Parameter를 받을 떄 안 받을 때 2가지 경우가 있는경우는 Parameter 뒤에 ? 를 붙인다 
const usePagingHook = (content?:string) => {

    const COUNT = 5;

    const [boardList,setboardList]=useState<IpreviewItem[]>([]);
    const [viewList,setViewList] = useState<IpreviewItem[]>([]);
    const [pageNumber,setpageNumber] =useState<number>(1);

    // ? 한 페이지에 5개의 게시물을 보여주고자 할 떄 
    // ? 배열의 시작 인덱스 5 * pageNumber -5 -> 5*(pageNUmber-1)
    // ? 배열의 마지막 인덱스는 5 * pageNumber -1


    const onPageHandler = (page:number) =>{

        setpageNumber(page);

        // ^ 같은 맥락으로 setpageNUmber함수 아직 적용 되지 못햇어 ==> PageNumber 대신 page를 써야 한다 
        const startIndex = COUNT * (page -1) ;
        const endIndex = COUNT * page - 1;

        const tmpList :IpreviewItem[]=[];


        for(let index = startIndex; index<=endIndex; index++){
           
            // boardList data의 개수를 벗어나면 for문 바로 벗어나고 지금까지 저장했던 tmpList만 출력 
            if(boardList.length<index+1) {
                break;
            }

            tmpList.push(boardList[index]);
                
        }
        setViewList(tmpList);
    }

    


    
    useEffect (()=>{
        // # array.filter(요소=>조건)
        // ? 특정한 조건에 부합하는 요소만 모아서 새로운 배열로 만들어 반환하는 메서드 
        // # string.includes(검색할 문자열)
        // ? 해당 문자열에서 검색할 문자열이 존재한다면 true, 아니면 false 를 반환하는 메서드  
        // ? 변수 as string 쓰면  변수가 어떤 타입인지 확정적이지 않은 상황에서 무조선 string으로만 받는다 
        // content가 빈 값이면 앞에것 아니면 뒤에 것 
        // ? 참(content가 빈 값이면 )이면 BOARD_LIST의 값들을 모두 tmp에 넣고 거짓이면(content가  빈 값이 아니면)boardTitle에서 content를 포함하는 것들만을 tmp에 넣는다 
        const tmp = !content ? BOARD_LIST:BOARD_LIST.filter((board)=>board.boardTitle.includes(content as string))
        setboardList(tmp);
        // ^  set 메서드는 useEffect가 종료된 후에 리렌더링 되고 boardList가 변경된다 
        // ^ useEffect 함수안에서 변경 되려고 하면 board_List 대신 이미 바껴있는 BOARD_LIST를 사용 한다 

        // ? SELECT * FROM Board ORDER BY writeDate DESC;

        // ? SELECT * FROM Board WHERE boardTitle LIKE %contnet% ORDER BY writeDate;
        
    },[content]);  //contetnt 바뀔때 마다 tmp가 바뀌고 boardList가 계속 바뀐다  발동



    useEffect(()=>{
        onPageHandler(pageNumber);
    },[boardList]) // 조건 : 시작하자마자 발동되고 그리고 boardList가 바뀌면 onPageHandler(pageNumber) 가 발동 
        // ^ boardList가 바뀔때는 content가 바뀔때마다 boardList가 바껴서 useEffect가 발동 되고 onPageHandler가 발동된다 
    return {boardList,viewList,pageNumber,onPageHandler,COUNT};
}


export default usePagingHook;