import {createContext} from "react";

const SearchBarContext = createContext({
    city: "",
    setCity: undefined,
    department: "",
    setDepartment: undefined,
    departments: [],
    cities: [],
    state:"",
    setState:undefined,
    states:[],
    studentId:"",
    setStudentId:undefined
});

export { SearchBarContext };
