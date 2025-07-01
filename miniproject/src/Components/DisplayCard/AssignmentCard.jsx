import React from "react";
import "./AssignmentCard.css";

const AssignmentCard = ({ imageUrl }) => {
  return (
    <div className="assignment-card">
      <img src={imageUrl} alt="Assignment" className="assignment-card-img" />
    </div>
  );
};

export default AssignmentCard;
