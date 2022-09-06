import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {
    editFacultyById,
    fetchFaculties,
    fetchFacultyById,
    fetchStudentsBySearching
} from "../services/FacultiesService";
import LoadingState from './LoadingState';


const initialState = {
    faculty: null,
    faculties: [],
    students: [],
    loading: LoadingState.IDLE
}

export const getFaculties = createAsyncThunk(
    'getFaculties',
    async () => {
        return await fetchFaculties();
    }
)

export const getFacultyById = createAsyncThunk(
    'getFacultyById',
    async (id) => {
        return await fetchFacultyById(id);
    }
)

export const updateFacultyById = createAsyncThunk(
    'editFacultyById',
    async (faculty) => {
        return await editFacultyById(faculty);
    }
)

export const fetchStudentsBySearch = createAsyncThunk(
    'fetchStudentsBySearch',
    async ({searchText, city, department, state, studentId}) => {
        return await fetchStudentsBySearching(searchText, city, department, state, studentId);
    }
)


const facultiesSlice = createSlice({
    name: 'faculties',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(getFaculties.fulfilled, (state, action) => {
            state.faculties = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(getFaculties.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(getFacultyById.fulfilled, (state, action) => {
            state.faculty = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(getFacultyById.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(updateFacultyById.fulfilled, (state, action) => {
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(updateFacultyById.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStudentsBySearch.fulfilled, (state, action) => {
            state.students = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchStudentsBySearch.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStudentsBySearch.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });

    }
});

export default facultiesSlice.reducer;
