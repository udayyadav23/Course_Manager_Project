import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./addcourse.css";

const AddCourse = ({ onAdd }) => {
  const [showForm, setShowForm] = useState(false);
  const [courseName, setCourseName] = useState("");
  const [professor, setProfessor] = useState("");
  const [courseCode, setCourseCode] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (courseName && professor && courseCode) {
      const newCourse = { courseName, professor, courseCode };

      try {
        await axios.post("http://localhost:8082/courses", newCourse);
        onAdd?.({ id: Date.now(), ...newCourse });
        setCourseName("");
        setProfessor("");
        setCourseCode("");
        setShowForm(false);
        navigate("/courses");
      } catch (error) {
        console.error("Error adding course:", error);
        alert("Failed to add course. Please try again.");
      }
    }
  };

  return (
    <>
      <button onClick={() => setShowForm(true)} className="add-button">
        Add
      </button>

      {showForm && (
        <div className="overlay">
          <form onSubmit={handleSubmit} className="form-container">
            <h2 className="form-title">Add Course</h2>
            <input
              type="text"
              placeholder="Course Name"
              value={courseName}
              onChange={(e) => setCourseName(e.target.value)}
              className="form-input"
            />
            <input
              type="text"
              placeholder="Professor"
              value={professor}
              onChange={(e) => setProfessor(e.target.value)}
              className="form-input"
            />
            <input
              type="text"
              placeholder="Course Code"
              value={courseCode}
              onChange={(e) => setCourseCode(e.target.value)}
              className="form-input"
            />
            <div className="form-buttons">
              <button type="submit" className="form-submit">
                Add
              </button>
              <button
                type="button"
                onClick={() => setShowForm(false)}
                className="form-cancel"
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      )}
    </>
  );
};

export default AddCourse;
