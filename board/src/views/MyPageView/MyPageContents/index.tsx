import {useState,useEffect} from 'react'
import {Box,Typography,Stack} from '@mui/material'
import { fontSize } from '@mui/system'
import Pagination from '@mui/material/Pagination'
import Grid from '@mui/material/Grid'
import Card from '@mui/material/Card'
import CreateIcon from '@mui/icons-material/Create';
import CardActionArea from '@mui/material/CardActionArea'
import { usePagingHook } from 'src/hooks'
import { BOARD_LIST } from 'src/mock'
import { useUserStore } from 'src/stores'


export default function MyPageContent() {
    // ^ 기능상으로 계속 사용 하는것을 저장해 두는 것이 Hook 함수 (PagingHook)
    // ^ interface상으로 같은 요소를 저장해 두는것을 component
    const {boardList,viewList,pageNumber,setboardList,onPageHandler,COUNT}=usePagingHook();
    const {user} =useUserStore();


    useEffect(()=>{
        const tmp=BOARD_LIST.filter((board)=>board.writerNickName===user?.nickname);
        setboardList(tmp);
    },[])

  return (
    <Box sx={{p:'40px 120px',backgroundColor:'rgba(0,0,0,0.05)' }}>
        <Box>
            <Typography sx={{pl:'110px' ,fontSize:'24px' ,fontWeight:500}}>나의 게시물 10</Typography>
        </Box>
        <Box sx={{mt:'20px' ,mb:'80px'}}>
            
                <Grid container spacing={3}>
                    <Grid item sm={12} md={8}></Grid>
                    <Grid item sm={12} md={4}>
                        <Card variant='outlined' >
                            <CardActionArea sx={{p:'25px 0px',display:'flex',justifyContent:'center'}}>
                                <CreateIcon sx={{mr:'6px' }}/>
                                <Typography sx={{fontSize:'18px',fontWeight:500}}>글쓰기</Typography>
                            </CardActionArea>   
                        </Card>
                    </Grid>
                </Grid>

            
        </Box>
        <Box sx={{display:'flex',justifyContent:'center'}}>
            <Pagination />
        </Box>


    </Box>
  )
}
