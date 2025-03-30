// src/app/books/page.js
import Sidebar from "../../components/Sidebar";
import BooksPageContent from "./BooksPageContent";

async function getBooks() {
  try {
    const res = await fetch("https://nextjs-homework005.vercel.app/api/book", {
      cache: "no-store",
    });
    if (!res.ok) {
      throw new Error("Failed to fetch books");
    }
    const data = await res.json();
    const filteredBooks = data.payload.map((book) => ({
      id: book.id.toString(),
      title: book.book_title,
      image: book.image,
    }));
    return filteredBooks;
  } catch (error) {
    console.error("Error fetching books:", error);
    return [];
  }
}

export default async function Books() {
  const books = await getBooks();

  return (
    <div className="flex min-h-screen bg-gray-100">
      <Sidebar />
      <div className="ml-64 p-8 flex-1">
        <BooksPageContent initialBooks={books} />
      </div>
    </div>
  );
}