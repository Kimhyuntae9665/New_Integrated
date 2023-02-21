import { Avatar, Typography, Card, CardActionArea } from '@mui/material';

import { Box } from '@mui/system'
import React from 'react'
import { useNavigate } from 'react-router-dom';
import { IpreviewItem } from 'src/interfaces';

interface Props {
    item: IpreviewItem
};




export default function BoardListItem({ item }: Props) {

        const navigator = useNavigate();

    return (
        <Card variant='outlined' >
            <CardActionArea sx={{ display: 'flex', justifyContent: 'space-between', p: '24px', backgroundColor: '#ffffff' }} onClick={()=>navigator(`/board/update/${item.boardNumber}`)}>
                <Box>
                    <Box sx={{ display: 'flex' }}>
                        <Box sx={{ mr: '8px' }}>
                            <Avatar alt={item.writerNickName} src={item.writerProfile} />
                        </Box>
                        <Box>
                            <Typography sx={{ fontSize: '12px', fontWeight: 500, color: '#000000' }}>{item.writerNickName}</Typography>
                            {/* rgba의 a는 투명도  */}
                            <Typography sx={{ mt: '2px', fontSize: '12px', fontWeight: 400, color: 'rgba(0,0,0,0.7)' }}>{item.writeDate}</Typography>
                        </Box>

                    </Box>
                    <Box sx={{ mt: '16px', mb: '16px' }}>
                        <Typography sx={{ fontSize: '16px', fontWeight: 500, color: '#000000' }}>{item.boardTitle}</Typography>
                        <Typography sx={{ mt: '5px', fontSize: '12px', fontWeight: 400, color: 'rgba(0,0,0,0.7)' }}>{item.boardContent}</Typography>

                    </Box>
                    <Box>
                        <Typography sx={{ fontSize: '12px', fontWeight: 400, color: 'rgba(0,0,0,0.7)' }}>{`댓글 ${item.commentCount} · 좋아요 ${item.likeCount} · 조회수 ${item.viewCount}`}</Typography>

                    </Box>
                </Box>
                {/* item.img가 있으면 이미지 사진을 보여준다  */}
                {item.img && (
                    <Box>
                        {/* 박스가 단독으로<Box/> 있다  img tag 사용 할떄는 박스 한개만 단독으로 */}
                        <Box component='img' src={item.img as string} sx={{ height: '152px', width: '152px', borderRadius: '5%' }} />
                    </Box>
                )}
            </CardActionArea>

        </Card>
    )
}
