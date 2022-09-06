import JobItem from "./JobItem";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { getRecetlyAppliedJob, searchJob } from "../../reducers/JobsSlice";
import LoadingState from "../../reducers/LoadingState";

const jobListView = (jobs, loading) => {
    if (loading === LoadingState.PENDING) {
        return <h4 className="text-center">Loading</h4>;
    }

    if (loading === LoadingState.FAILED) {
        return <h4 className="text-center" style={{ color: 'red' }}>Error occurred fetching jobs</h4>;
    }

    if (loading === LoadingState.SUCCEEDED && jobs.length === 0) {
        return <h4 className="text-center">No Job Advertisements found.</h4>
    }

    return <div>
        {jobs.map(job => <JobItem key={job.id} item={job} />)}
    </div>;

}
const RecentlyAppliedJobs = () => {
    const { jobs, loading } = useSelector(state => state.jobs.recentlyAppliedJobs);
    const dispatch = useDispatch();


    useEffect(() => {
        dispatch(getRecetlyAppliedJob());
    }, [])


    return (
        <div className="container-xxl bg-white p-0">
            {jobListView(jobs, loading)}
        </div>
    )
};

export default RecentlyAppliedJobs;