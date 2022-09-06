import { useKeycloak } from "@react-keycloak/web";
import { useEffect } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import { getFacultyById } from "../../reducers/FacultiesSlice";

const Faculty = () => {
    const params = useParams()
    const faculty = useSelector(state => state.faculty.faculty)
    const dispatch = useDispatch()
    const { keycloak } = useKeycloak()
    const currentUserEmail = keycloak.idTokenParsed.email;

    useEffect(() => {
        dispatch(getFacultyById(params.id))
    }, [])

    return (
        <div className="container">
            {faculty &&
                <>
                    <div className="row justify-content-md-center">
                        <div className="col col-md-3">
                            <div className="card" style={{width: "18rem", marginTop: "1rem"}}>
                                <img className="flex-shrink-0 img-fluid border rounded"
                                        style={{width: "100%", height: "100%"}}
                                        src="https://www.freeiconspng.com/uploads/account-profile-user-icon--icon-search-engine-10.png"
                                        />
                                <div className="card-body">
                                    <h5 className="card-title">{faculty.firstName} {faculty.lastName}</h5>
                                </div>
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">Email: {faculty.email}</li>
                                    <li className="list-group-item">Department: {faculty.department?.name}</li>
                                </ul>
                                <div className="card-body">
                                    {
                                        (currentUserEmail === faculty.email) &&
                                        <Link className="card-link" to="edit">
                                            Edit
                                        </Link>
                                    }
                                </div>
                            </div>
                            {/* <table>
                                <tbody>
                                    <tr>
                                        <td>Firtname</td>
                                        <td>{faculty.firstName}</td>
                                    </tr>
                                    <tr>
                                        <td>Lastname</td>
                                        <td>{faculty.lastName}</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td>{faculty.email}</td>
                                    </tr>
                                    <tr>
                                        <td>Department</td>
                                        <td>{faculty.department.name}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            {
                                                (currentUserEmail === faculty.email) &&
                                                <Link className="btn btn-primary btn-block" to="edit">
                                                    Edit
                                                </Link>
                                            }
                                        </td>
                                    </tr>
                                </tbody>
                            </table> */}

                        </div>
                    </div>

                </>
            }
        </div>
    )
};

export default Faculty;