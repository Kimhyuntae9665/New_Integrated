import { useState } from 'react'
// ? Box 컴포넌트 : mui에서 공간을 할당하는 모든 태그를 포함 
// ? Grid 컴포넌트 : mui에서 공간을 12등분하여 반응형 가로 사이즈에 따라 반응형 웹 개발을 지원 
import { Box, Grid, Card, TextField, FormControl, InputLabel, Input, InputAdornment, IconButton, Button } from '@mui/material'
// ? Typography 컴포넌트는 :mui 에서 글자를 출력하는 모든 태그를 포함
import { Typography } from '@mui/material'
import ContentPasteTwoToneIcon from '@mui/icons-material/ContentPasteTwoTone';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';
import LoginCardView from './LoginCardView';
import SignUpCardView from './SingUpCardView';


// ^ 컴포넌트 함수 안 return()안에서는 조건문 사용 불가하므로 조건문으로 논리연산자와 삼항연산자를 사용  
// ? 논리 연산자(&&) : if문만 쓸 때 
// ? 삼항 연산자 (조건 ? 참 : 거짓 ): if-else /if -else if -else 쓸 때 

export default function AuthenticationView() {

    // ^ 화면이 바뀌거나 안 바뀌거나 둘 중 하나이기 때문에 boolean 이 적절하다 
    const[loginView,setLoginView] = useState<boolean>(true);


    // !use state를 통해 컴포넌트 에서 바뀌는 값 관리
    // ! 눈 알 누르면 password <=> text 로 바뀐다 

    
    return (
        <Box sx={{ pr: "120px", pl: "120px" }}>
            <Grid container spacing={2}>
                <Grid item lg={7} sm={12}>
                    <Box sx={{ display: 'flex', height: '100%', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
                        {/*alignItems : center 는 좌우 기준으로 중앙으로 배치 justifyContent : center는 위 아래 기준으로 중앙 정렬   */}
                        <ContentPasteTwoToneIcon sx={{ fontSize: 40 }} />
                        <Typography variant="h4">환영합니다.</Typography>
                        <Typography variant="h4">HOONS BOARD 입니다</Typography>
                    </Box>
                </Grid>
                <Grid item lg={5} sm={12}>
                    <Card sx={{ height: '630px', mt: '100px', mb: '80px', pt: '50px', pb: '30px', pl: '50px', pr: '50px' }}>
                        
                        
                    {/* // ^ 컴포넌트 함수 안 return()안에서는 조건문 사용 불가하므로 조건문으로 논리연산자와 삼항연산자를 사용   */}

                        
                        {/* // ? 삼항 연산자 (조건 ? 참 : 거짓 ): if-else /if -else if -else 쓸 때  */}
                        {/*                                      로그인 뷰 / 회원가입 뷰 /백지 뷰  */}
                        {/*             소괄호 잊지말자  */}
                        

                                     {/*LoginCardView 의 매개변수로 setLoginView={setLoginView}를 받은거   */}
                         {/* loginView 의 초기값이 true이기 때문에 LoginCardView의 함수가 호출되어 Default 값으로 로그인 창이 보이는거  */}
                        {loginView ?  (<LoginCardView setLoginView={setLoginView} />):(<SignUpCardView setLoginView={setLoginView}/>)}
                        

                    </Card>
                </Grid>
            </Grid>

        </Box>
    )
}

