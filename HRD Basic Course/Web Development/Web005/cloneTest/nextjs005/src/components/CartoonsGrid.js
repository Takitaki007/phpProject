// src/components/CartoonsGrid.jsx
"use client";

import Link from "next/link";
import CartoonCard from "./CartoonCard";

export default function CartoonsGrid({ cartoons }) {
  return (
    <div className="grid grid-cols-3 gap-6">
      {cartoons.map((cartoon) => (
        <Link href={`/cartoons/${cartoon.id}`} key={cartoon.id}>
          <div className="flex justify-around">
            <CartoonCard cartoon={cartoon} />
          </div>
        </Link>
      ))}
    </div>
  );
}