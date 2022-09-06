// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getMessaging, getToken, onMessage } from "firebase/messaging";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAu2JVEU2rf8xmn6vv1JLWjiDBawBPl2cg",
  authDomain: "waa-project-fd157.firebaseapp.com",
  projectId: "waa-project-fd157",
  storageBucket: "waa-project-fd157.appspot.com",
  messagingSenderId: "568803811423",
  appId: "1:568803811423:web:861ccb9a36e4dd48127bea",
  measurementId: "G-TELWKCGFM5"
};

// Initialize Firebase
const firebaseApp = initializeApp(firebaseConfig);
const analytics = getAnalytics(firebaseApp);
const messaging = getMessaging(firebaseApp);

export const requestForToken = (onTokenReady) => {
  return getToken(messaging, { vapidKey: `BH1xU2PLksYYs0-OmI22McfS5zZ4KCwkb66GfIEyGraMuRAkfcLAl11zj9bpdsWGUoBHpOAPRR4A5V4l9041svs` })
    .then((currentToken) => {
      if (currentToken) {
        // console.log('current token for client: ', currentToken);
        // Perform any other neccessary action with the token
        onTokenReady(currentToken);
        console.log('Register notification token');
      } else {
        // Show permission request UI
        console.log('No registration token available. Request permission to generate one.');
      }
    })
    .catch((err) => {
      console.log('An error occurred while retrieving token. ', err);
    });
};

// Handle incoming messages. Called when:
// - a message is received while the app has focus
// - the user clicks on an app notification created by a service worker `messaging.onBackgroundMessage` handler.
export const onMessageListener = () =>
  new Promise((resolve) => {    
    onMessage(messaging, (payload) => {
      resolve(payload);
    });
  });