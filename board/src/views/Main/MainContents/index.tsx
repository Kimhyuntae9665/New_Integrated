
import React,{useEffect,useState} from 'react'
import {Box,Grid,Typography,Pagination,Stack} from '@mui/material'
import BoardListItem from 'src/components/BoardListItem'
import PopularCard from 'src/components/PopularCard'
import { IpreviewItem } from 'src/interfaces';
import { BOARD_LIST } from 'src/mock';

export default function MainContents() {

    const COUNT = 5;

    const [boardList,setboardList]=useState<IpreviewItem[]>([]);
    const [viewList,setViewList] = useState<IpreviewItem[]>([]);
    const [pageNumber,setpageNumber] =useState<number>(1);

    // ? 한 페이지에 5개의 게시물을 보여주고자 할 떄 
    // ? 배열의 시작 인덱스 5 * pageNumber -5 -> 5*(pageNUmber-1)
    // ? 배열의 마지막 인덱스는 5 * pageNumber -1


    const onPageHandler = (page:number) =>{
        setpageNumber(page);

        const startIndex = COUNT * (pageNumber -1) ;
        const endIndex = COUNT * pageNumber - 1;

        const tmpList :IpreviewItem[]=[];


        for(let index = startIndex; index<=endIndex; index++){
            
                tmpList.push(boardList[index]);
                
        }
        setViewList(tmpList);
    }

    


    
    useEffect (()=>{
        setboardList(BOARD_LIST);
        // ^  set 메서드는 useEffect가 종료된 후에 리렌더링 되고 boardList가 변경된다 
        // ^ useEffect 함수안에서 변경 되려고 하면 board_List 대신 이미 바껴있는 BOARD_LIST를 사용 한다 

        const startIndex = COUNT * (pageNumber -1) ;
        const endIndex = COUNT * pageNumber -1;
        const tmpList :IpreviewItem[]=[];

       

            //  ^ page개수 관련 
        for(let index = startIndex; index<=endIndex; index++){
                tmpList.push(BOARD_LIST[index]);
                
        }
        setViewList(tmpList);
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
                    <PopularCard/>
                </Grid>
            </Grid>
        </Box>
        {/* content를 중앙 정렬 하는 방법  */}
        <Box sx={{display:'flex',justifyContent:'center'}}>
            <Pagination page={pageNumber} count={10} onChange={(event,value)=>onPageHandler(value)}/>
        </Box>
    </Box>
  )
}
