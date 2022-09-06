import httpClient from "./HttpClient";

const fetchStudents = async (searchText, city, department,state,studentId, page, pageSize = 5) => {
    return await httpClient.get('/students', { searchText, city, department, state,studentId, page, pageSize});
}

const fetchCities = async () => {
    return await httpClient.get('/students/cities');
}

const fetchStudent = async (id) => {
    return await httpClient.get(`/students/${id}`);
}


const fetchDepartments = async () => {
    return await httpClient.get("/departments");
}


const addNewComment = async (id,data) => {
    return await httpClient.post(`/students/${id}/comments`, data);
}

const editStudentById = async (student) => {
    return await httpClient.put(`/students/${student.id}`, student);
}

export { fetchStudents, fetchStudent, fetchDepartments, fetchCities, addNewComment, editStudentById}