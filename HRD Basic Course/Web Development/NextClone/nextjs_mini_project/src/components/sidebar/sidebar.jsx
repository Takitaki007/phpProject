'use client';
import React from 'react';
import { MoreHorizontal, LogOut } from 'lucide-react';

function Sidebar() {
  return (
    <div className="flex h-screen font-sans bg-white">
      {/* Sidebar */}
      <aside className="w-64 bg-white p-6 flex flex-col justify-between">
        <div>
          <div className="mb-6">
            <div className='flex justify-between'>
            <h2 className="text-lg font-semibold text-gray-500 mb-2">Workspace</h2>
            <img className='w-[23] h-[23] mr-1' src='add.png'></img>
              
            </div>
            <ul className="space-y-2">
              {["HRD Design", "Website Design", "Mobile Design", "Spring Boot"].map((item, i) => (
                <li key={i} className={`flex items-center justify-between px-2 py-1 rounded-md hover:bg-slate-100 ${item === "HRD Design" ? "bg-slate-100 font-semibold text-gray-800" : "text-gray-600"}`}>
                  <span className="flex items-center gap-2">
                    <span className={`w-2 h-2 rounded-full ${["bg-red-500", "bg-blue-500", "bg-green-500", "bg-purple-500"][i]}`}></span>
                    {item}
                  </span>
                  <MoreHorizontal className="w-4 h-4 text-gray-400" />
                </li>
              ))}
            </ul>
          </div>

          <div className='mt-10'>
            <div className='flex justify-between'>
            <h2 className="text-lg font-semibold text-gray-500 mb-2">Favorite</h2>
            <img className='w-[22] h-[22] mr-1' src='favor.png'></img>

            </div>
            <ul className="space-y-2">
              {["HRD Design", "Website Design"].map((item, i) => (
                <li key={i} className="flex items-center justify-between px-2 py-1 rounded-md hover:bg-slate-100 text-gray-600">
                  <span className="flex items-center gap-2">
                    <span className={`w-2 h-2 rounded-full ${["bg-red-500", "bg-blue-500"][i]}`}></span>
                    {item}
                  </span>
                  <MoreHorizontal className="w-4 h-4 text-gray-400" />
                </li>
              ))}
            </ul>
          </div>
        </div>
        <button className="flex items-center gap-2 text-sm text-emerald-600 font-semibold">
          <LogOut className="w-4 h-4" /> Logout
        </button>
      </aside>
    </div>
  );
}

export default Sidebar;
