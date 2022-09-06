import httpClient from "./HttpClient";

const getJobsByLocation = async () => {
    return await httpClient.get('/jobAdvertisements/echarts/jobsByLocation');
}

const getStudentsPerState = async () => {
    return await httpClient.get('/students/echarts/studentsPerState');
}

const getStudentsByCity = async (state) => {
    return await httpClient.get('/students/echarts/studentsByCity', {state});
}

const getStateStudentLiving = async () => {
    return await httpClient.get('/students/states');
}

const getTagByJob = async () => {
    return await httpClient.get('/jobAdvertisements/echarts/tagsByJob');
}

const getTagByJobInLocation = async (state) => {
    return await httpClient.get('/jobAdvertisements/echarts/tagsByLocation', {state});
}

const getJobsByCity = async () => {
    return await httpClient.get('/jobAdvertisements/echarts/tagsByCity');
}

export {getJobsByLocation, getStudentsPerState, getStudentsByCity, getStateStudentLiving, getTagByJob, getTagByJobInLocation, getJobsByCity}