import { Box, Grid, Pagination, Typography } from '@mui/material'
import { Stack } from '@mui/system'
import React, { useEffect,useState } from 'react'
import { useParams } from 'react-router-dom'
import BoardListItem from 'src/components/BoardListItem'
import PopularCard from 'src/components/PopularCard'
import { usePagingHook } from 'src/hooks'
import { IpreviewItem } from 'src/interfaces'
import { BOARD_LIST } from 'src/mock'
import { getPageCount } from 'src/utils'



export default function SearchView() {
        // ^ 주소에서 파라미터를 가져온다 
    const {content} = useParams();
    const {pageNumber,viewList,boardList,COUNT,onPageHandler}=usePagingHook(content as string);

    
    // const COUNT =5;

    // const [boardList,setBoardList]=useState<IpreviewItem[]>([]);
    // const [viewList,setViewList] =useState<IpreviewItem[]>([]);
    // const[pageNumber,setPageNumber]=useState<number>(1);
    // // 주소 path의 단어를 Parnms를 가져온다 
    // // SearchView의 Path인 search/:content 의 content를 가져온다 
     

    // const onPageHandler = (page:number)=>{
    //     setPageNumber(page);

    //     const tmpList:IpreviewItem[] =[];
    //     const startIndex = COUNT    *   (page-1);
    //     const endIndex = COUNT  *   page-1;

    //     for(let index = startIndex;   index<=endIndex;  index++){
    //         if(boardList.length<index+1) break;

    //         tmpList.push(boardList[index]);
            
    //     }

    //     setViewList(tmpList);
    // }

    // useEffect(()=>{

        
       
    //     const tmp =BOARD_LIST.filter((board)=>board.boardTitle.includes(content as string))
    //     setBoardList(tmp);
    // },[])

    // useEffect(()=>{
    //     onPageHandler(pageNumber);
    // },[boardList])

  return (
    <Box sx={{p:'40px 120px' , backgroundColor:'rgba(0,0,0,0,0.05)'}}>
        <Box sx={{fontSize:'24px',fontWeight:500}}>
            <Box component={'strong'} sx={{opacity:1}}>{content}</Box>
            <Typography component='span' sx={{fontSize:'24px',fontWeight:500,opacity:0.7}}>에 대한 검색결과 입니다</Typography>
            <Box component={'strong'} sx={{opacity:1}}>{boardList.length}</Box>

        </Box>
        <Box sx={{pt:'20px',pb:'80px'}}>
            <Grid container spacing={3}>
                <Grid item sm={12} md={8}>
                    <Stack spacing={2}>
                        {/* setViewList를 찾아보면 viewList에 뭐가 들어가있는지 보인다  */}
                        {viewList.map((boardItem)=>(<BoardListItem item={boardItem}/>))}
                    </Stack>
                </Grid>
                <Grid item sm={12} md={4}>
                    <PopularCard title='연관 검색어'/>
                </Grid>
            </Grid>
        </Box>
        <Box sx={{display:'flex',justifyContent:'center'}}>
            <Pagination page={pageNumber} count={getPageCount(boardList,COUNT)} onChange={(event,value)=>onPageHandler(value)}/>
        </Box>



    </Box>
  )
}
