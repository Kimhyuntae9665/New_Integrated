
import { Card, Grid, Typography } from '@mui/material'
import { Box } from '@mui/system'
import axios, { AxiosResponse } from 'axios';
import React, {useState,useEffect}  from 'react'
import ResponseDto from 'src/apis/response';
import { GetTop3ListResponseDto } from 'src/apis/response/board';
import PreviewCard from 'src/components/PreviewCard'
import { GET_TOP3_LIST_URL } from 'src/constants/api';
import { IpreviewItem } from 'src/interfaces';
import {    TOP3   } from 'src/mock'

export default function MainHead() {
//        top3List도 자동으로 배열이 된다 
    const[top3List,setTop3List] =useState<GetTop3ListResponseDto[]>([]);

    const getTop3List = () =>{
        axios.get(GET_TOP3_LIST_URL)
            .then((response)=>{getTop3ListResponseHandler(response)})
            .catch((error)=>{getTop3ListErrorHandler(error)})
    }

    const getTop3ListResponseHandler = (response:AxiosResponse<any, any>)=>{
        const {result,message,data} = response.data as ResponseDto<GetTop3ListResponseDto[]>;
        if(!result || data === null) return;
        setTop3List(data);
    }

    const getTop3ListErrorHandler = (error:any) =>{
        console.log(error.message);
    }


    // ^ useEffect 쓰면 화면 첫 로드와 동시에 동작한다 
    // ^ 
    useEffect (()=>{
        getTop3List();
    },[]) //^ [] 안에는 이 변수가 바꼇을때 setTop3List가 실행된다
  
    return (
        <Box sx={{pb:'40px',pl:'120px',pr:'120px'}}>
            {/* 점보트론 텍스트 */}
            {/* 중앙 정렬 textAlign:'center' */}
            <Box sx={{pt:'80px',pb:'32px', textAlign:'center'}}>
                <Typography sx={{fontSize:'40px',fontWeight:400}}>Hoons Board에서</Typography>
                <Typography sx={{fontSize:'40px',fontWeight:400}}>다양한 이야기를 나눠보세요.</Typography>
            </Box>
            {/* 주간 top 3 게시물 */}
            {/* Grid는 12등분 기본으로 하고 spaceing(선 사이의 공간 )을 조절함으로써 그림이 차지하는 크기를 조절한다  */}
            <Box>
                <Typography sx={{fontSize:'24px',fontWeight:400,p:'24px',textAlign:'center'}}>주간 TOP 3 게시물</Typography>

                <Grid container spacing={3}>  {/*Grid container 이므로 큰 박스 역할  */}
                    {/* React에서는 매개변수 === Props */}
                    {top3List.map((item)=>(<Grid item sm={12} md={4}>
                        {/* 매개변수 즉, Props와 함께 이용해서 함수 부르기  */}
                        <PreviewCard previewItem={item}/>
                    </Grid>))}
                    
                    
                </Grid>
            </Box>
        </Box>
    
  )
}
