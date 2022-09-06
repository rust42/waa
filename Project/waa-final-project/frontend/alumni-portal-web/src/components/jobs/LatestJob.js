import * as React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import JobSearchBar from "./JobSearchBar";
import { Link } from "react-router-dom";
import { fetchJobs } from "../../reducers/JobsSlice";
import Table from 'react-bootstrap/Table';


const LatestJob = () => {
    const jobs = useSelector(state => state.jobs.latestJobs)
    const [value, setValue] = React.useState(null);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchJobs());
    }, []);

    return (
        <div className="container-xxl bg-white p-0">
            <>

            <Table striped bordered hover>
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
                               <td className='action'><Link className='btn btn-primary' to={`/jobs/${row.id}`} >Detail</Link></td> 
                               </tr> 
                                   
                            ))}
         
          
       
        </tbody>
        </Table>

            </>
           

        </div>


    );
};

export default LatestJob;