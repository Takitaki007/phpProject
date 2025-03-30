import { Plus } from "lucide-react";
import React, { useState } from "react";

export default function AddNewProjectComponent({ onAddProject }) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    projectName: "",
    dueDate: "",
    progress: "",
    description: "",
  });
  const [errors, setErrors] = useState({});

  // Handle Input Change
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Validate Form
  const validateForm = () => {
    const newErrors = {};
    const today = new Date().toISOString().split("T")[0]; // Get today's date (YYYY-MM-DD)

    if (!formData.projectName.trim())
      newErrors.projectName = "Project Name is required";
    if (!formData.dueDate) newErrors.dueDate = "Due Date is required";
    else if (formData.dueDate < today)
      newErrors.dueDate = "Date cannot be in the past";
    if (!formData.progress) newErrors.progress = "Progress is required";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle Form Submission
  const handleSubmit = (e) => {
    e.preventDefault();
    if (!validateForm()) return; // Stop if validation fails

    // Set Default Description if Empty
    const finalDescription = formData.description.trim()
      ? formData.description
      : "lorem ipsum dolor sit amet consectetur adipiscing elit amet consecteturadipiscing elit. Accusamus  ..... ";

    // Create new project object
    const newProject = {
      projectName: formData.projectName,
      dueDate: formData.dueDate,
      progress: formData.progress,
      description: finalDescription,
    };

    // Pass the new project to the parent component
    onAddProject(newProject);

    // Reset Form (but keep modal open)
    setFormData({
      projectName: "",
      dueDate: "",
      progress: "",
      description: "",
    });
    setErrors({});
    setIsModalOpen(false); // Close the modal
  };

  return (
    <div>
      {/* Button to Open Modal */}
      <button
        onClick={() => setIsModalOpen(true)}
        className="text-white bg-custom-sky-blue hover:bg-custom-sky-blue-500 focus:ring-3 focus:outline-none focus:ring-custom-sky-blue-500 font-medium rounded-lg text-sm px-3 py-2.5 text-center flex items-center gap-2"
        type="button"
      >
        <Plus size={22} /> <span className="text-base">New Project</span>
      </button>

      {/* Modal */}
      {isModalOpen && (
       <div className="fixed top-0 left-0 w-full h-full flex justify-center items-center backdrop-blur-sm bg-opacity-50 z-50">
          <div className="bg-white rounded-2xl shadow-lg p-6 max-w-md w-full">
            <div className="flex items-center justify-between border-b pb-3">
              <h3 className="text-lg font-semibold text-gray-900">
                Create New Project
              </h3>
              <button
                onClick={() => setIsModalOpen(false)}
                className="text-gray-400 hover:text-gray-900"
              >
                ✕
              </button>
            </div>

            {/* Form */}
            <form className="mt-4" onSubmit={handleSubmit}>
              {/* Project Name */}
              <div className="mb-4">
                <label
                  htmlFor="projectName"
                  className="block text-sm font-medium text-gray-900"
                >
                  Project Name
                </label>
                <input
                  type="text"
                  id="projectName"
                  name="projectName"
                  value={formData.projectName}
                  onChange={handleChange}
                  className="w-full mt-1 p-2 border rounded-lg"
                  placeholder="Type Project Name"
                />
                {errors.projectName && (
                  <p className="text-red-500 text-sm">{errors.projectName}</p>
                )}
              </div>

              {/* Due Date */}
              <div className="mb-4">
                <label
                  htmlFor="dueDate"
                  className="block text-sm font-medium text-gray-900"
                >
                  Due Date
                </label>
                <input
                  type="date"
                  id="dueDate"
                  name="dueDate"
                  value={formData.dueDate}
                  onChange={handleChange}
                  className="w-full mt-1 p-2 border rounded-lg"
                />
                {errors.dueDate && (
                  <p className="text-red-500 text-sm">{errors.dueDate}</p>
                )}
              </div>

              {/* Progress */}
              <div className="mb-4">
                <label
                  htmlFor="progress"
                  className="block text-sm font-medium text-gray-900"
                >
                  Progress
                </label>
                <select
                  id="progress"
                  name="progress"
                  value={formData.progress}
                  onChange={handleChange}
                  className="w-full mt-1 p-2 border rounded-lg"
                >
                  <option value="">Select Progress</option>
                  <option value="100">100%</option>
                  <option value="75">75%</option>
                  <option value="50">50%</option>
                  <option value="25">25%</option>
                </select>
                {errors.progress && (
                  <p className="text-red-500 text-sm">{errors.progress}</p>
                )}
              </div>

              {/* Project Description */}
              <div className="mb-4">
                <label
                  htmlFor="description"
                  className="block text-sm font-medium text-gray-900"
                >
                  Project Description
                </label>
                <textarea
                  id="description"
                  name="description"
                  rows="4"
                  value={formData.description}
                  onChange={handleChange}
                  className="w-full mt-1 p-2 border rounded-lg"
                  placeholder="Write project description here (optional)"
                ></textarea>
              </div>

              {/* Submit Button */}
              <div className="text-right">
                <button
                  type="submit"
                  className="text-white bg-custom-sky-blue hover:bg-custom-sky-blue-500 font-medium rounded-lg text-sm px-5 py-2.5"
                >
                  Create
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
