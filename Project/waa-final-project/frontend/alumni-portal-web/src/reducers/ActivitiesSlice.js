import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import LoadingState from "./LoadingState";
import {
    activityCountByAction,
    activityCountByActionIn24,
    activityCountByUser,
    activityCountByUserIn24
} from "../services/ActivitiesService";

const initialState = {
    activityCountByUser: [],
    activityCountByAction: [],
    activityCountByUserIn24: [],
    activityCountByActionIn24: [],
    loading: LoadingState.IDLE // can be one of 'idle' | 'pending' | 'succeeded' | 'failed'
}

export const fetchActivityCountByUser = createAsyncThunk(
    'fetchActivityCountByUser',
    async () => {
        return await activityCountByUser()
    }
)

export const fetchActivityCountByAction = createAsyncThunk(
    'fetchActivityCountByAction',
    async () => {
        return await activityCountByAction()
    }
)

export const fetchActivityCountByUserIn24 = createAsyncThunk(
    'fetchActivityCountByUserIn24',
    async () => {
        return await activityCountByUserIn24()
    }
)

export const fetchActivityCountByActionIn24 = createAsyncThunk(
    'fetchActivityCountByActionIn24',
    async () => {
        return await activityCountByActionIn24()
    }
)

const activitiesSlice = createSlice({
    name: 'jobs',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchActivityCountByUser.fulfilled, (state, action) => {
            state.activityCountByUser = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchActivityCountByUser.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchActivityCountByUser.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchActivityCountByAction.fulfilled, (state, action) => {
            state.activityCountByAction = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchActivityCountByAction.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchActivityCountByAction.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchActivityCountByUserIn24.fulfilled, (state, action) => {
            state.activityCountByUserIn24 = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchActivityCountByUserIn24.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchActivityCountByUserIn24.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchActivityCountByActionIn24.fulfilled, (state, action) => {
            state.activityCountByActionIn24 = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchActivityCountByActionIn24.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchActivityCountByActionIn24.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });
    }
});

export const {} = activitiesSlice.actions;

export default activitiesSlice.reducer;
