import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {useState} from "react";
import httpClient from "../../services/HttpClient";
import {useNavigate, useParams} from "react-router-dom";

const ApplyJob = () => {
    const navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [coverLetter, setCoverLetter] = useState("");
    const [file, setFile] = useState("");

    const params = useParams();
    const jobId = params.id;

    const submitForm = async () => {
        try {
             await httpClient.post(`/jobAdvertisements/${jobId}/apply`,
                {title, coverLetter, file},
                {'Content-Type': 'multipart/form-data'});
        } catch (ex) {
            console.error("Error occurred ", ex);
        }
        navigate("/jobs", {replace: true});

    };

    return (
        <Form style={{width: '30rem', margin: '0 auto' }} >
            <h2 className="mt-5">Apply for this job</h2>
            <Form.Group className="mb-3 mt-5" controlId="exampleForm.ControlInput1">
                <Form.Label>Title</Form.Label>
                <Form.Control type="text"
                              value={title}
                              onChange={(event) => setTitle(event.target.value)}
                              placeholder="Application for the job" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Label>Cover Letter</Form.Label>
                <Form.Control as="textarea"
                              value = {coverLetter}
                              onChange={(event) => setCoverLetter(event.target.value)}
                              placeholder="Cover letter"
                              rows={3} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <label htmlFor="formFileMultiple" className="form-label">Upload CV</label>
                <input className="form-control"
                       type="file"
                       name={file}
                       onChange={(event) => {
                           if(event.target.files.length > 0) {
                               const file = event.target.files[0];
                               const reader = new FileReader();
                               reader.onload = () => {
                                 setFile(file);
                               };

                               if (file) {
                                   reader.readAsDataURL(file);
                               }
                           }
                       }}
                       id="formFileMultiple" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Button onClick={submitForm}>Submit</Button>
            </Form.Group>

        </Form>);
};

export default ApplyJob;