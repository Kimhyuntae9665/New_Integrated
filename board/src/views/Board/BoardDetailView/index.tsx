import { MouseEvent, useEffect, useState } from 'react'

import { Avatar, Box, Divider, IconButton, Menu, MenuItem, Typography } from '@mui/material'
import MoreVertIcon from '@mui/icons-material/MoreVert';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import CommentOutlinedIcon from '@mui/icons-material/CommentOutlined';
import KeyboardArrowDownOutlinedIcon from '@mui/icons-material/KeyboardArrowDownOutlined';
import KeyboardArrowUpOutlinedIcon from '@mui/icons-material/KeyboardArrowUpOutlined';
import { useNavigate, useParams } from 'react-router-dom';
import { BOARD_LIST } from 'src/mock';
import { IpreviewItem } from 'src/interfaces';

export default function BoardDetailView() {

    const [anchorElement, setAnchorElement] = useState<null | HTMLElement>(null);
    const [menuOpen, setMenuOpen] = useState<boolean>(false);
    const [board, setBoard] = useState<null | IpreviewItem>(null);

    const { boardNumber } = useParams();
    const navigator = useNavigate();

    const onMenuClickHandler = (event: MouseEvent<HTMLButtonElement>) => {
        setAnchorElement(event.currentTarget);
        setMenuOpen(true);
    }

    const onMenuCloseHandler = () => {
        setAnchorElement(null);
        setMenuOpen(false);
    }

    useEffect(() => {
        //? boardNumber가 존재하는지 검증
        if (!boardNumber) {
            navigator('/');
            return;
        }
        //? BOARD_LIST에서 boardNumber에 해당하는 board를 가져옴
        const board = BOARD_LIST.find((boardItem) => boardItem.boardNumber === parseInt(boardNumber));
        //? 검색한 결과가 존재하는지 검증
        if (!board) {
            navigator('/');
            return;
        }
        setBoard(board);
    }, [])

  return (
    <Box sx={{ p: '100px 222px' }}>
        <Box>
            <Box>
                <Typography sx={{ fontSize: '32px', fontWeight: 500 }}>{board?.boardTitle}</Typography>
                <Box sx={{ mt: '20px', display: 'flex', justifyContent: 'space-between' }}>
                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                        <Avatar src={board?.writerProfile} sx={{ height: '32px', width: '32px', mr: '8px' }} />
                        <Typography sx={{ mr: '8px', fontSize: '16px', fontWeight: 500 }}>{board?.writerNickName}</Typography>
                        <Divider sx={{ mr: '8px' }} orientation='vertical' variant='middle' flexItem />
                        <Typography sx={{ fontSize: '16px', fontWeight: 400, opacity: 0.4 }}>{board?.writeDate}</Typography>
                    </Box>
                    <IconButton onClick={(event) => onMenuClickHandler(event)}>
                        <MoreVertIcon />
                    </IconButton>
                    <Menu anchorEl={anchorElement} open={menuOpen} onClose={onMenuCloseHandler}>
                        <MenuItem sx={{ p: '10px 59px', opacity: 0.5 }}>수정</MenuItem>
                        <Divider />
                        <MenuItem sx={{ p: '10px 59px', color: '#ff0000', opacity: 0.5 }}>삭제</MenuItem>
                    </Menu>
                </Box>
            </Box>
            <Divider sx={{ m: '40px 0px' }} />
            <Box>
                <Typography sx={{ fontSize: '18px', fontWeight: 500, opacity: 0.7 }}>{board?.boardContent}</Typography>
                { board?.img && (<Box sx={{ width: '100%', mt: '20px' }} component='img' src={board?.img} />) }
            </Box>
            <Box sx={{ display: 'flex', mt: '20px' }}>
                <Box sx={{ mr: '20px', display: 'flex' }}>
                    <FavoriteBorderIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} />
                    <Typography sx={{ fontSize: '16px', fontWeight: 500, opacity: 0.7, mr: '6px' }}>좋아요 {board?.likeCount}</Typography>
                    <IconButton sx={{ height: '24px', width: '24px' }}>
                        <KeyboardArrowDownOutlinedIcon />
                    </IconButton>
                </Box>
                <Box sx={{ display: 'flex' }}>
                    <CommentOutlinedIcon sx={{ height: '24px', width: '24px', mr: '6px', opacity: 0.7 }} />
                    <Typography sx={{ fontSize: '16px', fontWeight: 500, opacity: 0.7, mr: '6px' }}>댓글 {board?.commentCount}</Typography>
                    <IconButton sx={{ height: '24px', width: '24px' }}>
                        <KeyboardArrowDownOutlinedIcon />
                    </IconButton>
                </Box>
            </Box>
        </Box>
        <Box></Box>
        <Box></Box>
    </Box>
  )
}