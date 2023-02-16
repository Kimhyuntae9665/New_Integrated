
import React,{useEffect,useState} from 'react'
import {Box,Grid,Typography,Pagination,Stack} from '@mui/material'
import BoardListItem from 'src/components/BoardListItem'
import PopularCard from 'src/components/PopularCard'
import { IpreviewItem } from 'src/interfaces';
import { BOARD_LIST } from 'src/mock';

export default function MainContents() {

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
        setboardList(BOARD_LIST);
        // ^  set 메서드는 useEffect가 종료된 후에 리렌더링 되고 boardList가 변경된다 
        // ^ useEffect 함수안에서 변경 되려고 하면 board_List 대신 이미 바껴있는 BOARD_LIST를 사용 한다 

        
    },[]);

    useEffect(()=>{
        onPageHandler(pageNumber);
    },[boardList]) // 조건 : 시작하자마자 발동되고 그리고 boardList가 바뀌면 onPageHandler(pageNumber) 가 발동 

  
    return (
    <Box sx={{p:'40px 120px',backgroundColor:'rgba(0,0,0,0.05)'}}>
        <Box>
            <Typography sx={{fontSize:'24px',fontWeight:500 }}>최신 게시물</Typography>
        </Box>
        <Box sx={{pt:'20px',pb:'80px'}}>
            <Grid container spacing={3}>
                {/* 세로 큰 선 grid의 차지 비율이 8:4이므로  */}
                <Grid item sm={12} md={8}>
                    {/*30개의  칸 과 칸이 쌓이는 느낌  */}
                    <Stack spacing ={2}>
                        {viewList.map((borardItem)  =>  (<BoardListItem item={borardItem}/>))}
                    </Stack>
                </Grid>
                <Grid item sm={12} md={4}>
                    <PopularCard/>
                </Grid>
            </Grid>
        </Box>
        {/* content를 중앙 정렬 하는 방법  */}
        <Box sx={{display:'flex',justifyContent:'center'}}>
            <Pagination page={pageNumber} count={Math.floor(boardList.length / COUNT)+1} onChange={(event,value)=>onPageHandler(value)}/>
        </Box>
    </Box>
  )
}
