
import React,{useEffect,useState} from 'react'
import {Box,Grid,Typography,Pagination,Stack} from '@mui/material'
import BoardListItem from 'src/components/BoardListItem'
import PopularCard from 'src/components/PopularCard'
import { IpreviewItem } from 'src/interfaces';
import { BOARD_LIST } from 'src/mock';
import { getPageCount } from 'src/utils';
import { usePagingHook } from 'src/hooks';

export default function MainContents() {

   const {pageNumber,viewList,boardList,setboardList,COUNT,onPageHandler}=usePagingHook();

   useEffect(()=>{
    setboardList(BOARD_LIST);
   },[])

  
    return (
    <Box sx={{p:'40px 120px',backgroundColor:'rgba(0,0,0,0.05)'}}>
        <Box>
            <Typography sx={{fontSize:'24px',fontWeight:500 }}>최신 게시물</Typography>
        </Box>
        <Box sx={{pt:'20px',pb:'80px'}}>
            <Grid container spacing={3}>
                {/* 세로 큰 선 grid의 차지 비율이 8:4이므로  */}
                <Grid item sm={12} md={8}>
                    {/*30개의  칸 과 칸이 쌓이는 느낌  */}
                    <Stack spacing ={2}>
                        {viewList.map((borardItem)  =>  (<BoardListItem item={borardItem}/>))}
                    </Stack>
                </Grid>
                <Grid item sm={12} md={4}>
                    <PopularCard title="인기 검색어"/>
                </Grid>
            </Grid>
        </Box>
        {/* content를 중앙 정렬 하는 방법  */}
        <Box sx={{display:'flex',justifyContent:'center'}}>
            <Pagination page={pageNumber} count={getPageCount(boardList,COUNT)} onChange={(event,value)=>onPageHandler(value)}/>
        </Box>
    </Box>
  )
}
