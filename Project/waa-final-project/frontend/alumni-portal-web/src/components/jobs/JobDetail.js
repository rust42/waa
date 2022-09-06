import { useKeycloak } from "@react-keycloak/web";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux"
import {Link, useNavigate, useParams} from "react-router-dom";
import {fetchJobById} from "../../reducers/JobsSlice";
import parse from 'html-react-parser';
import '../../css/JobDetail.css';

export default function JobDetail() {
    const job = useSelector((state) => state.jobs.job)
    const dispatch = useDispatch()
    const params = useParams()
    const { keycloak } = useKeycloak()
    const currentUserEmail = keycloak.idTokenParsed.email;

    const navigate = useNavigate();

    useEffect(() => {
        dispatch(fetchJobById({id:params.id}))
    }, [])
    
    return (
        <div className="container">
            {job &&
                <div>
                    <div className="row" style={{marginTop:"20px"}}>
                        <div className="col-sm-9">
                            <div className="card">
                            <div className="card-body">
                                <h3 className="card-title">{job.title}</h3>
                                <div><div className="col-sm-12 col-md-8 d-flex align-items-center">
                                    {job.tags.map(tag => {
                                        return (
                                            <h5 key={tag.id}><span className="label badge badge-secondary m-lg-1 bg-primary bg-darken-xl">{tag.tagName}</span></h5>
                                        )
                                    })
                                    }
                                </div>

                                </div>
                                <h4>Description</h4>
                                <div className="card-text description" >
                                    <p className="text-justify">
                                        {parse(job.description)}
                                    </p>
                                </div>
                                <h4>Benefit</h4>
                                <p className="card-text">{job.benefits}</p>
                                {currentUserEmail === job.student.email ?
                                    <>
                                        <Link className="btn btn-primary" to={`/jobs/${params.id}/edit`} style={{marginRight: "10px"}}>Edit</Link>
                                        <Link className="btn btn-primary" to={`/jobs/${params.id}/jobApplications`} >View Application</Link>
                                    </>
                                    :
                                    <Link className="btn btn-primary" type="button" to={`/jobs/${params.id}/apply`} >Apply</Link>
                                }
                            </div>
                            </div>
                        </div>
                        <div className="col-sm-3">
                            <div className="card">
                            <div className="card-body">
                                <div>Company: {job.companyName}</div>
                                <div>State: {job.state}</div>
                                <div>City: {job.city}</div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
        </div>
    )

}