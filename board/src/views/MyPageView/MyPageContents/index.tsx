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
import BoardListItem from 'src/components/BoardListItem'
import { getPageCount } from 'src/utils'
import { useNavigate } from 'react-router-dom'


export default function MyPageContent() {
    // ^ 기능상으로 계속 사용 하는것을 저장해 두는 것이 Hook 함수 (PagingHook)
    // ^ interface상으로 같은 요소를 저장해 두는것을 component
    const {boardList,viewList,pageNumber,setboardList,onPageHandler,COUNT}=usePagingHook();
    // ? 로그인 한 상태일 때 유저 정보를 가져올 수 있도록 
    // ? 스토어에서 user 상태를 가져옴 
    const {user} =useUserStore();
    const navigator = useNavigate();

    useEffect(()=>{
        // ^ 로그인이 되어있지 않으면 로그인 페이지로 이동 
        // if(!user){
        //     alert('로그인이 필요한 작업 입니다');
        //     navigator('/auth'); // ? 로그인 창으로 보내버린다 
        //     return;
            
        // }
        // ? BOARD_LIST (전체 게시물 리스트 )에서 작성자의 nickname이 
        // ? 로그인한 회원의 nickname과 일치하는 게시물만 필터링해서 
        // ? 기준이 되는 새로운 리스트를 생성 
        const tmp=BOARD_LIST.filter((board)=>board.writerNickName===user?.nickname);
        // ? 기준이 되는 새로운 리스트를 boardList 상태에 저장 
        setboardList(tmp);
    },[])

  return (
    <Box sx={{p:'40px 120px',backgroundColor:'rgba(0,0,0,0.05)' }}>
        <Box>
            <Typography sx={{pl:'110px' ,fontSize:'24px' ,fontWeight:500}}>나의 게시물 {boardList.length}</Typography>
        </Box>
        <Box sx={{mt:'20px' ,mb:'80px'}}>
            
                <Grid container spacing={3}>
                    <Grid item sm={12} md={8}>
                        <Stack spacing={2}> 
                            {viewList.map((boardItem)=>(<BoardListItem item={boardItem}/>))}
                        </Stack>
                    </Grid>
                    <Grid item sm={12} md={4}>
                        <Card variant='outlined' >
                            <CardActionArea sx={{p:'25px 0px',display:'flex',justifyContent:'center'}} onClick={()=>navigator('/board/write')}>
                                <CreateIcon sx={{mr:'6px' }}/>
                                <Typography sx={{fontSize:'18px',fontWeight:500}}>글쓰기</Typography>
                            </CardActionArea>   
                        </Card>
                    </Grid>
                </Grid>

            
        </Box>
        <Box sx={{display:'flex',justifyContent:'center'}}>
            <Pagination page={pageNumber} count={getPageCount(boardList,COUNT)} onChange={(event,value)=>onPageHandler(value)}/>
        </Box>


    </Box>
  )
}
