import {LoadingStatus} from "./TagsSlice";
import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {fetchStates} from "../services/JobAdvertisementsService";

const initialState = {
    states: [],
    loading: LoadingStatus.IDLE,
}

export const fetchStatesReducer = createAsyncThunk(
    'fetchStates',
    async () => {
        return await fetchStates();
    }
)

const StatesSlice = createSlice({
    name: "stateSlice",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(fetchStatesReducer.fulfilled, (state, action) => {
            state.states = action.payload;
            state.loading = LoadingStatus.SUCCEEDED;
        })
        builder.addCase(fetchStatesReducer.rejected, (state, action) => {
            state.loading = LoadingStatus.FAILED;
        })
        builder.addCase(fetchStatesReducer.pending, (state, action) => {
            state.loading = LoadingStatus.PENDING;
        })
    }
})

export const {} = StatesSlice.actions;

export default StatesSlice.reducer;