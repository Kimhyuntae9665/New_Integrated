import React from 'react'
import {Box} from '@mui/material';
import MyPageHead from './MyPageHead';
import MyPageContent from './MyPageContents';

export default function MyPageView() {
  return (
    <Box>
        <MyPageHead/>
        <MyPageContent/>
    </Box>
  )
}
