import React from 'react'
import {Box,Typography,IconButton} from '@mui/material'
import InstagramIcon from '@mui/icons-material/Instagram';
import FacebookIcon from '@mui/icons-material/Facebook';

export default function Footer() {
  return (
    <Box sx={{p:'40px 120px 50px 120px',backgroundColor:'#373737'}}>
        <Box sx={{display:'flex',justifyContent:'space-between'}}>
            <Box>
                <Typography sx={{fontSize:'20px',fontWeight:400,color:'#ffffff'}} color='#ffffff'>Hoons Board</Typography>
            </Box>

            <Box>
                {/* span은 가로로 옆으로 붙여준다  */}
                <Typography component='span' sx={{fontSize:'12px',fontWeight:400,color:'#ffffff'}} >rlagusxo96652@naver.com</Typography>
                <IconButton>
                    <InstagramIcon sx={{color:'#ffffff'}}/>
                </IconButton>
                <IconButton >
                    {/* 그냥 color 하면 안되고 sx={{}} 안에 color를 적어놔야 한다  */}
                    <FacebookIcon sx={{color:'#ffffff'}}/>
                </IconButton>
            </Box>
        </Box>
        <Typography sx={{fontSize:'12px',fontWeight:400,color:'#ffffff'}}>Copyrigth @ 2022 rlagusxo96652@naver.com</Typography>
    </Box>
  )
}
