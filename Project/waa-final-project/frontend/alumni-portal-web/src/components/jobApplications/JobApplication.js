import {useParams} from "react-router-dom";
import {Card, Col, Container, Row, Spinner} from "react-bootstrap";
import {useEffect, useState} from "react";
import httpClient from "../../services/HttpClient";

import LoadingState from "../../reducers/LoadingState";


const loadingSpinner = () => {
    return <div className="d-flex justify-content-center mt-lg-4">
        <Spinner animation="border" variant="primary"  />
    </div>
};

const errorView = () => {
    return <div className="d-flex justify-content-center mt-lg-4">
        <p className="alert-danger">
            Could not load job application
        </p>
    </div>
};


const showAttachments = (attachments = []) => {
    if (attachments.length > 0) {
        return <Col md={{span: 2, offset: 1}}>
            <Card>
                <Card.Header>Attachment: </Card.Header>
                <Card.Body>
                    CV.pdf
                </Card.Body>
            </Card>
        </Col>
    }
    return "";
};

const applicantDetail = (application) => {
    const { applicant, attachments } = application;

    return <Container className="mt-lg-4 pt-4">

        <Row>
            <Col md={4}>
            </Col>
            <Col md={4} >
                <Card>
                    <Card.Header><h4>Applicant detail:</h4></Card.Header>
                    <Card.Body>

                        <Row>
                            <Col md={{span: 4}} className="font-weight-bold">
                                Name:
                            </Col>
                            <Col md={{span: 8}}
                            >
                                {applicant.firstName} {applicant.lastName}
                            </Col>
                        </Row>

                        <Row>
                            <Col md={{span: 4}} className="font-weight-bold">
                                Email:
                            </Col>
                            <Col md={{span: 8}}
                            >
                                {applicant.email}
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{span: 4}} className="font-weight-bold">
                                GPA:
                            </Col>
                            <Col md={{span: 8}}
                            >
                                {applicant.gpa}
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{span: 4}} className="font-weight-bold">
                                Major:
                            </Col>
                            <Col md={{span: 8}}
                            >
                                {applicant.major.name}
                            </Col>
                        </Row>

                        <Row>
                            <Col md={{span: 4}} className="font-weight-bold">
                                City:
                            </Col>
                            <Col md={{span: 8}}
                            >
                                {applicant.city}
                            </Col>
                        </Row>
                        <Row>
                            <Col md={{span: 4}} className="font-weight-bold">
                                State:
                            </Col>
                            <Col md={{span: 8}}
                            >
                                {applicant.state}
                            </Col>
                        </Row>

                    </Card.Body>
                </Card>
            </Col>
            {showAttachments(attachments)}

        </Row>
        <Row>
            <Card className="col-md-6 offset-md-3 mt-4">
                <Card.Header>
                    <h4>Cover letter</h4>
                </Card.Header>
                <Card.Body>
                    <h4>{application.title}</h4>
                    <div>
                        {application.coverLetter}
                    </div>
                </Card.Body>
            </Card>
        </Row>
    </Container>;
}


const JobApplication = () => {
    const params = useParams();
    const id = params.id

    const [detail, setDetail] = useState({
        state: LoadingState.IDLE,
        application: {}
    });

    const fetchJobApplication = async () => {
        try {
            setDetail({ ...detail, state: LoadingState.PENDING });
            const response = await httpClient.get(`/jobApplications/${id}`);

            setDetail({ ...detail, application: response, state: LoadingState.SUCCEEDED });
        } catch {
            setDetail({ ...detail, state: LoadingState.FAILED});
        }
    }
    useEffect(() => {
        fetchJobApplication();
    }, [id]);

    if (detail.state === LoadingState.PENDING || detail.state === LoadingState.IDLE) {
        return loadingSpinner();
    }

    if (detail.state === LoadingState.FAILED) {
        return errorView();
    }

    return applicantDetail(detail.application);

};

export default JobApplication;
