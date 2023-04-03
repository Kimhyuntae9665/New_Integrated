import React, { Dispatch, useEffect } from 'react'
import { useCookies } from 'react-cookie';
import { Box, TextField, FormControl, InputLabel, Input, InputAdornment, IconButton, Button } from '@mui/material'
import { Typography } from '@mui/material'
import { useState } from 'react'
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';
import axios, { AxiosResponse } from 'axios';

import {USER} from 'src/mock';
import ResponseDto from 'src/apis/response';

// ! export default 아닌 그냥 export는 {}꼭 해줘야한다 
import { useSignUpStore, useUserStore } from 'src/stores';
import { useNavigate } from 'react-router-dom';
import { SIGN_IN_URL } from 'src/constants/api';
import { SignInDto } from 'src/apis/request/auth';
import { SignInResponseDto } from 'src/apis/response/auth';

import { getExpires } from 'src/utils';

interface Props {
    setLoginView: Dispatch<React.SetStateAction<boolean>>;
}


export default function LoginCardView({ setLoginView }: Props) {
   
    const [cookies, setCookie,removeCookie] = useCookies(["accessToken"]);
    
    const[email,setEmail]= useState<string>('');
    const[password,setpassword] = useState<string>('');
    const [showPassword, setshowPassword] = useState<boolean>(false);
    const {setUser} = useUserStore(); 
    //          HOOK          //
    const navigator = useNavigate();

    // useEffect(() => {
    //     window.addEventListener("beforeunload", deleteCookie);

    //     return () => {
    //         window.removeEventListener("beforeunload", deleteCookie);
    //     };
    //     }, []);

    // const deleteCookie = ()=>{
    //     removeCookie("accessToken");
    // }
    //          Event Handler          //
    const onLoginHandler = () => {
        // ? email 입력했는지 검증 
        // ? password 입력했는지 검증

        if(!email.trim() || !password.trim()) {
            alert('모든 값을 입력해 주세요!');
            return;
        }

        const data: SignInDto = {email,password};

        // ^ URL 로 요청을 보낸다 
        axios.post(SIGN_IN_URL,data)
        .then((response)=>signInResponseHandler(response))
        .catch((error)=>signInErrorHandler(error));

        


        // ? 로그인 처리 
        // ? 쿠키에 로그인 데이터 (Token) 보관 
        // ? 스토어에 유저 데이터 보관  

       

        

    }
    //          Response Handler          //
    const signInResponseHandler = (response : AxiosResponse<any, any>) =>{

        const {result,message,data} = response.data as ResponseDto<SignInResponseDto>;
        if(!result || !data){
            alert("로그인 정보가 잘못되었습니다");
            return;
        }
        
        
        const { token, expiredTime, ...user } = data;
        //? 로그인 처리
        //? 쿠키에 로그인 데이터 (Token) 보관
        const expires = getExpires(expiredTime);
        setCookie('accessToken', token, { expires });
        //? 스토어에 유저 데이터 보관
        setUser(user);
        navigator('/');

        }
    //          Error Handler          //
    const signInErrorHandler=(error:any)=>{

        console.log(error.message);
    }

    return (
        <Box display='flex' sx={{ height: '100%', flexDirection: 'column', justifyContent: 'space-between' }}>

            <Box>
                <Typography variant='h4' fontWeight='900'>로그인</Typography>
                <TextField sx={{ mt: '40px' }} fullWidth label="E-mail" variant="standard" onChange={(event)=>setEmail(event.target.value)}/>

                <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
                    <InputLabel>비밀번호</InputLabel>
                    <Input
                        type={showPassword ? 'text' : 'password'}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={() => setshowPassword(!showPassword)}
                                >
                                    {showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                        onChange={(event)=>setpassword(event.target.value)}
                    />
                </FormControl>

            </Box>

            <Box>
                <Button sx={{ mb: '20px' }} fullWidth variant="contained" size='large' onClick={onLoginHandler}>로그인</Button>
                <Typography textAlign={'center'}>신규 사용자 이신가요?
                                                                        {/* LoginCardView함수에서 매개변수로 받았던 setLoginView를 클릭하면 false로 바뀐다 ==> 
                                                                        원래 가장 먼저 불렸던 AuthenticView 연결되어있던 AuthenticView에서 LoginView가 false로 바뀌면서 LoginCardView에서 SignInCardView 함수로 바뀐다 
                                                                        함수가 바뀌므로 화면이 전환된다 */}
                    <Typography component={'span'} fontWeight={900} onClick={() => setLoginView(false)}> 회원가입</Typography>
                </Typography>


            </Box>
        </Box>
    )
}
