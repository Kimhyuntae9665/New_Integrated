import * as React from 'react';
import { styled, alpha } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import InputBase from '@mui/material/InputBase';
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import { red } from '@mui/material/colors';
import { Button,FormControl,OutlinedInput,InputAdornment } from '@mui/material';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import {useState} from 'react';




export default function NavigationBar() {

  const [content,setContent] = useState<string>('');

  const navigator = useNavigate();
  const path = useLocation();


  const onSearchHandler = () =>{
    // trim() 사용 해서 뛰어쓰기도 모두 제거
    if(!content.trim()) {
      alert("검색어를 입력하세요");
      return;
    }

    navigator(`/board/search/${content}`);
  }

  

    return (
    <Box sx={{ flexGrow: 1}}>
      <AppBar variant = 'outlined' position="static" sx={{p:'0px 120px',backgroundColor:'#ffffff'}}>
        <Toolbar>
          

            <Typography
              variant="h6"
              noWrap   //^noWrap: 이 속성은 텍스트가 컨테이너의 너비를 초과하는 경우 텍스트가 여러 줄로 줄 바꿈되지 않도록 합니다.
              component="div"
              sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' ,color:'#000000'} }}//^라이브러리를 사용하여 컴포넌트의 스타일을 설정하는 스타일링 속성입니다
              onClick={()=>navigator('/')}   // 클릭하면 localhost:3000으로(기본으로) 돌아오게 만든다 
              // navigator()함수 사용 해서 해당 url로 이동 
            >
              Hoons Board

            </Typography>

          


          
          <Box sx={{display:'flex'}}>
            <FormControl  variant='outlined' sx={{mr:'10px'}}>
              <OutlinedInput
                size ='small'
                type='text'
                placeholder='검색어를 입력해 주세요'
                endAdornment={
                  <InputAdornment position ='end'>
                    <IconButton edge='end' onClick={onSearchHandler}>
                      <SearchIcon/>
                    </IconButton>
                  </InputAdornment>
                }
                onChange={(event)=>setContent(event.target.value)}
              />
            </FormControl>
            
            {/* path.name이 /auth이면 로그인 버튼 나오게  논리 연산자 사용해 준다 */}
            {path.pathname!=='/auth'&&(<Button variant='contained' sx={{backgroundColor:'#000000'}} onClick={()=>navigator('/auth')}>로그인</Button>)}
            {/*버튼의 variant의 contained를 하면 색상이 가득 찬 버튼 만들기  */}
            {/* 버튼 클릭했을 때 url 창에 주소 추가해 이동 할려면 (/auth) ==> (Linkto +) useNavigate() 사용  */}
            

          </Box>

        </Toolbar>
      </AppBar>
    </Box>
  );
}
