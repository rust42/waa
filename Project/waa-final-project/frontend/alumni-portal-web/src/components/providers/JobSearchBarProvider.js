import {createContext} from "react";

const SearchJobBarContext = createContext({
    city: "",
    setCity: undefined,
    cities:[],
    state: "",
    setState: undefined,
    states:[],
    companyName: "",
    setCompanyName: undefined,
    tagChoosing:"",
    setTagChoosing:undefined,
    tagOption:[],
    setTagOption:undefined,
});

export { SearchJobBarContext };
