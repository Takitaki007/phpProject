import React from "react";
import { EllipsisVertical } from "lucide-react";

// Helper function to format the date
const formatDate = (dateString) => {
  const date = new Date(dateString);
  return date.toLocaleDateString("en-US", {
    year: "numeric",
    month: "long",
    day: "numeric",
  });
};

// Helper function to calculate days left
const calculateDaysLeft = (dueDate) => {
  const today = new Date();
  const due = new Date(dueDate);
  const timeDiff = due - today;
  const daysLeft = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
  return daysLeft > 0 ? `${daysLeft} day${daysLeft > 1 ? "s" : ""} left` : "Due date passed";
};

// Helper function to determine the progress bar color
const getProgressBarColor = (progress) => {
  switch (progress) {
    case "100":
      return "bg-custom-sky-blue"; 
    case "75":
      return "bg-custom-carrot"; 
    case "50":
      return "bg-custom-yellow"; 
    case "25":
      return "bg-custom-pink"; 
    default:
      return "bg-gray-300";
  }
};

export default function CardComponent({ project }) {
  const formattedDate = formatDate(project.dueDate); // Format the date
  const daysLeft = calculateDaysLeft(project.dueDate);
  const progressBarColor = getProgressBarColor(project.progress);

  return (
    <div className="w-full p-10 bg-white rounded-2xl shadow-sm relative">
      {/* Top Section */}
      <div className="flex justify-between mb-5">
        {/* Due Date */}
        <p className="text-custom-sky-blue font-medium">{formattedDate}</p> {/* Use formattedDate here */}
        {/* Three-dot menu */}
        <EllipsisVertical size={20} className="text-gray-500" />
      </div>

      {/* Project Name */}
      <h5 className="capitalize mb-2 text-2xl font-semibold tracking-tight text-gray-900">
        {project.projectName}
      </h5>

      {/* Description */}
      <p className="line-clamp-2 mb-3 font-normal text-gray-400">{project.description}</p>

      {/* Progress Section */}
      <div className="w-full flex justify-between font-medium mb-1">
        <p>Progress</p>
        <p>{project.progress}%</p>
      </div>
      <div className="relative mb-5 w-full bg-gray-200 rounded-full h-2.5">
        <div
          className={`${progressBarColor} h-2.5 rounded-full`}
          style={{ width: `${project.progress}%` }}
        ></div>
      </div>

      {/* Days Left Button */}
      <div className="flex justify-end">
        <p className="font-medium bg-gray-100 py-1.5 px-4 rounded-lg text-center">
          {daysLeft}
        </p>
      </div>
    </div>
  );
}