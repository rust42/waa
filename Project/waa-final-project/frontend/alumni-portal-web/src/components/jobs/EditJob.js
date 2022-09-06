import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {fetchTags} from "../../reducers/TagsSlice";
import {editJobById, fetchJobById} from "../../reducers/JobsSlice";
import {useNavigate, useParams} from "react-router-dom";
import {useKeycloak} from "@react-keycloak/web";
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Select from "react-select";
import { TextEditor } from "./TextEditor";

const EditJob = () => {
    const [jobState, setJobState] = useState({
        tags: [],
        shortDescription: "",
        description: "",
        benefits: "",
        state: "",
        companyName: "",
        city: "",
        title: "",
        file: [],
    });

    const tags = useSelector(state => state.tags.tags);
    const existedJob = useSelector(state => state.jobs.job);

    let {id} = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const {keycloak} = useKeycloak();
    // const currentUserEmail = keycloak.idTokenParsed.email;

    const [tagChoosing, setTagChoosing] = useState([]);
    const [tagOption, setTagOption] = useState([]);
    const [tagShowOptions, setTagShowOptions] = useState([]);

    useEffect(() => {
        dispatch(fetchTags());
        dispatch(fetchJobById({id: id}));
    }, [])

    useEffect(() => {
        setJobState(existedJob);
        let tagsOption = [];
        tags.forEach(tag => {
            tagsOption.push({
                value: tag.id,
                label: tag.tagName,
            })
        })
        setTagOption(tagsOption);
        let tagShowOptions = [];
        if (existedJob) {
            existedJob.tags.forEach(tag => tagShowOptions.push({value: tag.id, label: tag.tagName}));
        }
        console.log(tagShowOptions);
        setTagShowOptions(tagShowOptions);
        let tagChosing =[];
        tagShowOptions.forEach(tag =>{
            tagChosing.push({ id: tag.value})
        })
        setTagChoosing(tagChosing);
    }, [existedJob, tags])

    function chooseHandle(e) {
        setTagChoosing(Array.isArray(e) ? e.map(x => {
            return {
                id: x.value
            }
        }) : []);
        setTagShowOptions(Array.isArray(e) ? e.map(x => x) : []);
    }
    const editJobEvent = async () => {
        const data = {...jobState, tags: tagChoosing};
        await dispatch(editJobById({data: data, id: id}));
        navigate(`/jobs/${id}`);
    }

    return (
        <>
            {existedJob &&
                <Card style={{width: '50rem', margin: "0 auto", padding: "1rem"}}>
                    <h3 style={{color: "blue"}}>Edit Job Advertisement Form</h3>
                    <Form>
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text"
                                          value={jobState.title}
                                          onChange={(e) => {
                                              setJobState({...jobState, [e.target.name]: e.target.value})
                                          }}
                                          name="title"/>
                        </Form.Group>
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>Company Name</Form.Label>
                            <Form.Control type="text"
                                          value={jobState.companyName}
                                          onChange={(e) => {
                                              setJobState({...jobState, [e.target.name]: e.target.value})
                                          }}
                                          name="companyName"/>
                        </Form.Group>
                        
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>City</Form.Label>
                            <Form.Control type="text"
                                          value={jobState.city}
                                          onChange={(e) => {
                                              setJobState({...jobState, [e.target.name]: e.target.value})
                                          }}
                                          name="city"/>
                        </Form.Group>
                        
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>State</Form.Label>
                            <Form.Control type="text"
                                          value={jobState.state}
                                          onChange={(e) => {
                                              setJobState({...jobState, [e.target.name]: e.target.value})
                                          }}
                                          name="state"/>
                        </Form.Group>
                        <Form.Group className="md-6">
                            <Form.Label>Short Description</Form.Label>
                            <TextEditor initText={jobState.shortDescription ? jobState.shortDescription: ""} onChange={ (data) => {
                                setJobState({...jobState, shortDescription: data})
                            }}/>
                        </Form.Group>
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>Description</Form.Label>
                            <TextEditor initText={jobState.description ? jobState.description : ""} onChange={ (data) => {
                                setJobState({...jobState, description: data})
                            }}/>
                        </Form.Group>
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>Benefits</Form.Label>
                            <div className="md-form md-4 pink-textarea active-pink-textarea-2">
              <textarea id="form17" className="md-textarea form-control" rows="3" value={jobState.benefits}
                        onChange={(e) => {
                            setJobState({...jobState, [e.target.name]: e.target.value})
                        }}
                        name="benefits"></textarea>
                            </div>
                        </Form.Group>
                        <Form.Group className="md-6" controlId="formBasicEmail">
                            <Form.Label>Tag</Form.Label>
                            <Select isMulti
                                    value={tagShowOptions}
                                    options={tagOption} onChange={(e) => chooseHandle(e)}>
                            </Select>
                        </Form.Group>
                        <Button variant="primary" onClick={editJobEvent} style={{width: "rem"}}>
                            Save
                        </Button>
                    </Form>
                </Card>
            }
        </>
    )
};

export default EditJob;