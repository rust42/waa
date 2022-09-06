import * as React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import JobSearchBar from "./JobSearchBar";
import { Link } from "react-router-dom";
import { fetchMyJobs } from "../../reducers/JobsSlice";
import Table from 'react-bootstrap/Table';


const MyJobs = () => {
    const jobs = useSelector(state => state.jobs.myJobs)
    const [value, setValue] = React.useState(null);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchMyJobs());
    }, []);

    return (
        <div className="container-xxl bg-white p-0">

            <Table striped bordered hover style={{margin: "35px"}}>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Job Title</th>
                        <th>City</th>
                        <th>Company Name</th>
                        <th>Creation Time</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    {jobs.map((row) => (
                        <tr key={row.id}>
                            <td>{row.id}</td>
                            <td>{row.title}</td>
                            <td>{row.city}</td>
                            <td>{row.companyName}</td>
                            <td>{row.creationTime}</td>
                            <td>
                                <Link className="btn btn-primary" to={`/jobs/${row.id}`} style={{marginRight: "10px"}}>View Detail</Link>
                                <Link className="btn btn-primary" to={`/jobs/${row.id}/jobApplications`} >View Application</Link>
                            </td>
                        </tr>

                    ))}
                </tbody>
            </Table>
        </div>


    );
};

export default MyJobs;