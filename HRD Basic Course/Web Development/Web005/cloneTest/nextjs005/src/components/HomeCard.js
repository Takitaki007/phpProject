// components/HomeCard.jsx
import Link from 'next/link';

export default function HomeCard({ card, index }) {
  return (
    <Link href={card.href}>
      <div className="relative  rounded-lg p-5 cursor-pointer group transition-transform duration-500 transform hover:scale-105">
        {/* Label */}
        <div className="absolute ml-3 mt-5 flex text-sky-700 font-bold bg-white text-sm rounded-full p-1 w-[90px]">
          {card.label}
        </div>
        {/* Image */}
        <img
          src={card.image}
          alt={card.title}
          className="w-[300px] h-auto rounded-lg object-cover"
        />
        {/* Hover Overlay */}
        <div className="absolute inset-0 flex items-center justify-center bg-opacity-0 group-hover:bg-opacity-50 transition-opacity rounded-lg">
          <p className="text-white text-lg font-bold opacity-0 group-hover:opacity-100 transition-opacity">
            {card.title}
          </p>
        </div>
      </div>
    </Link>
  );
}