import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./coursedetails.css";

const CourseDetail = () => {
  const location = useLocation();
  const course = location.state?.course;
  const navigate = useNavigate();

  if (!course) {
    return (
      <div className="course-detail-container">
        <h2>Course not found.</h2>
        <button onClick={() => navigate(-1)} className="back-button">Go Back</button>
      </div>
    );
  }

  return (
    <div className="course-detail-container">
      <div className="course-detail-card">
        <h1 className="detail-title">{course.course}</h1>

        <div className="sections">
          <div className="section-box" onClick={() => navigate(`/assignments/${course.id}`)}>📝 Assignments</div>
          <div className="section-box" onClick={() => navigate(`/projects/${course.id}`)}>💻 Projects</div>
          <div className="section-box" onClick={() => navigate(`/notes/${course.id}`)}>📓 Daily Notes</div>
          <div className="section-box" onClick={() => navigate(`/info/${course.id}`)}>📌 Extra Info</div>
        </div>

        <button onClick={() => navigate("/home")} className="back-button">
          ← Back to Home
        </button>
      </div>
    </div>
  );
};

export default CourseDetail;
