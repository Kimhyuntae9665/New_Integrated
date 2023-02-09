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

const Search = styled('div')(({ theme }) => ({
  position: 'relative',
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  '&:hover': {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginLeft: 0,
  width: '100%',
  [theme.breakpoints.up('sm')]: {
    marginLeft: theme.spacing(1),
    width: 'auto',
  },
}));

const SearchIconWrapper = styled('div')(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: 'inherit',
  '& .MuiInputBase-input': {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}));

export default function NavigationBar() {
  return (
    <Box sx={{ flexGrow: 1,pr:"120px",pl:"120px" }}>
      <AppBar position="static">
        <Toolbar>
          
          
          <Typography
            variant="h6"
            noWrap   //^noWrap: 이 속성은 텍스트가 컨테이너의 너비를 초과하는 경우 텍스트가 여러 줄로 줄 바꿈되지 않도록 합니다.
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}//^라이브러리를 사용하여 컴포넌트의 스타일을 설정하는 스타일링 속성입니다
          >
            Hoons Board
            
          </Typography>
            <IconButton color ={"default"}>
              {/* IconButton으로 인해 아이콘 모양이 버튼 클릭도 가능해 진다 */}
              <SearchIcon />
              {/* SearchIcon이 클릭이 가능하게 된다  */}
            </IconButton>
            
        </Toolbar>
      </AppBar>
    </Box>
  );
}
