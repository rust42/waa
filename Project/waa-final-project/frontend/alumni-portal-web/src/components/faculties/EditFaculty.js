import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { getFacultyById, updateFacultyById } from "../../reducers/FacultiesSlice";
import { useKeycloak } from "@react-keycloak/web";
import { fetchDepartments } from "../../reducers/DepartmentsSlice";
import LoadingState from "../../reducers/LoadingState";

const EditFaculty = () => {
    const params = useParams()
    const faculty = useSelector(state => state.faculty.faculty)
    const dispatch = useDispatch()
    const { keycloak } = useKeycloak()
    const currentUserEmail = keycloak.idTokenParsed.email
    const departments = useSelector(state => state.department.departments)
    const [departmentId, setDepartmentId] = useState("")
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const navigate = useNavigate()
    

    useEffect(() => {
        dispatch(getFacultyById(params.id))
        dispatch(fetchDepartments());
    }, [])

    useEffect(() => {
        if(faculty){
            setDepartmentId(faculty.department?.id)
            setLastName(faculty.lastName)
            setFirstName(faculty.firstName)
        }
    }, [faculty])

    const onSave = async ()=>{
        const newFaculty = {
            ... faculty, 
            firstName: firstName,
            lastName: lastName,
            department:{
                id: departmentId
            }
        }
        await dispatch(updateFacultyById(newFaculty));
        navigate(`/faculties/${faculty.id}`)
    }

    return (
        <Container className="container" style={{marginTop: "1rem"}}>
            <Row className="justify-content-md-center">
                <Col xs={6}>
                    {faculty &&
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="text" value={faculty.email} disabled/>
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>First Name</Form.Label>
                                <Form.Control type="text" value={firstName} 
                                onChange={(event) => setFirstName(event.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Lastname</Form.Label>
                                <Form.Control type="text" value={lastName}
                                onChange={(event) => setLastName(event.target.value)}/>
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Department </Form.Label>
                                <Form.Select
                                        value={departmentId}
                                        onChange={(event) => setDepartmentId(event.target.value)}>
                                    {departments.map(department => {
                                        return <option key={department.id} value={department.id}>{department.name}</option>
                                    })}
                                </Form.Select>
                            </Form.Group>
                            {
                                (currentUserEmail === faculty.email) &&
                                <Button variant="primary" onClick={onSave}>
                                    Save
                                </Button>
                            }
                        </Form>
                    }
                </Col>
            </Row>
        </Container>
    )
};

export default EditFaculty;