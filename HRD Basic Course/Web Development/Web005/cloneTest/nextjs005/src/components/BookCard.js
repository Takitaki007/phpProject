// src/components/BookCard.jsx
export default function BookCard({ book }) {
  return (
    <div className="bg-white transform hover:scale-105 duration-300 transition-transform rounded-lg shadow-md overflow-hidden w-[300px]">
      <img
        src={book.image}
        alt={book.title}
        className="w-full h-64 object-cover"
      />
      <div className="p-4 bg-gray-50">
        <h3 className="text-xl font-bold text-teal-900 mb-2">{book.title}</h3>
        <button className="mt-4 bg-blue-500 duration-200  text-white px-4 cursor-pointer py-2 rounded hover:bg-pink-600">
          Read Full Article
        </button>
      </div>
    </div>
  );
}