import { Avatar, Typography } from '@mui/material'
import { Box } from '@mui/system'
import React from 'react'




export default function BoardListItem() {
  return (
    <Box sx={{p:'24px' ,backgroundColor:'#ffffff'}}>
        <Box sx={{display:'flex'}}>
            <Box sx={{mr:'8px'}}>
                <Avatar alt="Remy Sharp" src="" />
            </Box>
            <Box>
                <Typography sx={{fontSize:'12px',fontWeight:500,color:'#000000'}}>Nickname</Typography>
                {/* rgba의 a는 투명도  */}
                <Typography sx={{mt:'2px', fontSize:'12px',fontWeight:400,color:'rgba(0,0,0,0.7)'}}>2023.02.15</Typography>
            </Box>

        </Box>
        <Box sx={{mt:'16px',mb:'16px'}}>
            <Typography sx={{fontSize:'16px',fontWeight:500,color:'#000000'}}>Title</Typography>
            <Typography sx={{mt:'5px',fontSize:'12px',fontWeight:400,color:'rgba(0,0,0,0.7)'}}>Contents</Typography>

        </Box>
        <Box>
            <Typography sx={{fontSize:'12px',fontWeight:400,color:'rgba(0,0,0,0.7)'}}>{`댓글 0 · 좋아요 0 · 조회수 0`}</Typography>

        </Box>
    </Box>
  )
}
