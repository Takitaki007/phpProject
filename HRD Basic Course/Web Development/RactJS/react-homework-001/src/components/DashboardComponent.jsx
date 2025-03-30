import React from "react";
import { dashboard } from "../data/dashboard"; 

export default function DashboardComponent() {
  return (
    <div>
      <h2 className="text-xl font-semibold mb-5">Dashboard</h2>

      {/* Display summary dynamically */}
      <div className="flex gap-5 ">
        {dashboard.map((item) => (
          
          <div key={item.id} className="flex bg-white gap-5 py-3.5 px-4 rounded-xl w-full shadow-md">
            <div className={`w-[50px] h-[50px] flex items-center justify-center rounded-xl ${item.color}`}>
              <img src={item.icon} alt="file icon" className="w-6 h-6" />
            </div>
            <div>
              <p className="text-xl font-semibold">{item.totalTasks}</p>
              <p className="text-gray-400">{item.label}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
