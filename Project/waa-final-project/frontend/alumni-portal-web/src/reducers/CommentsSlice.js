import { createSlice } from '@reduxjs/toolkit';
import { createAsyncThunk } from "@reduxjs/toolkit";
import { fetchCommentByStudentId  } from '../services/CommentService';
import LoadingState from './LoadingState';

const initialState = {
    comments: [],
    loading: LoadingState.IDLE
}

export const getCommentByStudentId = createAsyncThunk(
    'getCommentByStudentId',
    async (id) => {
        return await fetchCommentByStudentId(id);
    }
)

const commentsSlice = createSlice({
    name: 'commentsSlice',
    initialState,
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(getCommentByStudentId.fulfilled, (state, action) => {
            state.comments = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(getCommentByStudentId.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

    }
});

export default commentsSlice.reducer;