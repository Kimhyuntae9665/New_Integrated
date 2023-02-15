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