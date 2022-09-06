import * as React from 'react';
import {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {fetchTags} from "../../reducers/TagsSlice";
import {addJob} from "../../reducers/JobsSlice";
import Form from 'react-bootstrap/Form';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Select from 'react-select'
import { TextEditor } from './TextEditor';

const NewJob = () => {
    const tags = useSelector(state => state.tags.tags);
    const dispatch = useDispatch();
    const [newJobState, setNewJobState] = useState({
        tags: [],
        shortDescription: "",
        description: "",
        benefits: "",
        state: "",
        companyName: "",
        city: "",
        title: "",
        file: [],
    })

    useEffect(() => {
        dispatch(fetchTags())
    }, [])

    useEffect(() => {
        init(tags);
    }, [tags])

    function addNewJobEvent() {
        const data = {...newJobState, tags: tagChoosing}
        dispatch(addJob({data: data}));
        setNewJobState({
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
        setTagChoosing([]);
        setTagShowOptions([]);
    }

    const [tagChoosing, setTagChoosing] = useState([]);
    const [tagOption, setTagOption] = useState([]);
    const [tagShowOptions, setTagShowOptions] = useState([]);

    function init(tags) {
        let tagsOption = [];
        tags.forEach(tag => {
            tagsOption.push({
                value: tag.id,
                label: tag.tagName,
            })
        })
        setTagOption(tagsOption);
    }

    function chooseHandle(e) {
        setTagChoosing(Array.isArray(e) ? e.map(x => {
            return {
                id: x.value
            }
        }) : []);
        setTagShowOptions(Array.isArray(e) ? e.map(x => x) : []);
    }

    return (
        < div>
            <Card style={{width: '50rem', margin: "0 auto", padding: "1rem"}}>
                <h3 style={{color: "blue"}}>Job Advertisement Form</h3>
                <Form>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Title</Form.Label>
                        <Form.Control type="text"
                                      value={newJobState.title}
                                      onChange={(e) => {
                                          setNewJobState({...newJobState, [e.target.name]: e.target.value})
                                      }}
                                      name="title"/>
                    </Form.Group>
                    
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Company Name</Form.Label>
                        <Form.Control type="text"
                                      value={newJobState.companyName}
                                      onChange={(e) => {
                                          setNewJobState({...newJobState, [e.target.name]: e.target.value})
                                      }}
                                      name="companyName"/>
                    </Form.Group>

                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>City</Form.Label>
                        <Form.Control type="text"
                                      value={newJobState.city}
                                      onChange={(e) => {
                                          setNewJobState({...newJobState, [e.target.name]: e.target.value})
                                      }}
                                      name="city"/>
                    </Form.Group>

                    

                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>State</Form.Label>
                        <Form.Control type="text"
                                      value={newJobState.state}
                                      onChange={(e) => {
                                          setNewJobState({...newJobState, [e.target.name]: e.target.value})
                                      }}
                                      name="state"/>
                    </Form.Group>

                    <Form.Group className="md-6">
                        <Form.Label>Short Description</Form.Label>
                        <TextEditor initText={newJobState.shortDescription} onChange={ (data) => {
                            setNewJobState({...newJobState, shortDescription: data})
                        }}/>
                    </Form.Group>
                    <Form.Group className="md-6">
                        <Form.Label>Description</Form.Label>
                        <TextEditor initText={newJobState.description} onChange={ (data) => {
                            setNewJobState({...newJobState, description: data})
                        }}/>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Benefits</Form.Label>
                        <div className="md-form md-4 pink-textarea active-pink-textarea-2">
              <textarea id="form17" className="md-textarea form-control" rows="3" value={newJobState.benefits}
                        onChange={(e) => {
                            setNewJobState({...newJobState, [e.target.name]: e.target.value})
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
                </Form>
                <Button variant="primary" onClick={addNewJobEvent}>
                    Add new job
                </Button>
            </Card>
        </div>
    )
};

export default NewJob;