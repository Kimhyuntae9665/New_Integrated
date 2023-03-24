interface Comment{

    commentNumber:number;
    writerEmail:string;
    boardNumber:number;
    writeDatetime:string;
    commentContent:string;
    // ^ ? 는 없어도 된다는 의미 
    writerProfileUrl? : string | null;
    writernickName: string;

}
export default Comment;