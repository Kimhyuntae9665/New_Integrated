import  {useEffect} from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { BOARD_LIST } from 'src/mock';
import { useUserStore } from 'src/stores';

export default function BoardUpdateView() {

  const {user}=  useUserStore();

  const {boardNumber} = useParams();

  const navigator = useNavigate();

  useEffect(()=>{
    // ? 정상적이지 않은 경로로 접근을 시도 했을 때에 
    // ? main화면으로 돌려보냄 
    if(!boardNumber){ //^ 큰 흐름에서 벗어나는 것을 if문 안으로 넣어야 한다 
      navigator('/'); //메인 화면으로 간다 
      return;
    }
     //pathVariable로 전달받은 boardNumber에 해당하는 board 데이터를 검색해 옴      // ParseInt()는 string을 number로 반환 
    // 검색한 값과 같은 값을 가지는 게시물을 board에 넣는다  
    const board  = BOARD_LIST.find((item)=>item.boardNumber=== parseInt(boardNumber) );
    
    // board가 존재하는지를 확인   ==> 검색 결과가 존재하지 않으면 
    // ? main 화면으로 돌려보낸다 
    if(!board){
        navigator('/');
        return;
    }

    // ? 현재 로그인되어 있는지 검증 
    if(!user){
      navigator('/auth');
      return;
    }


    // ? 검색된 board의 작성자가 로그인한 user와 일치하는지 검증 
    if(board.writerNickName==='박호두'){
        navigator('/');
        return;
    }


  },[])


  return (
    <div>BoadUpdate</div>
  )
}
