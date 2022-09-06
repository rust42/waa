import {useDispatch, useSelector} from "react-redux";
import {deleteHistory, fetchAllByStudentId} from "../../reducers/JobHistorySlice";
import {useEffect} from "react";
import Moment from 'moment';
import {Form} from "react-bootstrap";
import {useParams} from "react-router-dom";

export default function JobExperienceHistories() {
    Moment.locale('en');
    const jobHistory = useSelector(state => state.jobHistories.jobHistories);
    const dispatch = useDispatch();
    const params = useParams();
    useEffect(() => {
        dispatch(fetchAllByStudentId({id:params.id}))
    },[])

    async function deleteById() {
        dispatch(deleteHistory({id: this.id}))
    }
    return (
        <div className="container-xxl bg-white p-0 rounded-circle">
            {jobHistory.map((row) => (
                <div className="d-grid gap-3">
                    <div className="p-2 bg-light border" style={{marginTop:"20px"}}>
                        <h1>Title: {row.title}</h1>
                        <h2>{row.company}</h2>
                        <h3>{row.tags.map(data => {
                            return <span key={data.id} className="text-truncate me-3"><i
                                className="fa fa-map-marker-alt text-primary me-2">{data.tagName}</i></span>
                        })}</h3>
                        <h5>From: {Moment(row.startDate).format('MMM yyyy')} to {Moment(row.endDate).format('MMM yyyy')}</h5>
                        <p>Description: {row.description}</p>
                        <p>Comments: {row.comments}</p>
                        <button className="btn btn-dark border-0 w-100" onClick={deleteById.bind(row)}>Delete</button>
                    </div>
                </div>
            ))}
        </div>)
}