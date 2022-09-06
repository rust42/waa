import httpClient from "./HttpClient";


const fetchUserByEmail = async (email) => {
    return await httpClient.get(`/users/findByEmail?email=${email}`);
}

export { fetchUserByEmail }