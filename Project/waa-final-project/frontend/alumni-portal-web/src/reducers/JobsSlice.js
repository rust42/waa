import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import {
    addNewJob,
    editById,
    fetch10LatestJobAdvs as get10LatestJobAdvs,
    fetchJobById as getJobById, fetchJobCities, fetchRecentlyAppliedJob, getMyJobs, searchJobByQuery
} from "../services/JobAdvertisementsService";
import LoadingState from "./LoadingState";

const initialState = {
    cities:[],
    job: null,
    latestJobs:[],
    editJobs: { loading: LoadingState.IDLE, job: {}},
    myJobs:[],
    jobs:{ loading: LoadingState.IDLE, content: [], totalPages: 0, totalElements: 0, page: -1},
    recentlyAppliedJobs: {loading: LoadingState.IDLE, jobs: []},
    loading: LoadingState.IDLE // can be one of 'idle' | 'pending' | 'succeeded' | 'failed'
}

export const fetchJobs = createAsyncThunk(
    'fetchJobs',
    async () => {
        return await get10LatestJobAdvs();
    }
)

export const searchJob = createAsyncThunk(
    'searchJob',
    async ({tagChoosing, state, city, companyName, page, pageSize}) => {
        return await searchJobByQuery(tagChoosing, state, city, companyName, page);
    }
)

export const fetchMyJobs = createAsyncThunk(
    'fetchMyJobs',
    async () => {
        return await getMyJobs();
    }
)

export const fetchJobById = createAsyncThunk(
    'fetchJobById',
    async ({id}) => {
        console.log('fetchJobById', id)
        return await getJobById(id)
    }
)

export const addJob = createAsyncThunk(
    'addJob',
    async ({data}) => {
        return await addNewJob(data)
    }
)

export const editJobById = createAsyncThunk(
    'editJobById',
    async ({data, id}) => {
        return await editById(data, id)
    }
)

export const getRecetlyAppliedJob = createAsyncThunk(
    'getRecetlyAppliedJob',
    async () => {
        return await fetchRecentlyAppliedJob()
    }
)

export const fetchJobsCities = createAsyncThunk(
    'fetchJobsCities',
    async () => {
        return await fetchJobCities()
    }
)

const jobsSlice = createSlice({
    name: 'jobs',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchJobs.fulfilled, (state, action) => {
            state.latestJobs = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchJobs.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchJobs.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchMyJobs.fulfilled, (state, action) => {
            state.myJobs = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchMyJobs.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchMyJobs.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(fetchJobById.fulfilled, (state, action) => {
            state.job = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(fetchJobById.pending, (state) => {
            state.loading = LoadingState.PENDING;
        });

        builder.addCase(addJob.fulfilled, (state, action) => {
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(addJob.pending, (state) => {
            state.loading = LoadingState.PENDING;
        })

        builder.addCase(editJobById.fulfilled, (state, action) => {
            state.editJobs.loading = LoadingState.SUCCEEDED;
            state.editJobs.job = action.payload;
        });

        builder.addCase(editJobById.rejected, (state, action) => {
            state.editJobs.loading = LoadingState.FAILED;
        });

        builder.addCase(editJobById.pending, (state) => {
            state.editJobs.loading = LoadingState.PENDING;
        })

        builder.addCase(searchJob.fulfilled, (state, action) => {
            state.jobs.loading = LoadingState.SUCCEEDED;
            state.jobs.totalPages = action.payload.totalPages;
            state.jobs.totalElements = action.payload.totalElements;
            state.jobs.page = action.payload.currentPage;

            if (action.payload.currentPage === 0) {
                state.jobs.content = action.payload.content;
            } else {
                state.jobs.content.push(...action.payload.content);
            }
        });

        builder.addCase(searchJob.rejected, (state, action) => {
            state.jobs.loading = LoadingState.FAILED;
        });

        builder.addCase(searchJob.pending, (state) => {
            state.jobs.loading = LoadingState.PENDING;
        })

        builder.addCase(getRecetlyAppliedJob.fulfilled, (state, action) => {
            state.recentlyAppliedJobs.loading = LoadingState.SUCCEEDED;
            state.recentlyAppliedJobs.jobs = action.payload;
        });

        builder.addCase(getRecetlyAppliedJob.rejected, (state, action) => {
            state.recentlyAppliedJobs.loading = LoadingState.FAILED;
        });

        builder.addCase(getRecetlyAppliedJob.pending, (state) => {
            state.recentlyAppliedJobs.loading = LoadingState.PENDING;
        })


        builder.addCase(fetchJobsCities.fulfilled, (state, action) => {
            state.loading = LoadingState.SUCCEEDED;
            state.cities = action.payload;
        });

        builder.addCase(fetchJobsCities.rejected, (state, action) => {
            state.loading = LoadingState.FAILED;
        });

        builder.addCase(fetchJobsCities.pending, (state) => {
            state.loading = LoadingState.PENDING;
        })

    }
});

export const {} = jobsSlice.actions;

export default jobsSlice.reducer;
