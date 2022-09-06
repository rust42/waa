import { createSlice } from '@reduxjs/toolkit';
import { createAsyncThunk } from "@reduxjs/toolkit";
import { fetchStudents as getStudents, fetchStudent as getStudent, editStudentById } from "../services/StudentsService";
import LoadingState from "./LoadingState";
import { addNewComment } from '../services/StudentsService';

const initialState = {
    student: null,
    students:{ loading: LoadingState.IDLE, content: [], totalPages: 0, totalElements: 0, page: -1},
    loading: LoadingState.IDLE
}

export const fetchStudents = createAsyncThunk(
    'fetchStudents',
    async ({ searchText, city, department ,state, studentId, page, pageSize}) => {
        return await getStudents(searchText, city, department,state, studentId, page, pageSize);
    }
)



export const fetchStudentById = createAsyncThunk(
    'fetchStudentById',
    async ({id}) => {
        return await getStudent(id);
    }
)


export const addComment = createAsyncThunk(
    'addComment',
    async ({id,comment}) => {
        return await addNewComment(id,comment);
    }
)

export const updateStudentById = createAsyncThunk(
    'editStudentById',
    async (student) => {
        return await editStudentById(student);
    }
)


const studentsSlice = createSlice({
    name: 'students',
    initialState,
    reducers: {
    },
    extraReducers: (builder) => {
        builder.addCase(fetchStudents.fulfilled, (state, action) => {
            state.students.loading = LoadingState.SUCCEEDED;
            state.students.totalPages = action.payload.totalPages;
            state.students.totalElements = action.payload.totalElements;
            state.students.page = action.payload.currentPage;

            if (action.payload.currentPage === 0) {
                state.students.content = action.payload.content;
            } else {
                state.students.content.push(...action.payload.content);
            }
        });

        builder.addCase(fetchStudents.pending, (state, action) => {
            state.students.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStudents.rejected, (state, action) => {
            state.students.loading = LoadingState.FAILED;
        });


        builder.addCase(fetchStudentById.fulfilled, (state, action) => {
            state.student = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchStudentById.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStudentById.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(addComment.fulfilled, (state, action) => {
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(addComment.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(addComment.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });
      

        builder.addCase(updateStudentById.fulfilled, (state, action) => {
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(updateStudentById.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });
    }
});

export default studentsSlice.reducer;
