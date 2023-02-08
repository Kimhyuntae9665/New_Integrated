import React, { Dispatch } from 'react'
import { Box, TextField, FormControl, InputLabel, Input, InputAdornment, IconButton, Button } from '@mui/material'
import { Typography } from '@mui/material'
import { useState } from 'react'
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';

// ! export default 아닌 그냥 export는 {}꼭 해줘야한다 
import { useSignUpStore } from 'src/stores';

interface Props {
    setLoginView: Dispatch<React.SetStateAction<boolean>>;
}


export default function LoginCardView({ setLoginView }: Props) {
   
    

    const [showPassword, setshowPassword] = useState<boolean>(false);

    return (
        <Box display='flex' sx={{ height: '100%', flexDirection: 'column', justifyContent: 'space-between' }}>

            <Box>
                <Typography variant='h4' fontWeight='900'>로그인</Typography>
                <TextField sx={{ mt: '40px' }} fullWidth label="E-mail" variant="standard" />

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
                    />
                </FormControl>

            </Box>

            <Box>
                <Button sx={{ mb: '20px' }} fullWidth variant="contained" size='large'>로그인</Button>
                <Typography textAlign={'center'}>신규 사용자 이신가요?
                    <Typography component={'span'} fontWeight={900} onClick={() => setLoginView(false)}> 회원가입</Typography>
                </Typography>


            </Box>
        </Box>
    )
}
