import React from 'react'
import {Box,Avatar} from '@mui/material';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';
import { useUserStore } from 'src/stores';

export default function MyPageHead() {

    const {user} = useUserStore();

  return (
    <Box sx={{p:'40px 120px',display:'flex'}}>
        <Box>
            <IconButton>
                <Avatar sx={{height:'120px',width:'120px'}} alt ={user?.nickname} src={user?.profile}/>
            </IconButton>
        </Box>
        <Box sx={{ml:'25px',display:'flex',flexDirection:'column',justifyContent:'center'}}>
            <Box sx={{display:'flex',alignItems:'center'}}>
                <Typography sx={{fontSize:'24px',fontWeight:500,color:'rgba(0,0,0,0.7)'}}>{user?.nickname}</Typography>
                <IconButton sx={{mr:'10px'}}>
                    <EditOutlinedIcon/>
                </IconButton>
            </Box>
            <Typography sx={{mt:'10px' ,fontSize:'16px',fontWeight:500,color:'rgba(0,0,0,0.4)'}}>{user?.email}</Typography>
        </Box>
    </Box>
  )
}
