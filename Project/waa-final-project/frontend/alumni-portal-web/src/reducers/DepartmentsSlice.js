import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import { fetchDepartments as getDepartments } from '../services/StudentsService';
import LoadingStatus from "./LoadingState";

const initialState = {
    departments: [],
    loading: LoadingStatus.IDLE
}

export const fetchDepartments = createAsyncThunk(
     'fetchDepartments',
    async () => {
        return await getDepartments()
    }
);

const departmentSlice = createSlice(
    {
        name: 'departments',
        initialState: initialState,
        reducers: {

        },
        extraReducers: (builder) => {
            builder.addCase(fetchDepartments.fulfilled, (state, action) => {
                state.departments = action.payload;
                state.loading = LoadingStatus.SUCCEEDED;
            });

            builder.addCase(fetchDepartments.pending, (state, action) => {
                state.loading = LoadingStatus.PENDING;
            });
        }
    }
);

export default departmentSlice.reducer;