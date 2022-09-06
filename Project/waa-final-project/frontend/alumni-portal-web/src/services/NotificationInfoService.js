import httpClient from "./HttpClient";

const registerNotificationInfo = async (token) => {
    const data = {
        token: token
    }
    return await httpClient.post(`/notificationInfo/registerToken`, data);
}

export { registerNotificationInfo }

