import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useKeycloak } from "@react-keycloak/web";
import { fetchStudentById, updateStudentById } from "../../reducers/StudentsSlice";
import { fetchDepartments } from "../../reducers/DepartmentsSlice";
import { fetchCities } from "../../reducers/CitiesSlice";
import { fetchStatesReducer } from "../../reducers/StatesSlice";
import { fetchTags } from "../../reducers/TagsSlice";
import Select from "react-select";

const EditStudent = () => {
    const { keycloak } = useKeycloak()
    const currentUserEmail = keycloak.idTokenParsed.email
    const params = useParams()
    const student = useSelector(state => state.student.student)
    const departments = useSelector(state => state.department.departments)
    const tags = useSelector(state => state.tags.tags);
    const cities = useSelector(state => state.city.cities)
    const states = useSelector(state => state.states.states)

    const [tagChoosing, setTagChoosing] = useState([]);
    const [tagOption, setTagOption] = useState([]);
    const [tagShowOptions, setTagShowOptions] = useState([]);
    const dispatch = useDispatch()
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [gpa, setGpa] = useState(0)
    const [stateId, setStateId] = useState("")
    const [cityId, setCityId] = useState("")
    const [majorId, setMajorId] = useState("")
    const [tagIds, setTagIds] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        dispatch(fetchStudentById({ id: params.id }))
        dispatch(fetchDepartments());
        dispatch(fetchCities())
        dispatch(fetchStatesReducer())
        dispatch(fetchTags())
    }, [])

    useEffect(() => {
        if (student) {
            setMajorId(student.major?.id)
            setLastName(student.lastName)
            setFirstName(student.firstName)
            setGpa(student.gpa)
            setStateId(student.state)
            setCityId(student.city)
            setTagIds(student.interestedTags.map(tag => tag.id))
        }
        let tagsOption = [];
        tags.forEach(tag => {
            tagsOption.push({
                value: tag.id,
                label: tag.tagName,
            })
        })
        setTagOption(tagsOption);
        let tagShowOptions = [];
        if (student) {
            student.interestedTags.forEach(tag => tagShowOptions.push({value: tag.id, label: tag.tagName}));
        }
        setTagShowOptions(tagShowOptions);
        let tagChosing =[];
        tagShowOptions.forEach(tag =>{
            tagChosing.push({ id: tag.value})
        })
        setTagChoosing(tagChosing);
    }, [student,tags])

    function chooseHandle(e) {
        setTagChoosing(Array.isArray(e) ? e.map(x => {
            return {
                id: x.value
            }
        }) : []);
        setTagShowOptions(Array.isArray(e) ? e.map(x => x) : []);
    }

    const onSave = async () => {
        const newStudent = {
            ...student,
            firstName: firstName,
            lastName: lastName,
            gpa: gpa,
            state: stateId,
            city: cityId,
            major: {
                id: majorId
            },
            interestedTags: tagChoosing
        }
        await dispatch(updateStudentById(newStudent));
        navigate(`/students/${student.id}`)
    }

    return (
        <Container className="container" style={{ marginTop: "1rem" }}>
            <Row className="justify-content-md-center">
                <Col xs={6}>
                    {student &&
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="text" value={student.email} disabled />
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>First Name</Form.Label>
                                <Form.Control type="text" value={firstName}
                                    onChange={(event) => setFirstName(event.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>Lastname</Form.Label>
                                <Form.Control type="text" value={lastName}
                                    onChange={(event) => setLastName(event.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>GPA</Form.Label>
                                <Form.Control type="text" value={gpa}
                                    onChange={(event) => setGpa(event.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label>State</Form.Label>
                                <Form.Select
                                    value={stateId}
                                    onChange={(event) => setStateId(event.target.value)}>
                                    {states.map(state => {
                                        return <option key={state} value={state}>{state}</option>
                                    })}
                                </Form.Select>
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>City</Form.Label>
                                <Form.Select
                                    value={cityId}
                                    onChange={(event) => setCityId(event.target.value)}>
                                    {cities.map(city => {
                                        return <option key={city} value={city}>{city}</option>
                                    })}
                                </Form.Select>
                            </Form.Group>
                            <Form.Group className="mb-3">
                                <Form.Label>Major</Form.Label>
                                <Form.Select
                                    value={majorId}
                                    onChange={(event) => setMajorId(event.target.value)}>
                                    {departments.map(department => {
                                        return <option key={department.id} value={department.id}>{department.name}</option>
                                    })}
                                </Form.Select>
                            </Form.Group>
                            <Form.Group className="md-6" controlId="formBasicEmail">
                                <Form.Label>Tag</Form.Label>
                                <Select isMulti
                                        value={tagShowOptions}
                                        options={tagOption} onChange={(e) => chooseHandle(e)}>
                                </Select>
                            </Form.Group>
                            {
                                (currentUserEmail === student.email) &&
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

export default EditStudent;