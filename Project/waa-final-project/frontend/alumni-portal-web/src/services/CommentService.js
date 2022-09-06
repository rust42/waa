import httpClient from "./HttpClient";

const fetchCommentByStudentId= async (id) => {
    return await httpClient.get(`/students/${id}/comments`);
}

export { fetchCommentByStudentId }

