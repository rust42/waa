import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {fetchTags} from "../../reducers/TagsSlice";
import {createNewHistory} from "../../reducers/JobHistorySlice";
import Form from "react-bootstrap/Form";
import {Card} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import Select from "react-select";

const NewJobExperience = () => {

    const [tagChoosing, setTagChoosing] = useState([]);
    const [tagOption, setTagOption] = useState([]);
    const [tagShowOptions, setTagShowOptions] = useState([]);
    const tags = useSelector(state => state.tags.tags);

    const dispatch = useDispatch();

    const [newJobHistoryState, setNewJobHistoryState] = useState({
        tags: [],
        company: "",
        reasonToLeave: "",
        title:"",
        description:"",
        comments: "",
        startDate: "",
        endDate: "",
    })
    useEffect(() => {
        dispatch(fetchTags())
    }, [])

    useEffect(() => {
        let tagsOption = [];
            tags.forEach(tag => {
                tagsOption.push({
                    value: tag.id,
                    label: tag.tagName,
                })
            })
            setTagOption(tagsOption);
    }, [tags])

    function chooseHandle(e) {
        setTagChoosing(Array.isArray(e) ? e.map(x => {
            return {
                id: x.value
            }
        }) : []);
        setTagShowOptions(Array.isArray(e) ? e.map(x => x) : []);
    }

    function addNewJobHistoryEvent() {
        const data = {...newJobHistoryState, tags: tagChoosing};
        dispatch(createNewHistory({data: data}));
        setNewJobHistoryState({
            tags: [],
            company: "",
            reasonToLeave: "",
            description:"",
            comments: "",
            startDate: "",
            endDate: "",
            title:""
        });
        setTagShowOptions([]);
        setTagChoosing([]);
    }

    return (
        <>
            <Card style={{width: '30rem', margin: "0 auto", padding: "1rem"}}>
                <h3 style={{color: "blue"}}>Job Experience Form</h3>
                <Form>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Title</Form.Label>
                        <Form.Control type="text"
                                      value={newJobHistoryState.title}
                                      onChange={(e) => {
                                          setNewJobHistoryState({
                                              ...newJobHistoryState,
                                              [e.target.name]: e.target.value
                                          })
                                      }}
                                      name="title"/>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Company</Form.Label>
                        <Form.Control type="text"
                                      value={newJobHistoryState.company}
                                      onChange={(e) => {
                                          setNewJobHistoryState({
                                              ...newJobHistoryState,
                                              [e.target.name]: e.target.value
                                          })
                                      }}
                                      name="company"/>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Tag</Form.Label>
                        <Select isMulti
                                value={tagShowOptions}
                                options={tagOption} onChange={(e) => chooseHandle(e)}>
                        </Select>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Description</Form.Label>
                        <div className="md-form md-4 pink-textarea active-pink-textarea-2">
              <textarea id="form17" className="md-textarea form-control" rows="3"
                        value={newJobHistoryState.description}
                        onChange={(e) => {
                            setNewJobHistoryState({...newJobHistoryState, [e.target.name]: e.target.value})
                        }}
                        name="description"></textarea>
                        </div>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Comments</Form.Label>
                        <div className="md-form md-4 pink-textarea active-pink-textarea-2">
              <textarea id="form17" className="md-textarea form-control" rows="3" value={newJobHistoryState.comments}
                        onChange={(e) => {
                            setNewJobHistoryState({...newJobHistoryState, [e.target.name]: e.target.value})
                        }}
                        name="comments"></textarea>
                        </div>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>Start date</Form.Label>
                        <Form.Control type="date"
                                      value={newJobHistoryState.startDate}
                                      onChange={(e) => {
                                          setNewJobHistoryState({
                                              ...newJobHistoryState,
                                              [e.target.name]: e.target.value
                                          })
                                      }}
                                      name="startDate"/>
                    </Form.Group>
                    <Form.Group className="md-6" controlId="formBasicEmail">
                        <Form.Label>End date</Form.Label>
                        <Form.Control type="date"
                                      value={newJobHistoryState.endDate}
                                      onChange={(e) => {
                                          setNewJobHistoryState({
                                              ...newJobHistoryState,
                                              [e.target.name]: e.target.value
                                          })
                                      }}
                                      name="endDate"/>
                    </Form.Group>
                    <Button variant="primary" onClick={addNewJobHistoryEvent}>
                        Add
                    </Button>
                </Form>
            </Card>
        </>
    )
};

export default NewJobExperience;