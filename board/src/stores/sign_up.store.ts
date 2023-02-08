//  ^ sign-in.store.ts
// # java Class 또는 React의 컴포넌트의 파일명은 UpperCamelCase를 따랐음
// # Typescript의 경우 트별한 파일의 네이밍 규칙이 지정되어 있지 않기 때문에 
// # 필수적으로 UpperCamelCase를 사용할 필요가 없음 

// # zustand를 사용하여 스토어 생성
// ^ zustand  에서 create 요소를 import
import {create} from "zustand";


// # Typescript에서 함수의 타입을 지정하는 방법
// ? (매개변수명 : 매개변수타입) => 반환타입

interface ISignUpStore{
    email : string;
    password : string;
    passwordCheck:string;
    nickName: string;
    telNumber : string;
    address : string;
    addressDetail : string;
    setEmail:(email:string) =>void;
    setpassword:(password:string)=>void;
    setpasswordCheck:(passwordCheck:string)=>void;
    setnickName:(passnickName:string)=>void;
    settelNumber:(telNumber:string)=>void;
    setaddress:(address:string)=>void;
    setaddressDetail:(addressDetail:string)=>void;
}


// ! 객체 반환 
// ^ create 메서드를 사용해서 스토어를 생성 
const useStore = create<ISignUpStore>((set)=>({
    // ^ 상태의 이름 
    // ^ 상태변수 선언 
    email:'',
    password:'',
    passwordCheck:'',
    nickName:'',
    telNumber:'',
    address:'',
    addressDetail:'',

    setEmail:(email)=>set((state)=>({...state,email})),
    setpassword:(password)=>set((state)=>({...state,password})),
    setpasswordCheck:(passwordCheck)=>set((state)=>({...state,passwordCheck})),
    setnickName:(nickName)=>set((state)=>({...state,nickName})),
    settelNumber:(telNumber)=>set((state)=>({...state,telNumber})),
    setaddress:(address)=>set((state)=>({...state,address})),
    setaddressDetail:(addressDetail)=>set((state)=>({...state,addressDetail})),

    
   
}))
// ! useStore는 함수이다 create함수를 가지고 있기 때문이다 
export default useStore;

//!  일반적인 상태를 선언하는 코드 
// ? const [상태,메서드] = useState<Generics>(초기화 값);
// ! Zustand를 사용하서 상태를 선언하는 코드 
// ? const useStore = create<데이터 타입>((set)=>({
// ?    state 변수 이름  :  초기화 값
// ?    상태명1: 초기화 값,
// ?    상태명2:초기화 값,
// ?    상태명3: 초기화 값


// ?    set메서드(상태를 변경하는 메서드) : (파라미터)=>set((state)=>({...state,파라미터})) 
// !    set메서드는 중간에 파라미터에 따라서 누굴위한 set메서드 인지가 결정된다 
// !    파라미터로 들어가지 않는 상태명들은 그냥 쩌리로 ...state에서 자동 묶음 처리 
// !     상태명1 에 대한 set메서드 
// ?    set메서드(상태를 변경하는 메서드) : (상태명1)=>set((state)=>({...state,상태명1})) 
// !    상태명 2 에 대한 set메서드 
// ?    set메서드(상태를 변경하는 메서드) : (상태명2)=>set((state)=>({...state,상태명2})) 
// !    상태명1,상태명2에 대한 set메서드 
// ?    set메서드(상태를 변경하는 메서드) : (상태명1,상태명2)=>set((state)=>({...state,상태명1,상태명2})) 

// ?  }))

// ^ 1. const useStore =  create((set)=>({...state}))
// ^     :==useState

// ^2. 상태명 : 초기화 값,
// ^    :==[상태명,...] = ...(초기화 값)

// ^ 3. set메서드: (파라미터)=>set((state)=>({...state,파라미터}))
// ^     :==[...,set메서드]

// ^ 4. const{요소1,요소2,...} = 객체;
// ? 객체 : 객체에서 지정한 요소를 제외하고 남은 요소를 객체로 묶음 처리 함 
// ? const{요소1,...묶음 객체 명 } = 객체 

// const mainObj = {
//     a:0,
//     b:1,
//     c:2,
//     d:3
// }

// const {a,...subObj} = mainObj;

// // ? subObj = {b:1,c:2,d:3}

// const setMainObjA = (a:number) =>{
//     mainObj
// }