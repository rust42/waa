import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {getAllTags} from "../services/TagsService";
import LoadingState from "./LoadingState";

export const LoadingStatus = {
    IDLE: 'idle',
    PENDING: 'pending',
    SUCCEEDED: 'succeeded',
    FAILED: 'failed'
}

const initialState = {
    tags: [],
    loading: LoadingStatus.IDLE
}

export const fetchTags = createAsyncThunk(
    "fetchAllTags",
    async () => {
        return await getAllTags();
    }
)

const tagsSlice = createSlice({
    name: "tagsSlice",
    initialState,
    reducers: [],
    extraReducers: (builder) => {
        builder.addCase(fetchTags.fulfilled, (state, action) => {
            state.tags = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        })
        builder.addCase(fetchTags.pending, (state) => {
            state.loading = LoadingState.PENDING;
        })
        builder.addCase(fetchTags.rejected, (state) => {
            state.loading = LoadingState.FAILED;
        })
    }
})

export const {} = tagsSlice.actions;

export default tagsSlice.reducer;