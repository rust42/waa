import {Link} from "react-router-dom";
import parse from 'html-react-parser';

const JobItem = ({
                     item: {id, title, state, companyName, description, creationTime, tags, shortDescription, city},
                     children
                 }) => {
    return <>
        <div className="container-xxl py-0">
            <div className="tab-content">
                <div id="tab-1" className="tab-pane fade show p-0 active">
                    <div className="job-item p-4 mb-0">
                        <div className="row g-4">
                            <div className="col-sm-12 col-md-8 d-flex align-items-center">
                                <div className="text-start">
                                    <Link to={`/jobs/${id}`}>
                                        <h5 className="mb-0">{title}</h5>
                                    </Link>
                                    <div>
                                <span className="text-truncate me-3"><i
                                    className="far fa-clock text-primary me-2">{companyName}</i></span></div>
                                    <span className="text-truncate me-3"><i
                                        className="fa fa-map-marker-alt text-primary me-2">{city} - {state}</i></span>
                                </div>
                            </div>
                            <div className="col-sm-12 col-md-8 d-flex align-items-center">
                                    {tags.map(tag => {
                                        return (
                                            <h5 key={tag.id}><span className="label badge badge-secondary m-lg-3 bg-primary bg-darken-xl">{tag.tagName}</span></h5>
                                        )
                                    })
                                    }
                            </div>
                            <div className="col-sm-12 col-md-8 d-flex align-items-center">
                                <div className="text-start">
                                <span ><i
                                    className="far fa-money-bill-alt text-primary "></i>
                                    {parse(shortDescription ? shortDescription : description)}
                                    </span>
                                </div>
                            </div>
                            <div
                                className="col-sm-12 col-md-4 d-flex flex-column align-items-start align-items-md-end justify-content-center">
                                <div className="d-flex mb-3">
                                    <Link className="btn btn-primary" type="button" to={`/jobs/${id}/apply`}>Apply
                                        Now</Link>
                                </div>
                                <small className="text-truncate"><i
                                    className="far fa-calendar-alt text-primary me-2"></i>{creationTime}</small>
                            </div>
                        </div>
                    </div>
                    {children}
                </div>
                <hr/>
            </div>
        </div>
    </>;
}

export default JobItem;