import httpClient from "./HttpClient";

const fetchApplicationByJobId= async (id) => {
    return await httpClient.get(`/jobApplications/findLastByJobAdsId?id=${id}`);
}

export { fetchApplicationByJobId }