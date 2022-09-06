import httpClient from "./HttpClient";

const getAllTags = async() =>{
    return httpClient.get("/tags")
}

export {getAllTags}