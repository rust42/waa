import { createSlice } from '@reduxjs/toolkit';
import { createAsyncThunk } from "@reduxjs/toolkit";
import { fetchApplicationByJobId } from '../services/JobApplicationService';
import LoadingState from './LoadingState';

const initialState = {
    jobApplications: [],
    loading: LoadingState.IDLE
}

export const getApplicationByJobId = createAsyncThunk(
    'getApplicationByJobId',
    async (id) => {
        return await fetchApplicationByJobId(id);
    }
)

const jobApplicationsSlice = createSlice({
    name: 'jobApplicationsSlice',
    initialState,
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(getApplicationByJobId.fulfilled, (state, action) => {
            state.jobApplications = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(getApplicationByJobId.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

    }
});

export default jobApplicationsSlice.reducer;
