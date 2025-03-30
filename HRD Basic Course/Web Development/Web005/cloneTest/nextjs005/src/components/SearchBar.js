// src/components/SearchBar.jsx
"use client";

import { useState } from "react";

export default function SearchBar({ onSearch }) {
  const [searchTerm, setSearchTerm] = useState("");

  const handleSearch = (e) => {
    e.preventDefault();
    if (onSearch) {
      onSearch(searchTerm);
    }
  };

  return (
    <form onSubmit={handleSearch} className="mb-6">
      <div className="flex items-center">
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          placeholder="Search anything you want to"
          className=" rounded-lg p-2 w-[950px] bg-white text-gray-700 focus:outline-none focus:ring-2 focus:ring-sky-500"
        />
        <button
          type="submit"
          className="bg-sky-500 w-[100px]  transition-all duration-100  text-white p-2 ml-5 rounded-md cursor-pointer transation-10s hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-sky-500"
        >
          Search
        </button>
      </div>
    </form>
  );
}