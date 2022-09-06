import * as React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import { getApplicationByJobId } from '../../reducers/JobApplicationsSlice';


const JobApplications = () => {
    const jobApplications = useSelector(state => state.jobApplications.jobApplications)
    const dispatch = useDispatch();
    const params = useParams();

    useEffect(() => {
        dispatch(getApplicationByJobId(params.jobId));
    }, []);

    return (
        <div className="container-xxl bg-white p-0">

            <Table striped bordered hover style={{margin: "35px"}}>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Applicant's Name</th>
                        <th>Applicant's Email</th>
                        <th>Time</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    {jobApplications.map((row) => (
                        <tr key={row.id}>
                            <td>{row.id}</td>
                            <td>{row.title}</td>
                            <td>{row.applicant.firstName} {row.applicant.lastName} </td>
                            <td>{row.applicant.email}</td>
                            <td>{row.creationTime}</td>
                            <td><Link className="btn btn-primary" to={`/jobApplications/${row.id}`} >View Detail</Link></td>
                        </tr>

                    ))}
                </tbody>
            </Table>
        </div>


    );
};

export default JobApplications;