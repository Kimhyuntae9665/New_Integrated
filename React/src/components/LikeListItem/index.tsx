import React from 'react'
import { Avatar, Box, Typography } from '@mui/material'
import { ILikeUser, Likey } from 'src/interfaces'

interface Props {
    likeUser: Likey
}

export default function LikeListItem({ likeUser }: Props) {

  return (
    <Box sx={{ display: 'inline-flex', alignItems: 'center', mr: '30px' }}>
        <Avatar sx={{ height: '32px', width: '32px', mr: '8px' }} src={likeUser.userProfileUrl ? likeUser.userProfileUrl:''} />
        <Typography component='span' sx={{ fontSize: '16px', fontWeight: 500 }}>{likeUser.userNickname}</Typography>
    </Box>
    
  )
}