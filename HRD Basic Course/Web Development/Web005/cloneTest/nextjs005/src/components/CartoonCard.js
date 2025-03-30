// src/components/CartoonCard.jsx
export default function CartoonCard({ cartoon }) {
  return (
    <div className="bg-white transform hover:scale-105 duration-300 transition-transform rounded-lg shadow-md overflow-hidden w-[300px]">
      <img
        src={cartoon.image || "/placeholder.jpg"}
        alt={cartoon.title}
        className="w-full h-64 object-cover"
      />
      <div className="p-4 bg-gray-50">
        <h3 className="text-xl font-bold text-sky-700 mb-2">{cartoon.title}</h3>
        <button className="mt-4 bg-blue-500 cursor-pointer text-white px-4 py-2 rounded hover:bg-pink-600">
          Watch Now
        </button>
      </div>
    </div>
  );
}