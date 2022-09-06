import {LoadingStatus} from "./TagsSlice";
import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {createNewJobHistory, deleteHistoryById, fetchAllHistoriesByStudentId} from "../services/JobHistoriesService";
import LoadingState from "./LoadingState";

const initialState = {
    jobHistories: [],
    jobHistory: null,
    loading: LoadingStatus.IDLE
}

export const fetchAllByStudentId = createAsyncThunk(
    'fetchAllByStudentId',
    async ({id}) => {
        return await fetchAllHistoriesByStudentId(id);
    }
)

export const createNewHistory = createAsyncThunk(
    'createNewHistory',
    async ({data}) => {
        return await createNewJobHistory(data);
    }
)

export const deleteHistory = createAsyncThunk(
    'deleteHistory',
    async ({id}) => {
        return await deleteHistoryById(id);
    }
)

const JobHistorySlice = createSlice({
    name: 'jobHistorySlice',
    initialState,
    reducers: {
        setTag(state, action) {
            state.jobHistory.Tag = action.payload;
        },
        setCompany(state, action) {
            state.jobHistory.company = action.payload;
        },
        setStateDate(state, action) {
            state.jobHistory.stateDate = action.payload;
        },
        setEndDate(state, action) {
            state.jobHistory.endDate = action.payload;
        },
        setReasonToLeave(state, action) {
            state.jobHistory.reasonToLeave = action.payload;
        },
        setComments(state, action) {
            state.jobHistory.comments = action.payload;
        },
    },
    extraReducers: (builder) => {
        builder.addCase(fetchAllByStudentId.fulfilled, (state, action) => {
            state.jobHistories = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchAllByStudentId.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchAllByStudentId.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(createNewHistory.fulfilled, (state, action) => {
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(createNewHistory.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(createNewHistory.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(deleteHistory.fulfilled, (state, action) => {
            state.jobHistories = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(deleteHistory.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(deleteHistory.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });
    }
})

export const {
    setTag,
    setCompany,
    setStateDate,
    setEndDate,
    setReasonToLeave,
    setComments
} = JobHistorySlice.actions;

export default JobHistorySlice.reducer;