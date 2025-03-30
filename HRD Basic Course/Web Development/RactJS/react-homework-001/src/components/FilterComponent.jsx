import React from "react";

export default function FilterComponent({ onSort }) {
  const handleSortChange = (e) => {
    onSort(e.target.value);
  };

  return (
    <form className="mt-4 mx-4 flex justify-between">
      <div className="relative w-full">
        <select
          id="filterLearningMaterials"
          name="filterLearningMaterials"
          onChange={handleSortChange}
          className="text-sm focus:ring-custom-sky-blue focus:border-custom-sky-blue block w-full p-4 focus:outline-none text-gray-400 border-none rounded-xl bg-light-gray"
        >
          <option hidden value="">
            Sort By
          </option>
          <option value="A-Z">A-Z</option>
          <option value="Z-A">Z-A</option>
        </select>
      </div>
    </form>
  );
}