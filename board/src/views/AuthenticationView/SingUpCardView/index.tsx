import {Box, Button, Typography,TextField,FormControl,InputLabel,Input,InputAdornment,IconButton} from '@mui/material'
import { Dispatch, SetStateAction, useState } from 'react'
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';
import { useSignUpStore } from 'src/stores';
import FollowTheSignsIcon from '@mui/icons-material/FollowTheSigns';





function FirstPage(){
    const{setEmail,setpassword,setpasswordCheck} = useSignUpStore();
    const{email,password,passwordCheck} = useSignUpStore();

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
                        value={password}
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
                        value={passwordCheck}
                        onChange={(event)=>setpasswordCheck(event.target.value)}
                    />
                </FormControl>
        </Box>
    )
}

function SecondPage(){
    const {nickName,telNumber,address,addressDetail} = useSignUpStore();
    const {setnickName,settelNumber,setaddress,setaddressDetail} = useSignUpStore();

    return(
      <Box>
        <TextField sx={{mt:'40px'}} fullWidth label='닉네임*' variant='standard' value={nickName} onChange={(event)=>setnickName(event.target.value)}/>
                                                                                                    {/* 원래 nickName의 값이 바뀌면(event) setnickName 함수가 바뀌는 데로 nickName변수의 값을 바꿔준다  */}
        <TextField sx={{mt:'40px'}} fullWidth label='휴대폰 번호*' variant='standard' value={telNumber} onChange={(event)=>settelNumber(event.target.value)}/>

        <FormControl fullWidth variant='standard' sx={{mt:'40px'}}>
            <InputLabel>주소*</InputLabel>
            <Input type="text" endAdornment={
                <InputAdornment position ="end">
                    <IconButton>
                        <FollowTheSignsIcon/>
                    </IconButton>
                </InputAdornment>

            }
            value={address}
            
            onChange={(event)=>setaddress(event.target.value)}
            
            />
        </FormControl>

        <TextField sx={{mt:'40px'}} fullWidth label='상세주소*' variant='standard' value={addressDetail} onChange={(event)=>setaddressDetail(event.target.value)}/>
            
      </Box>  
    );
}

interface Props{
    setLoginView:Dispatch<SetStateAction<boolean>>
}



export default function SignUpCardView({setLoginView}:Props) {

    const [page,setPage] = useState<number>(1);

    const {email,password,passwordCheck} = useSignUpStore();

    const{nickName,telNumber,address,addressDetail} = useSignUpStore();

    const onNextButtonHandler = ()=>{  //! {} 이므로 조건문 사용 가능 
        //todo : 이메일 /비밀번호/비밀번호 확인 검증 
        // ? 해당 문자열 변수가 빈값인지 확인 
        // ? 1. 해당 변수 == '';
        // ? 2. 해당 변수의 길이 == 0 ;
        if(!email || !password || !passwordCheck){
            alert('모든 값을 입력하세요');
            return;
        }
        if(password!==passwordCheck){
            alert('비밀번호가 서로 다릅니다 ')
            return; 
        }


        // todo : 검증이 실패하면 return 
        // todo : 검증이 성공하면 page 변경 
        setPage(2);
    }

    const onSignUpHandler = () =>{
        if( !email || !password || !passwordCheck){
            alert('모든 값을 입력하세요');
            setPage(1);
            return;
        }
        if(!nickName || !telNumber || !address || !addressDetail){
            alert('모든 값을 입력하세요');
            setPage(2);
            return;
        }
        
        if(password!==passwordCheck){
            alert('비밀번호를 확인해 주세요.');
            setPage(1);
            return;

        }

        alert('회원 가입 완료!');

        const data ={
            email,password,nickName,address,telNumber,addressDetail
        }

        console.log(data);
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
                {page ===2 && (<Button fullWidth variant='contained' size = 'large' sx={{mb:'20px'}} onClick={onSignUpHandler}>회원가입</Button>)}
                
                <Typography variant='h5' fontWeight={'900'}>이미 계정이 있으신가요?
                    <Typography component={'span'} fontWeight={900} onClick={()=>setLoginView(true)}> 로그인</Typography>
                </Typography>

            </Box>
        </Box>
    )
}