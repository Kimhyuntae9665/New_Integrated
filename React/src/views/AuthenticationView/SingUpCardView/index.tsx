import { Box, Button, Typography, TextField, FormControl, InputLabel, Input, InputAdornment, IconButton, FormHelperText, Checkbox } from '@mui/material'
import { ChangeEvent, Dispatch, SetStateAction, useState } from 'react'
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';
import { useSignUpStore } from 'src/stores';
import FollowTheSignsIcon from '@mui/icons-material/FollowTheSigns';
import axios, { AxiosResponse } from 'axios';
import { SignUpDto } from 'src/apis/request/auth';
import { SignUpResponseDto } from 'src/apis/response/auth';
import ResponseDto from 'src/apis/response'
import { SIGN_UP_URL } from 'src/constants/api';





//          Component          //
interface FirstPageProps {
    signUpError: boolean;
}
function FirstPage({ signUpError }: FirstPageProps) {

    //          HOOK          //
    const { setEmail, setpassword, setpasswordCheck } = useSignUpStore();
    const { email, password, passwordCheck } = useSignUpStore();

    const [emailMessage, setEmailMessage] = useState<string>('');
    const [showPassword, setshowPassword] = useState<boolean>(false);
    const [PasswordCheckMessage,setPasswordCheckMessage] = useState<string>('');
    const [passwordMessage,setPasswordMessage] = useState<string>('');
    const [showPasswordCheck, setshowPasswordCheck] = useState<boolean>(false);


    //  ^ 정규식의 시작은 /^ 끝은 $/로  *는 길이제한 없다는 뜻    ? 는 또는 이라는 뜻 {2,3}은 2자리 또는 3자리 라는 뜻 
    const emailValidator = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
    const passwordValidator = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!?_]).{8,20}$/

    const onEmailChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        const value = event.target.value;
        const isMatched = emailValidator.test(value);
        if (isMatched) setEmailMessage('');
        else setEmailMessage('이메일 주소 포맷이 맞지 않습니다');
        setEmail(value);
    }

    const onPasswordChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>)=>{
        const value = event.target.value;
        const isMatched = passwordValidator.test(value);
        if(isMatched) setPasswordMessage('');
        else setPasswordCheckMessage('영대소문자 + 숫자 + 특수문자(!?_)를 포함한 8-20자');

        setpassword(value);
    }

    const onPasswordCheckChangeHandler = (event:ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) =>{
        const value = event.target.value;
        const isMatched = password === value;
        if(isMatched) setPasswordCheckMessage('');
        else setPasswordCheckMessage('비밀번호가 서로 일치하지 않습니다 ');
        setpasswordCheck(value);
    }

    return (
        <Box>
            <TextField sx={{ mt: '40px' }} fullWidth label='이메일 주소*' variant='standard' onChange={(event) => onEmailChangeHandler(event)} helperText={emailMessage} />
            <FormControl error={signUpError} fullWidth variant="standard" sx={{ mt: '40px' }}>
                <InputLabel>비밀번호*</InputLabel>
                <Input
                    type={showPassword ? "text" : "password"}
                    endAdornment={
                        <InputAdornment position="end">
                            <IconButton onClick={() => setshowPassword(!showPassword)}>
                                {showPassword ? <VisibilityOff /> : <Visibility />}
                            </IconButton>
                        </InputAdornment>
                    }
                    value={password}
                    onChange={(event) => onPasswordChangeHandler(event)}
                />
                <FormHelperText>{passwordMessage}</FormHelperText>
            </FormControl>

            <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
                <InputLabel>비밀번호 확인*</InputLabel>
                <Input
                    type={showPasswordCheck ? 'text' : 'password'}
                    error={signUpError}
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
                    onChange={(event) => onPasswordCheckChangeHandler(event)}
                />
                <FormHelperText>{PasswordCheckMessage}</FormHelperText>
            </FormControl>
        </Box>
    )
}
//          Component          //
interface SecondPageProps {
    signUpError: boolean;
}

function SecondPage({ signUpError }: SecondPageProps) {
    //          Hook          //
    const { nickname, telNumber, address, addressDetail } = useSignUpStore();
    const { setNickname, settelNumber, setaddress, setaddressDetail } = useSignUpStore();

    const [telNumberMessage,setTelNumberMessage] = useState<string>('');


    const telNumberValidator = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/

    //         Event Handler          //
    const onTelNumberHandler = (event:ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) =>{
        const value = event.target.value;
        const isMatched = telNumberValidator.test(value);
        if(isMatched) setTelNumberMessage('');
        else setTelNumberMessage('숫자만 입력해 주세요 ');
        settelNumber(value);
    }

    return (
        <Box>
            <TextField sx={{ mt: '40px' }} error={signUpError} fullWidth label='닉네임*' variant='standard' value={nickname} onChange={(event) => setNickname(event.target.value)} />
            {/* 원래의 nickname의 값이 바뀌면(event) setNickname 함수가 바뀌는 데로 nickname변수의 값을 바꿔준다  */}
            <TextField sx={{ mt: '40px' }} error={signUpError} fullWidth label='휴대폰 번호*' variant='standard' value={telNumber} onChange={(event) => onTelNumberHandler(event)} helperText={telNumberMessage} />

            <FormControl sx={{ mt: '40px' }} error={signUpError} fullWidth variant='standard' >
                <InputLabel>주소*</InputLabel>
                <Input type="text" endAdornment={
                    <InputAdornment position="end">
                        <IconButton>
                            <FollowTheSignsIcon />
                        </IconButton>
                    </InputAdornment>

                }
                    value={address}

                    onChange={(event) => setaddress(event.target.value)}

                />
            </FormControl>
            {/*원래의 값인 addressDetail의 값이 ''빈값이 바뀌면(onChange) onChange가 바로 발동 
                                                                                                        setaddressDetail(매개변수)에 매개변수로 들어온 
                                                                                                        event.target.value로 addressDetail이 바뀐다   */}
            <TextField sx={{ mt: '40px' }} error={signUpError} fullWidth label='상세주소*' variant='standard' value={addressDetail} onChange={(event) => setaddressDetail(event.target.value)} />
            {/* onChange={(event)=>setaddressDetail(event.target.value)}: 텍스트 입력 필드의 값이 변경될 때마다 호출되는 콜백 함수입니다.
                                                                                                      이벤트 개체를 인수로 받은 다음 event.target.value 속성을 사용하여 텍스트 입력 필드의 현재 값을 가져옵니다. 
                                                                                                      마지막으로 setAddressDetail 함수가 event.target.value 인수와 함께 호출되어 텍스트 입력 필드의 새 값으로 구성 요소의 상태를 업데이트합니다. */}
            
            <Box sx={{display:'flex',alignItems:'center',mt:'24px'}}>
                <Checkbox  color="default" />
                <Typography sx={{mr:'4px',color:'red',fontWeight:400}}>개인정보동의</Typography>
                <Typography sx={{fontWeight:500}}>더보기&gt;</Typography>
            </Box>
        </Box>
    );
}

interface Props {
    setLoginView: Dispatch<SetStateAction<boolean>>
}



export default function SignUpCardView({ setLoginView }: Props) {

    //          HOOK          //
    const [page, setPage] = useState<number>(1);
    const [signUpError, setSignUpError] = useState<boolean>(false);

    const { email, password, passwordCheck } = useSignUpStore();

    const { nickname, telNumber, address, addressDetail } = useSignUpStore();

    //  ^ 정규식의 시작은 /^ 끝은 $/로  *는 길이제한 없다는 뜻    ? 는 또는 이라는 뜻 {2,3}은 2자리 또는 3자리 라는 뜻 
    const emailValidator = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
    const passwordValidator = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!?_]).{8,20}$/

    //          Evenet Handler          //
    const onNextButtonHandler = () => {  //! {} 이므로 조건문 사용 가능 
        //todo : 이메일 /비밀번호/비밀번호 확인 검증 
        // ? 해당 문자열 변수가 빈값인지 확인 
        // ? 1. 해당 변수 == '';
        // ? 2. 해당 변수의 길이 == 0 ;
        if (!email || !password || !passwordCheck) {
            alert('모든 값을 입력하세요');

            return;
            //^ onNextButtonHandler를 호출했던 곳으로 돌아간다 
        }
        if (password !== passwordCheck) {
            alert('비밀번호가 서로 다릅니다 ')
            setSignUpError(true);
            return;
        }


        // todo : 검증이 실패하면 return 
        // todo : 검증이 성공하면 page 변경 
        setSignUpError(false)
        setPage(2);
    }
    // ? 동기함수로 바꾸는 async 
    const onSignUpHandler = () => {
        if (!email || !password || !passwordCheck) {
            setSignUpError(true);
            setPage(1);
            return;
        }

        if(!emailValidator.test(email)) return;
        if(!passwordValidator.test(password)) return;
        if(password!==passwordCheck) return;

        setSignUpError(true);
        setPage(2);

        if(!emailValidator.test(email)){
            setPage(1);
            return;
        }

        if(!passwordValidator.test(password)){
            setPage(1);
            return;
        }

        if (password !== passwordCheck) {
            
            setPage(1);
            return;

        }

        setSignUpError(false);



        const data: SignUpDto = {
            email, password, nickname, telNumber, address: `${address} ${addressDetail}`
        } //? json 객체 형태 




        // ? then()은 post()의 결과를 받아 실행되는 함수 만약 then()이 받은 결과가 잘못되었으면 catch()로 잡아준다 
        axios.post(SIGN_UP_URL, data)
            .then((response) => signUpResponseHandler(response))
            .catch((error) => signUpErrorHandler(error));

        // ^ async 함수는 await으로 받아 줘야한다 
        // const response = await axios.post("http://localhost:4040/auth/sign-up",data);


    }


    const signUpResponseHandler = (response: AxiosResponse<any, any>) => {

        const { result, message, data } = response.data as ResponseDto<SignUpResponseDto>;
        if (result) {
            setLoginView(true);
        } else {
            alert(message);
        }
    }

    const signUpErrorHandler = (error: any) => {
        console.log(error.response.status)
    }

    return (
        <Box display='flex' sx={{ height: '100%', flexDirection: 'column', justifyContent: 'space-between' }} >

            <Box>
                <Box display='flex' sx={{ justifyContent: 'space-between' }}>
                    {/* 가장 먼저 가로로든 세로로든 가로 세로 길이를 먼저 설정해 놔야한다 그래야 justeiftcontents가 먹힌다  */}
                    {/* 부모에다가 displat = flex 하면 한 block에  같이 나열하고 justifyContent는 space-between 해서 양 옆으로 붙여 버린다  */}
                    <Typography variant='h5' fontWeight={'900'}>회원가입</Typography>
                    <Typography variant='h5' fontWeight={'900'}>{page}/2</Typography>
                </Box>
                {page === 1 ? (<FirstPage signUpError={signUpError} />) : (<SecondPage signUpError={signUpError} />)}
            </Box>
            <Box>
                {page === 1 && (<Button fullWidth variant='contained' size='large' sx={{ mb: '20px' }} onClick={onNextButtonHandler}>다음 단계</Button>)}
                {page === 2 && (<Button fullWidth variant='contained' size='large' sx={{ mb: '20px' }} onClick={onSignUpHandler}>회원가입</Button>)}

                <Typography variant='h5' fontWeight={'900'}>이미 계정이 있으신가요?
                    <Typography component={'span'} fontWeight={900} onClick={() => setLoginView(true)}> 로그인</Typography>
                </Typography>

            </Box>
        </Box>
    )
}