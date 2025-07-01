// ExtraInfo.jsx
import React from "react";
import { useLocation } from "react-router-dom";
// import "./extrainfo.css";

const ExtraInfo = () => {
  const location = useLocation();
  const course = location.state?.course;

  return (
    <div className="info-container">
      <h1>📌 Extra Info - {course?.course}</h1>
      <p className="info-text">Here you'll find tips, tricks, references, and additional material related to this course.</p>
    </div>
  );
};

export default ExtraInfo;
