import httpClient from "./HttpClient";

const fetchFaculties= async () => {
    return await httpClient.get('/faculties');
}

const fetchFacultyById = async (id) => {
    return await httpClient.get(`/faculties/${id}`);
}

const editFacultyById = async (faculty) => {
    return await httpClient.put(`/faculties/${faculty.id}`, faculty);
}

const fetchStudentsBySearching = async (searchText, city, department, state, studentId) => {
    return await httpClient.get('/faculties/searching', { searchText, city, department , state, studentId});
}

export { fetchFaculties, fetchFacultyById, editFacultyById , fetchStudentsBySearching}