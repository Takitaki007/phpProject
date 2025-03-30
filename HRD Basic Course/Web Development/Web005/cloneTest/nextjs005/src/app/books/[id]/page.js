// src/app/books/[id]/page.js
import Link from "next/link";

async function getBookById(id) {
  try {
    const res = await fetch(`https://nextjs-homework005.vercel.app/api/book/${id}`, {
      cache: "no-store", // Disable caching for fresh data
    });
    if (!res.ok) {
      throw new Error("Book not found");
    }
    const data = await res.json();
    return data.payload; // API returns an object with a payload array, but we expect a single book
  } catch (error) {
    console.error("Error fetching book:", error);
    return null;
  }
}

export default async function BookDetail({ params }) {
  const book = await getBookById(params.id);

  if (!book) {
    return (
      <div className="min-h-screen bg-gray-100 flex items-center justify-center">
        <p className="text-red-500 text-lg">Book not found.</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 py-8 px-4 sm:px-6 lg:px-8">
      <div className="max-w-6xl mx-auto flex gap-2 font-bold text-gray-600 text-sm mb-6">
        <Link href="/" className="hover:underline flex items-center">🏠 Home</Link>
        <span className="mx-2">›</span>
        <Link href="/books" className="hover:underline">📚 Book Category</Link>
        <span className="mx-2">›</span>
        <span className="text-red-500">📖 {book.book_title}</span>
      </div>
      <div className="max-w-6xl mx-auto bg-white rounded-lg shadow-md p-8">
        <div className="flex flex-col md:flex-row gap-8">
          <div className="flex-1">
            <h1 className="text-3xl font-bold text-teal-900">{book.book_title}</h1>
            <p className="text-sm text-gray-500 mt-2">by {book.book_author}</p>
            <p className="mt-4 text-gray-700 leading-relaxed whitespace-pre-line">
              {book.description}
            </p>
          </div>
          <div className="md:w-[0.5/3]">
            <img
              src={book.image}
              alt={book.book_title}
              className="w-full h-80 object-cover rounded-lg"
            />
          </div>
        </div>
      </div>
    </div>
  );
}