
import { fetchCities as getCities } from '../services/StudentsService';
import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import LoadingState from "./LoadingState";

const initialState = {
    loading: LoadingState.IDLE,
    cities: []
};

export const fetchCities = createAsyncThunk(
    'fetchCities',
    async () => {
        return await getCities();
    }
);

const citiesSlice = createSlice(
    {
        name: "city",
        initialState,
        reducers: {},
        extraReducers: (builder) => {
            builder.addCase(fetchCities.pending, (state) => {
               state.loading = LoadingState.PENDING;
            });

            builder.addCase(fetchCities.fulfilled, (state, action) => {
               state.loading = LoadingState.SUCCEEDED;
               state.cities = action.payload;
            });

            builder.addCase(fetchCities.rejected, (state) => {
                state.loading = LoadingState.FAILED;
            })
        }
    }
);

export default citiesSlice.reducer;