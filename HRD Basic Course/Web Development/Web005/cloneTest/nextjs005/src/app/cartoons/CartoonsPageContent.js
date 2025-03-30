// src/app/cartoons/CartoonsPageContent.jsx
"use client";

import { useState, useEffect } from "react";
import SearchBar from "../../components/SearchBar";
import CartoonFilterSection from "../../components/CartoonFilterSection";
import CartoonsGrid from "../../components/CartoonsGrid";

export default function CartoonsPageContent({ initialCartoons }) {
  const [searchTerm, setSearchTerm] = useState("");
  const [cartoons, setCartoons] = useState(initialCartoons);

  useEffect(() => {
    if (!searchTerm.trim()) {
      setCartoons(initialCartoons);
      return;
    }

    // Split search term into keywords
    const keywords = searchTerm.toLowerCase().split(/\s+/).filter(Boolean);

    const filteredCartoons = initialCartoons.filter((cartoon) => {
      const title = cartoon.title.toLowerCase();
      const genre = cartoon.genre.toLowerCase();

      // Check if any keyword matches title or genre
      return keywords.every((keyword) =>
        title.includes(keyword) || genre.includes(keyword)
      );
    });

    setCartoons(filteredCartoons);
  }, [searchTerm, initialCartoons]);

  return (
    <>
      <SearchBar onSearch={setSearchTerm} />
      <CartoonFilterSection />
      {cartoons.length === 0 ? (
        <p className="text-center text-gray-500">No results found.</p>
      ) : (
        <CartoonsGrid cartoons={cartoons} />
      )}
    </>
  );
}