import {configureStore} from "@reduxjs/toolkit";
import studentsSlice from "./reducers/StudentsSlice";
import jobsSlice from "./reducers/JobsSlice";
import tagsSlice from "./reducers/TagsSlice"
import departmentSlice from "./reducers/DepartmentsSlice";
import citiesSlice from "./reducers/CitiesSlice";
import facultiesSlice from "./reducers/FacultiesSlice";
import userSlice from "./reducers/UserSlice";
import jobHistorySlice from "./reducers/JobHistorySlice"
import JobApplicationsSlice from "./reducers/JobApplicationsSlice";
import statesSlice from "./reducers/StatesSlice";
import CommentsSlice from "./reducers/CommentsSlice";
import echartsSlice from "./reducers/EchartsSlice";
import activitiesSlice from "./reducers/ActivitiesSlice";
import notificationInfoSlice from "./reducers/NotificationInfoSlice";

const store = configureStore({
    reducer: {
        students: studentsSlice,
        jobs: jobsSlice,
        tags: tagsSlice,
        student: studentsSlice,
        faculty: facultiesSlice,
        user: userSlice,
        department: departmentSlice,
        city: citiesSlice,
        states: statesSlice,
        jobHistories: jobHistorySlice,
        jobApplications: JobApplicationsSlice,
        comments:CommentsSlice,
        echarts: echartsSlice,
        activities: activitiesSlice,
        notificationInfo: notificationInfoSlice
    }
});

export default store;