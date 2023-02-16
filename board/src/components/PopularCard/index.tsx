import React, { useState ,useEffect} from 'react'
import {Card,Typography,Box,Chip} from '@mui/material';
import { POPULAR_LIST } from 'src/mock';
import { useNavigate } from 'react-router-dom';

interface Props{
    title : string;

}

export default function PopularCard({title}:Props) {

    const [popularList,setpopulatList]=useState<string[]>([]);
    const navigator = useNavigate();

    useEffect(()=>{
        setpopulatList(POPULAR_LIST);
    },[]);

  return (
    <Card variant='outlined' sx={{p:'24px 12px 26px 24px'}}>
        <Typography sx={{fontSize:'24px',fontWeight:500}}>{title}</Typography>
        <Box sx={{mt:'24px'}}>
            {popularList.map((popular)=>(
                    <Chip sx={{mr:'12px',mb:'12px',fontSize:'14px',fontWeight:500}} label={popular} variant="outlined" onClick={()=>navigator(`/board/search/${'popular'}`)}/>
            ))}
            
        </Box>
    </Card>
  )
}
