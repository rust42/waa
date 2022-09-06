import { createSlice } from '@reduxjs/toolkit';
import { createAsyncThunk } from "@reduxjs/toolkit";
import { fetchUserByEmail } from '../services/UsersService';
import LoadingState from './LoadingState';

const initialState = {
    user: null,
    loading: LoadingState.IDLE
}

export const getUserByEmail = createAsyncThunk(
    'getUserByEmail',
    async (id) => {
        return await fetchUserByEmail(id);
    }
)

const usersSlice = createSlice({
    name: 'usersSlice',
    initialState,
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(getUserByEmail.fulfilled, (state, action) => {
            state.user = action.payload;
            state.loading = LoadingState.SUCCEEDED;
        });

        builder.addCase(getUserByEmail.pending, (state, action) => {
            state.loading = LoadingState.PENDING;
        });

    }
});

export default usersSlice.reducer;
