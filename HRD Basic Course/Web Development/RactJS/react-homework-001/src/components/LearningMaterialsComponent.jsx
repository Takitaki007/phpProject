import React, { useState } from "react";
import { Star } from "lucide-react";
import FilterComponent from "./FilterComponent";
import { learningMaterials as initialMaterials } from "../data/learningMaterials";

export default function LearningMaterialsComponent() {
  const [materials, setMaterials] = useState(initialMaterials);

  // Function to toggle favorite
  const toggleFavorite = (id) => {
    setMaterials((prevMaterials) =>
      prevMaterials.map((material) =>
        material.id === id ? { ...material, isFavorite: !material.isFavorite } : material
      )
    );
  };

  // Function to sort materials
  const handleSort = (sortOrder) => {
    const sortedMaterials = [...materials].sort((a, b) => {
      if (sortOrder === "A-Z") return a.title.localeCompare(b.title);
      if (sortOrder === "Z-A") return b.title.localeCompare(a.title);
      return 0;
    });
    setMaterials(sortedMaterials);
  };

  return (
    <div className="bg-white ml-3 drop-shadow-lg rounded-2xl overflow-auto h-[80vh]">
      {/* Filter Component */}
      <FilterComponent onSort={handleSort} />

      {/* Title */}
      <div className="p-4 flex justify-between items-center">
        <h2 className="text-xl font-semibold">Learning Materials</h2>
        <img src="./images/dot.png" alt="three-dot" width={30} height={30} />
      </div>

      {/* Materials List */}
      <div className="space-y-3">
        {materials.map((item) => (
          <div key={item.id} className="bg-light-gray px-4 py-2 flex gap-5 items-center">
            <img
              src={item.image}
              alt={item.title}
              width={50}
              height={50}
              className="rounded-xl"
            />
            <div className="w-full">
              <div className="flex justify-between items-center">
                <p className="text-base font-medium">{item.title}</p>
                <Star
                  size={20}
                  fill={item.isFavorite ? "gold" : "none"}
                  stroke="black"
                  className="cursor-pointer"
                  onClick={() => toggleFavorite(item.id)}
                />
              </div>
              <p className="text-gray-400 text-sm">
                Posted at: {new Date(item.postedAt).toLocaleDateString("en-US", {
                  weekday: "short",
                  month: "long",
                  day: "numeric",
                  year: "numeric",
                })}
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}