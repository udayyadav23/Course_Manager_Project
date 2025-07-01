import React, { useEffect, useState } from "react";
import AddCourse from "../AddCourse/addcourse";
import CourseCard from "../CourseCard/coursecard";
import axios from "axios";
import "./homepage.css";

const Home = () => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);

  // Fetch list of course IDs, then fetch each course by ID
  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.get("http://localhost:8082/courses"); // List of course summaries or IDs
        const courseList = response.data;

        const coursePromises = courseList.map(async (courseSummary) => {
          const res = await axios.get(`http://localhost:8082/courses/${courseSummary.id}`);
          return res.data;
        });

        const fullCourses = await Promise.all(coursePromises);
        setCourses(fullCourses);
      } catch (error) {
        console.error("Error fetching courses:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchCourses();
  }, []);

  const handleAddCourse = (newCourse) => {
    setCourses((prevCourses) => [...prevCourses, newCourse]);
  };

  const handleDeleteCourse = async (id) => {
  try {
    // Send DELETE request to backend
    await axios.delete(`http://localhost:8082/courses/${id}`);

    // Remove from frontend state
    setCourses((prev) => prev.filter((course) => course.id !== id));
  } catch (error) {
    console.error("Error deleting course:", error);
    alert("Failed to delete course from database.");
  }
};


  return (
    <div className="home-container">
      <header className="home-header">
        <h1 className="home-title">📚 Course Manager</h1>
        <AddCourse onAdd={handleAddCourse} />
      </header>

      <div className="course-list">
        {loading ? (
          <p className="loading-text">Loading courses...</p>
        ) : courses.length === 0 ? (
          <p className="no-course-text">No courses found. Click "Add" to begin!</p>
        ) : (
          courses.map((course) => (
            <CourseCard
              key={course.id}
              course={course}
              onDelete={() => handleDeleteCourse(course.id)}
            />
          ))
        )}
      </div>
    </div>
  );
};

export default Home;
