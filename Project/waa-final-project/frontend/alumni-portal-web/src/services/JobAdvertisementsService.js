import httpClient from "./HttpClient";

const fetch10LatestJobAdvs = async () => {
    return await httpClient.get('/jobAdvertisements/getFirst10ByCreationTime');
}

const getMyJobs = async () => {
    return await httpClient.get('/jobAdvertisements/findMyPostedJob');
}

const addNewJob = async (data) => {
    return await httpClient.post('/jobAdvertisements/', data);
}

const editById = async (data, id) => {
    return await httpClient.put(`/jobAdvertisements/${id}`, data);
}

const fetchJobById = async (id) => {
    return await httpClient.get(`/jobAdvertisements/${id}`)
}

const searchJobByQuery = async (tags, state, city, companyName, page = 1, pageSize = 5) => {
    return await httpClient.get(`/jobAdvertisements/searching`, {tags, state, city, companyName, page, pageSize})
}

const fetchStates = async () => {
    return await httpClient.get("/jobAdvertisements/states");
}

const fetchRecentlyAppliedJob = async () => {
    return await httpClient.get("/jobAdvertisements/recentlyAppliedJob");
}

const fetchJobCities = async () => {
    return await httpClient.get('/jobAdvertisements/cities');
}

export {fetch10LatestJobAdvs, addNewJob, fetchJobById, editById, getMyJobs, searchJobByQuery, fetchStates, fetchRecentlyAppliedJob, fetchJobCities}
