import React from 'react';
import { Link } from 'react-router-dom';
import './coursecard.css';

const CourseCard = ({ course, onDelete }) => {
  return (
    <div className="course-card">
      <div className="card-content">
        <div className="card-details">
          <h2 className="course-title">{course.courseName}</h2>
          <p className="course-professor">👨‍🏫 {course.professor}</p>
          <p className="course-code">📘 Code: {course.courseCode}</p>
        </div>
        <div className="button-group">
          <Link
            to={`/course/${course.id}`}
            state={{ course }}
            className="go-button"
          >
            Go 🚀
          </Link>
          <button className="delete-button" onClick={onDelete}>
            Delete 🗑️
          </button>
        </div>
      </div>
    </div>
  );
};

export default CourseCard;
