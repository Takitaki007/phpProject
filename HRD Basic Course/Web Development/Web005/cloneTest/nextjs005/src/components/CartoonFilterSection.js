// components/CartoonFilterSection.jsx
export default function CartoonFilterSection() {
    return (
      <div className="flex justify-between items-center mb-6">
        <button className="bg-white font-bold text-sky-700 rounded-md p-2">
          Old School Cartoon
        </button>
        <select className="border rounded p-2 text-gray-600">
          <option>Filter By Category</option>
          <option>1990s</option>
          <option>1980s</option>
          <option>1970s</option>
          <option>Action</option>
          <option>Comedy</option>
        </select>
      </div>
    );
  }