// ? 인터페이스 관리 
export interface IpreviewItem{
    img: string | null;
    writerProfile: string;
    writerNickName: string;
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