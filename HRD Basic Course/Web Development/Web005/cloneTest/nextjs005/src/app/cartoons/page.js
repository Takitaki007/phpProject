// src/app/cartoons/page.js
import Sidebar from '../../components/Sidebar';
import CartoonsPageContent from './CartoonsPageContent';

// Define a static genre mapping based on ct_genre_id
const genreMap = {
  1: "Action",
  2: "Comedy",
  3: "Adventure",
  4: "Drama",
  5: "Fantasy",
  // Add more mappings based on your API's ct_genre_id values
};

async function getCartoons() {
  try {
    const res = await fetch("https://nextjs-homework005.vercel.app/api/cartoon", {
      cache: "no-store",
    });
    if (!res.ok) {
      throw new Error("Failed to fetch cartoons");
    }
    const data = await res.json();

    // Map cartoons and include genre name from the static mapping
    const filteredCartoons = data.payload.map((cartoon) => ({
      id: cartoon.id.toString(),
      title: cartoon.ct_title,
      image: cartoon.image,
      genre: genreMap[cartoon.ct_genre_id] || "Unknown", // Use the mapping or "Unknown" if no match
    }));
    return filteredCartoons;
  } catch (error) {
    console.error("Error fetching cartoons:", error);
    return [];
  }
}

export default async function Cartoons() {
  const cartoons = await getCartoons();

  return (
    <div className="flex min-h-screen bg-gray-100">
      <Sidebar />
      <div className="ml-64 p-8 flex-1">
        <CartoonsPageContent initialCartoons={cartoons} />
      </div>
    </div>
  );
}