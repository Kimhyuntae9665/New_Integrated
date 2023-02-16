import React from 'react'
import {Card,Typography,Box,Chip} from '@mui/material';

export default function PopularCard() {
  return (
    <Card variant='outlined' sx={{p:'24px'}}>
        <Typography sx={{fontSize:'24px',fontWeight:500}}>인기 검색어</Typography>
        <Box sx={{mt:'24px'}}>
            <Chip sx={{fontSize:'14px',fontWeight:500}} label="점심메뉴" variant="outlined" />
        </Box>
    </Card>
  )
}
