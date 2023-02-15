import { Card,Box,Typography ,Avatar} from '@mui/material'
import React from 'react'
import { IpreviewItem } from 'src/interfaces'
import BoardListItem from '../BoardListItem';

interface Props{
    previewItem: IpreviewItem
}

export default function PreviewCard({previewItem}:Props) {

  // ^ ``백 스쿼트  이미지 가져오기 
    const backgroundImage=`url(${previewItem.img})`;


  return (
   <Card sx={{height:'508px' ,backgroundImage:backgroundImage ,display:'flex',backgroundSize:'cover',flexDirection:'column-reverse'}}>
        <Box sx={{p:'24px' }}>
        <Box sx={{display:'flex'}}>
            <Box sx={{mr:'8px'}}>
                <Avatar alt="Remy Sharp" src="{previewItem.writerProfile}" />
            </Box>
            <Box>
                <Typography sx={{fontSize:'12px',fontWeight:500,color:'#ffffff'}}>{previewItem.writerNickName}</Typography>
                {/* rgba의 a는 투명도  */}
                <Typography sx={{mt:'2px', fontSize:'12px',fontWeight:400,color:'rgba(255,255,255,0.7)'}}>{previewItem.writeDate}</Typography>
            </Box>

        </Box>
        <Box sx={{mt:'16px',mb:'16px'}}>
            <Typography sx={{fontSize:'16px',fontWeight:500,color:'#ffffff'}}>{previewItem.boardTitle}</Typography>
            <Typography sx={{mt:'5px',fontSize:'12px',fontWeight:400,color:'rgba(255,255,255,0.7)'}}>{previewItem.boardContent}</Typography>

        </Box>
        <Box>
            <Typography sx={{fontSize:'12px',fontWeight:400,color:'rgba(255,255,255,0.7)'}}>{`댓글 ${previewItem.commentCount} · 좋아요 ${previewItem.likeCount} · 조회수 ${previewItem.viewCount}`}</Typography>

        </Box>
    </Box>
   </Card>
  )
}
