import '../css/App.css';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import HttpClient from '../services/HttpClient';
import keycloak from "../keycloak"
import Router from "../Router";
import { useState } from 'react';
import Loading from './Loading';


function App() {
  const [isLoading, setIsLoading ] = useState(true)

  const eventLogger = (event, error) => {
    console.log('onKeycloakEvent', event, error)
    if(event === 'onReady'){
      setIsLoading(false)
    }
  }

  const tokenLogger = (tokens) => {
    HttpClient.setAuthenticationCredentials(tokens);
    setIsLoading(false)
  }
  return (
    <div>
      <ReactKeycloakProvider
        authClient={keycloak}
        onEvent={eventLogger}
        onTokens={tokenLogger}
      >
        { isLoading ?
          <div className="d-flex align-items-center justify-content-sm-center" style={{height: '20rem', width: '100%'}}>
            <div className="spinner-grow text-primary m-lg-2" role="status" />
            <div className="spinner-grow text-primary m-lg-2" role="status" />
            <div className="spinner-grow text-primary m-lg-2" role="status" />
            <div className="spinner-grow text-primary m-lg-2" role="status" />
            <div className="spinner-grow text-primary m-lg-2" role="status" />
          </div>
            : <Router />
        }
      </ReactKeycloakProvider>
    </div>
  );
}

export default App;
