import React, { useState,useEffect } from 'react'
import {Box,Divider,IconButton,Input,Fab} from '@mui/material'
import ImageOutlinedIcon from '@mui/icons-material/ImageOutlined';
import CreateIcon from '@mui/icons-material/Create';
import { useNavigate } from 'react-router-dom';
import axios, { AxiosResponse } from 'axios';
import { PostBoardResponseto } from 'src/apis/response/board';
import ResponseDto from 'src/apis/response';
import { useCookies } from 'react-cookie';
import { PostBoardDto } from 'src/apis/request/board';
import { authorizationHeader, POST_BOARD_URL } from 'src/constants/api';


export default function BoardWriteView() {

  const [cookies] = useCookies();
  const[boardTitle,setBoardTitle] = useState<string>(''); 
  const [boardContent,setBoardContent]= useState<string>(''); 
  const [boardImgUrl,setBoardImgUrl] = useState<string>('');

  const navigator = useNavigate();

  const accessToken = cookies.accessToken;

  const postBoard = () =>{
    const data:PostBoardDto = { boardTitle,boardContent,boardImgUrl};

    axios.post(POST_BOARD_URL,data,authorizationHeader(accessToken))
        .then((response)=>postBoardResponseHandler(response))
        .catch((error)=>postBoardErrorHandler(error));
  }

  const postBoardResponseHandler = (response: AxiosResponse<any,any>) =>{
    const {result,message,data} = response.data as ResponseDto<PostBoardResponseto>;
    if(!result || !data){
      alert(message);
      return;
    }

    navigator('/myPage');



  }


  const postBoardErrorHandler= (error: any) =>{
    console.log(error.message);
  }

  const onWriteHandler = () =>{ //Fab 눌러서 작성완료 할려 할때 
    //? 제목 및 내용 검증 
    if(!boardTitle.trim() || !boardContent.trim()){
      alert('모든 내용을 입력해 주세요');
      return;
    }

    postBoard();
    
  }

  useEffect(()=>{

    if(!accessToken){
      // ^ 토큰이 없으면 
      alert('로그인이 필요한 작업입니다.');
      // ^ 인증  창으로 보낸다 
      navigator('/auth');
    }


  },[])

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
