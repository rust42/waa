import React from "react";
import JobAdvBasedOnLocation from "./JobAdvBasedOnLocation";
import StudentsPerState from "./StudentsPerState";
import StudentsPerCitySearchByState from "./StudentsPerCitySearchByState";
import TagByJob from "./TagByJob";
import TagByJobInLocationSearchByState from "./TagByJobInLocationSearchByState";
import ActivityCountByUser from "./ActivityCountByUser";
import ActivityCountByUserIn24 from "./ActivityCountByUserIn24";
import ActivityCountByAction from "./ActivityCountByAction";
import ActivityCountByActionIn24 from "./ActivityCountByActionIn24";
import JobPerCity from "./JobPerCity";

export default function Echarts() {
    return (
        <div className="container-xxl bg-white p-0 rounded-circle">
            <JobAdvBasedOnLocation/>
            <StudentsPerState/>
            <StudentsPerCitySearchByState/>
            <TagByJob/>
            <TagByJobInLocationSearchByState/>
            <ActivityCountByUser/>
            <ActivityCountByUserIn24/>
            <ActivityCountByAction/>
            <ActivityCountByActionIn24/>
            <JobPerCity/>
        </div>
    )
}