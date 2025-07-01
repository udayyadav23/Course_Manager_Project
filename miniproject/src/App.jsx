
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Signup from './Components/SignUp/Signup.jsx'; 
import './App.css'
import Login from './Components/Login/Login.jsx';
import OtpVerification from './Components/Otp/otpVerification.jsx';
import SetCredentials from './Components/SetCredentials/SetCredentials.jsx';
import HomePage from './Components/HomePage/homepage.jsx';
import CourseDetail from './Components/CourseDetails/coursedetails.jsx';
import Assignments from './Components/Assignments/assignments.jsx';
import Projects from './Components/Upload/ProjectUpload.jsx';
import Notes from './Components/Upload/NotesUpload.jsx';
import ExtraInfo from './Components/Upload/ExtraUpload.jsx';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/otp" element={<OtpVerification />} />
        <Route path="/set-credentials" element={<SetCredentials />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/course/:id" element={<CourseDetail />} />
        <Route path="/assignments/:courseId" element={<Assignments />} />
        <Route path="/projects/:courseId" element={<Projects />} />
        <Route path="/notes/:courseId" element={<Notes />} />
        <Route path="/info/:courseId" element={<ExtraInfo />} />
      </Routes>
    </Router>
  );
}

export default App;