// src/app/cartoons/[id]/page.js
import Link from 'next/link';

async function getCartoonById(id) {
  try {
    const res = await fetch(`https://nextjs-homework005.vercel.app/api/cartoon/${id}`, {
      cache: "no-store", // Disable caching for fresh data
    });
    if (!res.ok) {
      throw new Error("Cartoon not found");
    }
    const data = await res.json();
    // Map API fields to the expected structure
    const cartoon = {
      id: data.payload.id.toString(),
      title: data.payload.ct_title,
      image: data.payload.image,
      year: data.payload.published_year.split('-')[0], // Extract year from "YYYY-MM-DD"
      times: data.payload.view_count.toString(), // Convert to string for consistency
      description: data.payload.ct_description, // Map ct_description to description
    };
    return cartoon;
  } catch (error) {
    console.error("Error fetching cartoon:", error);
    return null;
  }
}

export default async function CartoonDetail({ params }) {
  const { id } = params;
  const cartoon = await getCartoonById(id);

  if (!cartoon) {
    return (
      <div className="min-h-screen bg-gray-100 flex items-center justify-center">
        <p className="text-red-500 text-lg">Cartoon not found.</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 py-8 px-4 sm:px-6 lg:px-8">
      {/* Breadcrumb Navigation */}
      <div className="max-w-6xl mx-auto flex gap-2 font-bold text-gray-600 text-sm mb-6">
        <Link href="/" className="hover:underline flex items-center">
          🏠 Home
        </Link>
        <span className="mx-2">›</span>
        <Link href="/cartoons" className="hover:underline">
          🎬 Old School Cartoons
        </Link>
        <span className="mx-2">›</span>
        <span className="text-red-500">🍿 {cartoon.title}</span>
      </div>

      {/* Cartoon Details */}
      <div className="max-w-6xl mx-auto bg-white rounded-lg shadow-md p-8">
        <div className="flex flex-col md:flex-row gap-8">
          {/* Cartoon Content */}
          <div className="flex-1">
            <h1 className="text-3xl font-bold text-sky-700">{cartoon.title}</h1>
            <p className="text-sm text-gray-500 mt-2">
              Released in {cartoon.year} | Views: {cartoon.times}
            </p>
            <p className="mt-4 text-gray-700 leading-relaxed whitespace-pre-line">
              {cartoon.description}
            </p>
          </div>

          {/* Cartoon Image */}
          <div className="md:w-[0.5/3]">
            <img
              src={cartoon.image}
              alt={cartoon.title}
              className="w-full h-80 object-cover rounded-lg shadow-md"
            />
          </div>
        </div>
      </div>
    </div>
  );
}