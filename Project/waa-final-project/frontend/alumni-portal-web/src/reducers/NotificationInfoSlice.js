import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import LoadingState from "./LoadingState";
import { registerNotificationInfo } from '../services/NotificationInfoService';

const initialState = {
    loading: LoadingState.IDLE,
    notificationInfo: []
};

export const registerNotiInfo = createAsyncThunk(
    'registerNotiInfo',
    async (data) => {
        return await registerNotificationInfo(data);
    }
);

const notificationInfoSlice = createSlice({
        name: "notificationInfo",
        initialState,
        reducers: {},
        extraReducers: (builder) => {
            builder.addCase(registerNotiInfo.pending, (state) => {
               state.loading = LoadingState.PENDING;
            });

            builder.addCase(registerNotiInfo.fulfilled, (state, action) => {
               state.loading = LoadingState.SUCCEEDED;
               state.notificationInfo = action.payload;
            });

            builder.addCase(registerNotiInfo.rejected, (state) => {
                state.loading = LoadingState.FAILED;
            })
        }
    }
);

export default notificationInfoSlice.reducer;