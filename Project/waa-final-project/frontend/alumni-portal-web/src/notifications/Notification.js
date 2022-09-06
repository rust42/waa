import React, { useState, useEffect } from 'react'
import { Alert } from 'react-bootstrap';
import toast, { Toaster } from 'react-hot-toast';
import { useDispatch } from 'react-redux';
import { registerNotiInfo } from '../reducers/NotificationInfoSlice';
import { requestForToken, onMessageListener } from './firebase';

export default function Notification() {
    const dispatch = useDispatch();
    const [notification, setNotification] = useState({ title: '', body: '' });

    function ShowNotification() {
        return (
            <Alert variant="success" dismissible>
                <Alert.Heading>{notification?.title}</Alert.Heading>
                <p>{notification?.body}</p>
            </Alert>
        );
    };

    useEffect(() => {
        if (notification?.title) {
            toast(<ShowNotification />);
        }
    }, [notification])

    useEffect(() => {
        requestForToken((token) => {
            dispatch(registerNotiInfo(token))
        });

        onMessageListener()
        .then((payload) => {
            console.log('Received force ground message ', payload);
            setNotification(payload?.notification);
        })
        .catch((err) => console.log('failed: ', err));
    }, [])

    return (
        <Toaster />
    )
}