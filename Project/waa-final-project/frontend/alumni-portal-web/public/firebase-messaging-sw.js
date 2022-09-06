
importScripts('https://www.gstatic.com/firebasejs/8.2.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.2.0/firebase-messaging.js');


// Initialize the Firebase app in the service worker by passing the generated config
const firebaseConfig = {
    apiKey: "AIzaSyAu2JVEU2rf8xmn6vv1JLWjiDBawBPl2cg",
    authDomain: "waa-project-fd157.firebaseapp.com",
    projectId: "waa-project-fd157",
    storageBucket: "waa-project-fd157.appspot.com",
    messagingSenderId: "568803811423",
    appId: "1:568803811423:web:861ccb9a36e4dd48127bea",
    measurementId: "G-TELWKCGFM5"
  };


firebase.initializeApp(firebaseConfig);

// Retrieve firebase messaging
const messaging = firebase.messaging();

// Handle incoming messages while the app is not in focus (i.e in the background, hidden behind other tabs, or completely closed).
messaging.onBackgroundMessage(function(payload) {
  console.log('Received background message ', payload);

  const notificationTitle = payload.notification.title;
  const notificationOptions = {
    body: payload.notification.body,
  };

  self.registration.showNotification(notificationTitle,
    notificationOptions);
});