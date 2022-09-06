import * as React from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import { getCommentByStudentId } from '../../reducers/CommentsSlice';


const Comments = () => {
    const comments = useSelector(state => state.comments.comments)
    const dispatch = useDispatch();
    const params = useParams();
  
    useEffect(() => {
        dispatch(getCommentByStudentId(params.studentId));
    }, []);

    return (
        
        <div className="container-xxl bg-white p-0">
            

            <Table striped bordered hover style={{margin: "35px"}}>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Comment</th>
                        <th>StudentId</th>
                        
                    </tr>
                </thead>
                <tbody>

                    {comments.map((row) => (
                        <tr key={row.id}>
                            <td>{row.id}</td>
                            <td>{row.comment}</td>
                            <td>{params.studentId}</td>
                            
                        </tr>

                    ))}
                </tbody>
            </Table>
        </div>


    );
};

export default Comments;