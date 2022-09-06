import httpClient from "./HttpClient";

const activityCountByAction = async () => {
    return await httpClient.get(`/activities/echarts/activityCountByAction`);
}

const activityCountByUser = async () => {
    return await httpClient.get(`/activities/echarts/activityCountByUser`);
}

const activityCountByUserIn24 = async () => {
    return await httpClient.get(`/activities/echarts/activityCountByUserIn24`);
}

const activityCountByActionIn24 = async () => {
    return await httpClient.get(`/activities/echarts/activityCountByActionIn24`);
}

export {activityCountByAction, activityCountByActionIn24, activityCountByUserIn24, activityCountByUser}