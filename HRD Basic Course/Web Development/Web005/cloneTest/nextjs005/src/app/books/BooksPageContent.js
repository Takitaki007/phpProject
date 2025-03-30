// src/app/books/BooksPageContent.jsx
"use client";

import { useState, useEffect } from "react";
import SearchBar from "../../components/SearchBar";
import BooksGrid from "../../components/BooksGrid";

export default function BooksPageContent({ initialBooks }) {
  const [searchTerm, setSearchTerm] = useState("");
  const [books, setBooks] = useState(initialBooks);

  useEffect(() => {
    const filteredBooks = initialBooks.filter((book) =>
      book.title.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setBooks(filteredBooks);
  }, [searchTerm, initialBooks]);

  return (
    <>
      <SearchBar onSearch={setSearchTerm} />
      <div className="flex flex-wrap justify-between items-center mb-20">
        <button className="bg-white font-bold text-sky-700 rounded-md p-2">
          All Books
        </button>
        <select className="border rounded p-2 text-gray-600">
          <option>Filter By Category</option>
          <option>Fiction</option>
          <option>Self-Help</option>
          <option>Memoir</option>
        </select>
      </div>
      <div className="mb-10">
        <hr />
      </div>
      <BooksGrid books={books} />
    </>
  );
}