import  {useEffect,useState} from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { BOARD_LIST } from 'src/mock';
import { useUserStore } from 'src/stores';
import {Box,Divider,IconButton,Input,Fab} from '@mui/material'
import ImageOutlinedIcon from '@mui/icons-material/ImageOutlined';
import CreateIcon from '@mui/icons-material/Create';


export default function BoardUpdateView() {
  
  const [boardTitle,setBoardTitle] = useState<string>('');
  const [boardContent,setBoardContent] = useState<string>('');
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

    setBoardTitle(board.boardTitle);
    setBoardContent(board.boardContent);


  },[])

  // ? 일반적으로 수정페이지는 작성페이지와 거의 똑같음 
  return (
    <Box sx={{p:'0px 198px',backgroundColor:'rgba(0,0,0,0.05)'}}>
    <Box sx={{p:'100px 24px',backgroundColor:'#ffffff'}}>
      <Input fullWidth   placeholder='제목을 입력하세요' disableUnderline sx={{fontSize:'32px',fontWeight:500,border:'0px'}} onChange={(event)=>setBoardTitle(event.target.value)}/>
      <Divider sx={{m:'40px 0px'}}/>
      <Box sx={{display:'flex',alignItems:'start'}}>
                                          {/*multiline으로 Enter가능 하게  minRows={최소 라인 수 처음부터 } */}
        <Input fullWidth disableUnderline  multiline minRows={20} maxRows={50}  placeholder='본문을 작성해 주세요' sx={{fontSize:'18px',fontWeight:500,lineHeight:'150%'}} onChange={(event)=>setBoardContent(event?.target.value)}/>
        <IconButton>
          <ImageOutlinedIcon/>
        </IconButton>
      </Box>
    </Box>
    <Fab sx={{position:'fixed',bottom:'200px',right:'200px',   backgroundColor:'rgba(0,0,0,0.4)'}} onClick={onWriteHandler}>
      <CreateIcon/>
    </Fab>
  </Box>
  )
}
