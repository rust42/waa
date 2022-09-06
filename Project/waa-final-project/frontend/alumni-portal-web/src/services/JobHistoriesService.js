import httpClient from "./HttpClient";

const fetchAllHistoriesByStudentId = async (id) =>{
    return await httpClient.get(`/jobHistories/student/${id}`);
}

const createNewJobHistory = async (data) => {
    return await httpClient.post(`/jobHistories/`, data);
}

const deleteHistoryById = async (id) => {
    return await httpClient.delete(`/jobHistories/${id}`, );
}
export {fetchAllHistoriesByStudentId, createNewJobHistory, deleteHistoryById}