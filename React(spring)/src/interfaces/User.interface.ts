interface User{

    email:string;
    nickname:string;
    telNumber:string;
    address:string;
    // ^ 필수가 아니다 라는 ? 표시 
    profile?:string | null;
}

export default User;