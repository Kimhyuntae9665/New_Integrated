import {Box, Button, Typography,TextField,FormControl,InputLabel,Input,InputAdornment,IconButton} from '@mui/material'
import { Dispatch, SetStateAction, useState } from 'react'
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';
import { useSignUpStore } from 'src/stores';


function FirstPage(){
    const{setEmail,setpassword,setpasswordCheck} = useSignUpStore();

    const [showPassword,setshowPassword] = useState<boolean>(false);
    const [showPasswordCheck,setshowPasswordCheck] = useState<boolean>(false);
    return(
        <Box>
            <TextField sx={{mt:'40px'}} fullWidth label='이메일 주소*' variant='standard' onChange={(event)=> setEmail(event.target.value)}/>
            <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
                    <InputLabel>비밀번호*</InputLabel>
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

                <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
                    <InputLabel>비밀번호 확인*</InputLabel>
                    <Input
                        type={showPasswordCheck ? 'text' : 'password'}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={() => setshowPasswordCheck(!showPasswordCheck)}
                                >
                                    {showPasswordCheck ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                        onChange={(event)=>setpasswordCheck(event.target.value)}
                    />
                </FormControl>
        </Box>
    )
}

function SecondPage(){
    return(
        <Box>두번째 페이지</Box>
    )
}

interface Props{
    setLoginView:Dispatch<SetStateAction<boolean>>
}



export default function SignUpCardView({setLoginView}:Props) {

    const [page,setPage] = useState<number>(1);

    const {} = useSignUpStore();

    const onNextButtonHandler = ()=>{
        //todo : 이메일 /비밀번호/비밀번호 확인 검증 
        // todo : 검증이 실패하면 return 
        // todo : 검증이 성공하면 page 변경 
        setPage(2);
    }


     return(
        <Box display='flex' sx={{height:'100%',flexDirection:'column',justifyContent:'space-between'}} >
            <Box>
                <Box display='flex' sx={{justifyContent:'space-between'}}>
                    {/* 가장 먼저 가로로든 세로로든 가로 세로 길이를 먼저 설정해 놔야한다 그래야 justeiftcontents가 먹힌다  */}
                    {/* 부모에다가 displat = flex 하면 한 block에  같이 나열하고 justifyContent는 space-between 해서 양 옆으로 붙여 버린다  */}
                    <Typography variant='h5' fontWeight={'900'}>회원가입</Typography>
                    <Typography variant='h5' fontWeight={'900'}>{page}/2</Typography>
                </Box>
                {page===1 ? (<FirstPage />):(<SecondPage />)}
            </Box>       
            <Box>
                {page ===1 && (<Button fullWidth variant = 'contained' size='large' sx={{mb:'20px'}} onClick={onNextButtonHandler}>다음 단계</Button>)}
                {page ===2 && (<Button fullWidth variant='contained' size = 'large' sx={{mb:'20px'}} onClick={()=>{setPage(1)}}>회원가입</Button>)}
                
                <Typography variant='h5' fontWeight={'900'}>이미 계정이 있으신가요?
                    <Typography component={'span'} fontWeight={900} onClick={()=>setLoginView(true)}> 로그인</Typography>
                </Typography>

            </Box>
        </Box>
    )
}