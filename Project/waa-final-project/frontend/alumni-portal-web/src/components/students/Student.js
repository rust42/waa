import {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {Link, useNavigate, useParams} from "react-router-dom";
import {addComment, fetchStudentById} from "../../reducers/StudentsSlice";

import Button from 'react-bootstrap/Button';
import {useKeycloak} from "@react-keycloak/web";
import {Col, Container, Row} from "react-bootstrap";

const Student = () => {
    const {keycloak} = useKeycloak()
    const currentUserEmail = keycloak.idTokenParsed.email
    const params = useParams()
    const [newComment, setNewComment] = useState(
        ""
    )

    const navigate = useNavigate();

    const student = useSelector(state => state.student.student)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(fetchStudentById({id: params.id}))
    }, [])


    const addNewComment = async () => {
        const comment = {
            'id': params.id,
            'comment': newComment,
        }
        dispatch(addComment({id: params.id, comment}));
        setNewComment("");
    }

    function navigateToExperiencePage() {
        navigate(`/students/${params.id}/jobExperiencesDetail`)
    }

    function navigateToCommentPage() {
        navigate(`/students/${params.id}/comments`)
    }

    function navigateToAddNewExeperiece() {
        navigate(`/students/jobHistory/new`);
    }

    return (
        <Container className="container" style={{marginTop: "1rem"}}>
            <Row className="justify-content-md-center">
                <Col xs={6}>
                    {student &&
                        <>
                            <table>
                                <tbody>
                                <tr>
                                    <td>Firstname: {student.firstName}</td>
                                </tr>
                                <tr>
                                    <td>Lastname: {student.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Email: {student.email}</td>
                                </tr>
                                <tr>
                                    <td>GPA: {student.gpa}</td>
                                </tr>
                                <tr>
                                    <td>State: {student.state}</td>
                                </tr>
                                <tr>
                                    <td>City: {student.city}</td>
                                </tr>
                                <tr>
                                    <td>Major: {student.major?.name}</td>
                                </tr>
                                <tr>
                                    <td>Interested
                                        Tags: {student.interestedTags.map(tag => tag.tagName).join(', ')}</td>
                                </tr>
                                <tr>
                                    <td>
                                        {
                                            (currentUserEmail === student.email) ?
                                                <>
                                                    <Link className="btn btn-primary" to="edit">
                                                        Edit
                                                    </Link>
                                                    <Button variant="primary" style={{marginLeft: "20px"}}
                                                            onClick={navigateToExperiencePage}>
                                                        View Experience Detail
                                                    </Button>
                                                    <Button variant="primary" style={{marginLeft: "20px"}}
                                                            onClick={navigateToAddNewExeperiece}>
                                                        Add Experience
                                                    </Button>
                                                </>
                                                :
                                                <>

                                                    <tr>
                                                        <td>Comment:</td>
                                                    </tr>
                                                    <textarea style={{height: "8rem", marginRight: "10px"}}
                                                              type="text"
                                                              value={newComment}
                                                              onChange={(e) => {
                                                                  setNewComment(e.target.value)
                                                              }}>
                                                        </textarea>
                                                    <Button variant="primary" onClick={addNewComment} >
                                                        Add Comment
                                                    </Button>
                                                    <br/>
                                                    <Button variant="primary"
                                                            style={{marginTop: "20px"}}
                                                            onClick={navigateToCommentPage}>
                                                        View Comment Detail
                                                    </Button>
                                                    <Button variant="primary"
                                                            style={{marginLeft: "20px", marginTop: "20px"}}
                                                            onClick={navigateToExperiencePage}>
                                                        View Experience Detail
                                                    </Button>
                                                </>
                                        }
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </>
                    }
                </Col>
            </Row>
        </Container>
    )
};

export default Student;