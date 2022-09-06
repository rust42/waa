import { Link } from "react-router-dom";
import Button from 'react-bootstrap/Button';

const StudentItem = ({item}) => {
    return <>
        <div className="container-xxl">
            <div className="tab-content">
                <div id="tab-1" className="tab-pane fade show p-0 active">
                    <div className="job-item p-4 mb-4">
                        <div className="row g-4">
                            <div className="col-sm-12 col-md-8 d-flex align-items-center">
                                <Link to={`/students/${item.id}`}>
                                <img className="flex-shrink-0 img-fluid border rounded"
                                        style={{width: "80px", height: "80px"}}
                                        src="https://www.freeiconspng.com/uploads/account-profile-user-icon--icon-search-engine-10.png"
                                        />
                                </Link>
                                <div className="text-start ps-4">
                                    <h5 className="mb-3">{`${item.firstName} ${item.lastName} - ${item.id}`}</h5>
                                    <span className="text-truncate me-3"><i
                                        className="fa fa-map-marker-alt text-primary me-2"></i>{item.email}</span>
                                    <div className="text-truncate me-3"><i
                                        className="far fa-clock text-primary me-2">Grade {item.gpa}</i></div>
                                    <div className="text-truncate me-0">
                                        <i
                                            className="far fa-money-bill-alt text-primary me-2">{item.city}, {item.state}</i>
                                    </div>
                                    <div className="text-truncate me-0">
                                        <i
                                            className="far fa-money-bill-alt text-primary me-2">{item.major.type}</i>
                                    </div>
                                    <div
                                className="text-truncate me-0">
                                <div className="mb-3">
                                    <Link className="btn btn-primary" type="button"  style={{width:"10rem"}} to={`/students/${item.id}/comments`} > View Comment</Link>
                                    
                                </div>
                                
                            </div>
                                    <div className="text-truncate me-0">
                                        <i
                                            className="far fa-money-bill-alt text-primary me-2">
                                            <Link to={`${item.id}`} >Add Comment</Link>
                                        </i>
                                    </div>

                                </div>

                            </div>
                            <div
                                className="col-sm-12 col-md-4 d-flex flex-column align-items-start align-items-md-end justify-content-center">
                                <small className="text-truncate"><i
                                    className="far fa-calendar-alt text-primary me-2"></i>{ }</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </>;
}

export default StudentItem;