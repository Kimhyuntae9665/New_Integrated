import { IUSER } from "src/interfaces";
import { create } from "zustand";

interface IUserStore {

    user: IUSER | null;
    setUser:(user:IUSER)=>void;
    resetUser:()=>void;

}


const useStore = create<IUserStore>((set)=>({
    user:null,
    setUser:(user:IUSER) =>set((state)=>({...state,user})),
    resetUser:()=>set((state)=>({...state,user:null})),
}))

export default useStore;