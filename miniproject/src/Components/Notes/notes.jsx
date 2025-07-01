// Notes.jsx
import React from "react";
import { useLocation } from "react-router-dom";
// import "./notes.css";

const Notes = () => {
  const location = useLocation();
  const course = location.state?.course;

  return (
    <div className="notes-container">
      <h1>📓 Daily Notes - {course?.course}</h1>
      <ul className="notes-list">
        <li>Week 1 - Basics of {course?.course}</li>
        <li>Week 2 - Advanced Concepts</li>
        <li>Week 3 - Practice Problems</li>
      </ul>
    </div>
  );
};

export default Notes;
