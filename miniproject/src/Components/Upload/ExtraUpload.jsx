import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import AssignmentCard from "../DisplayCard/AssignmentCard";
import "./assignments.css";

const ExtraUpload = () => {
  const { courseId } = useParams();
  const [courseName, setCourseName] = useState("");
  const [extraIds, setExtraIds] = useState([]);
  const [file, setFile] = useState(null);
  const [status, setStatus] = useState("");

  useEffect(() => {
    const fetchCourse = async () => {
      try {
        const res = await axios.get(`http://localhost:8082/courses/${courseId}`);
        setCourseName(res.data.courseName);
        setExtraIds(res.data.extras);
      } catch (err) {
        console.error("Error fetching course:", err);
        setStatus("Failed to load course data.");
      }
    };
    fetchCourse();
  }, [courseId]);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setStatus("");
  };

  const handleUpload = async () => {
    if (!file || !courseName) {
      setStatus("File or course name missing.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      const res = await axios.post(
        `http://localhost:8082/uploads/extras/${courseName}`,
        formData,
        { headers: { "Content-Type": "multipart/form-data" } }
      );

      if (res.status === 202) {
        setStatus("✅ Uploaded successfully!");
        const courseRes = await axios.get(`http://localhost:8082/courses/${courseId}`);
        setExtraIds(courseRes.data.extras);
        setFile(null);
      } else {
        setStatus("⚠️ Upload failed.");
      }
    } catch (err) {
      console.error("Upload failed:", err);
      setStatus("❌ Upload failed.");
    }
  };

  return (
    <div className="assignment-upload-container">
      <div className="upload-card">
        <h2>📤 Upload Extra Info</h2>
        <p><strong>Course ID:</strong> {courseId}</p>
        <p><strong>Course Name:</strong> {courseName || "Loading..."}</p>

        <label htmlFor="fileInput" className="custom-file-input">
          {file ? file.name : "Choose a file"}
          <input type="file" id="fileInput" onChange={handleFileChange} />
        </label>

        <button onClick={handleUpload} className="upload-btn">Add Extra Info</button>
        {status && <p className="status-message">{status}</p>}
      </div>

      <div className="assignment-gallery">
        <h3>📚 Previous Extras</h3>
        {extraIds.length === 0 ? (
          <p className="no-assignments">No extra info uploaded yet.</p>
        ) : (
          <div className="gallery-grid">
            {extraIds.map((id) => (
              <AssignmentCard key={id} imageUrl={`http://localhost:8082/uploads/${id}`} />
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default ExtraUpload;
