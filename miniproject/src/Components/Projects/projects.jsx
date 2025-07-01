// Projects.jsx
import React from "react";
import { useLocation } from "react-router-dom";
// import "./projects.css";

const Projects = () => {
  const location = useLocation();
  const course = location.state?.course;

  return (
    <div className="project-container">
      <h1>💻 Projects - {course?.course}</h1>
      <ul className="project-list">
        <li>Project 1 - Web App UI</li>
        <li>Project 2 - Backend API</li>
        <li>Project 3 - Final Integration</li>
      </ul>
    </div>
  );
};

export default Projects;
