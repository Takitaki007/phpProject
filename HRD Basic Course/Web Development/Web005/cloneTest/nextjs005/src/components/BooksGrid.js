// src/components/BooksGrid.jsx
"use client";

import Link from "next/link";
import BookCard from "./BookCard";

export default function BooksGrid({ books }) {
  return (
<div className="grid grid-cols-3 gap-10">
  {books.map((book) => (
    <Link href={`/books/${book.id}`} key={book.id}>
      <div className="flex justify-around">
        <BookCard book={book} />
      </div>
    </Link>
  ))}
</div>

  );
}