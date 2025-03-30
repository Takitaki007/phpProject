// components/FilterSection.jsx
export default function FilterSection() {
    return (
      <div className="flex justify-between items-center mb-6">
        <button className="bg-white font-bold text-sky-700 rounded-md p-2">
          All Books
        </button>
        <select className="border rounded p-2 text-gray-600">
          <option>Filter By Category</option>
          <option>Fiction</option>
          <option>Non-Fiction</option>
          <option>Memoir</option>
          <option>Self-Help</option>
        </select>
      </div>
    );
  }