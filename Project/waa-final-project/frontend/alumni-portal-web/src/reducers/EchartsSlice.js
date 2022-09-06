import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import LoadingState from "./LoadingState";
import {
    getJobsByCity,
    getJobsByLocation,
    getStateStudentLiving,
    getStudentsByCity,
    getStudentsPerState, getTagByJob, getTagByJobInLocation
} from "../services/EchartsService";

const initialState = {
    jobsByCity:[],
    jobByLocation: [],
    studentsPerState:[],
    tagByJob:[],
    tagByJobInLocation:[],
    studentsPerCitySearchByState:[],
    stateStudentLiving:[],
    loading: LoadingState.IDLE,
}

export const fetchJobsByLocation = createAsyncThunk(
    'fetchJobsByLocation',
    async () => {
        return await getJobsByLocation();
    }
)

export const fetchJobsByCity = createAsyncThunk(
    'fetchJobsByCity',
    async () => {
        return await getJobsByCity();
    }
)

export const fetchTagByLocation = createAsyncThunk(
    'fetchTagByLocation',
    async ({state}) => {
        return await getTagByJobInLocation(state);
    }
)

export const fetchTagByJob = createAsyncThunk(
    'fetchTagByJob',
    async () => {
        return await getTagByJob();
    }
)

export const fetchStudentsPerState = createAsyncThunk(
    'fetchStudentsPerState',
    async () => {
        return await getStudentsPerState();
    }
)

export const fetchStudentsByCity = createAsyncThunk(
    'fetchStudentsByCity',
    async ({state}) => {
        return await getStudentsByCity(state);
    }
)

export const fetchStateOfStudent = createAsyncThunk(
    'fetchStateOfStudent',
    async () => {
        return await getStateStudentLiving();
    }
)

const echartsSlice = createSlice({
    name: 'echartsSlice',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchJobsByLocation.fulfilled, (state, action) => {
            state.jobByLocation = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchJobsByLocation.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchJobsByLocation.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStudentsPerState.fulfilled, (state, action) => {
            state.studentsPerState = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchStudentsPerState.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchStudentsPerState.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStudentsByCity.fulfilled, (state, action) => {
            state.studentsPerCitySearchByState = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchStudentsByCity.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchStudentsByCity.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchStateOfStudent.fulfilled, (state, action) => {
            state.stateStudentLiving = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchStateOfStudent.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchStateOfStudent.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });


        builder.addCase(fetchTagByJob.fulfilled, (state, action) => {
            state.tagByJob = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchTagByJob.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchTagByJob.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchTagByLocation.fulfilled, (state, action) => {
            state.tagByJobInLocation = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchTagByLocation.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchTagByLocation.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchJobsByCity.fulfilled, (state, action) => {
            state.jobsByCity = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchJobsByCity.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchJobsByCity.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

    }
});

export const {} = echartsSlice.actions;

export default echartsSlice.reducer;
