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
import { Button } from '@mui/material';
import { Link, useLocation, useNavigate } from 'react-router-dom';



export default function NavigationBar() {

  const navigator = useNavigate();
  const path = useLocation();

  console.log(path.pathname);

    return (
    <Box sx={{ flexGrow: 1, pr: "120px", pl: "120px" }}>
      <AppBar position="static">
        <Toolbar>
          

            <Typography
              variant="h6"
              noWrap   //^noWrap: 이 속성은 텍스트가 컨테이너의 너비를 초과하는 경우 텍스트가 여러 줄로 줄 바꿈되지 않도록 합니다.
              component="div"
              sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}//^라이브러리를 사용하여 컴포넌트의 스타일을 설정하는 스타일링 속성입니다
              onClick={()=>navigator('/')}   // 클릭하면 localhost:3000으로(기본으로) 돌아오게 만든다 
              // navigator()함수 사용 해서 해당 url로 이동 
            >
              Hoons Board

            </Typography>

          


          
          <Box>
            <IconButton color={"default"}>
              {/* IconButton으로 인해 아이콘 모양이 버튼 클릭도 가능해 진다 */}
              <SearchIcon />
              {/* SearchIcon이 클릭이 가능하게 된다  */}
            </IconButton>
            {/* path.name이 /auth이면 로그인 버튼 나오게  */}
            {path.pathname!=='/auth'&&(<Button variant='contained' color='secondary' onClick={()=>navigator('/auth')}>로그인</Button>)}
            {/*버튼의 variant의 contained를 하면 색상이 가득 찬 버튼 만들기  */}
            {/* 버튼 클릭했을 때 url 창에 주소 추가해 이동 할려면 (/auth) ==> (Linkto +) useNavigate() 사용  */}
            

          </Box>

        </Toolbar>
      </AppBar>
    </Box>
  );
}
