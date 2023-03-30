// ? 인터페이스 관리 


import { type } from 'os';
import Board from './Board.interface'
import Comment from './Comment.interface'
import Likey from './Likey.interface'
import User from './User.interface'

export interface IpreviewItem{
    img: string | null;
    writerProfile: string;
    writerNickname: string;
    writeDate:string;
    boardTitle: string;
    boardContent: string;
    likeCount: number;
    commentCount:number;
    viewCount:number;

    boardNumber:number;
}

export interface IUSER{
    email:string;
    password:string;
    nickname:string;
    telNumber:string;
    address:string;
    addressDetail:string;
    // ^ profile을 넣지 않아도 된다 
    profile?:string;
}

export interface ILikeUser{
    likeUserProfile:string;
    likeUserNickname:string;
}

export interface ICommentItem{
    commentUserProfile:string;
    commentUserNickname:string;
    commentContent:string;
    commentDateTime:string;
}

export type {
    Board,Comment,Likey,User
}