'use client';
import React, { useState } from 'react';
import { MoreHorizontal, LogOut, Car } from 'lucide-react';
import Navbar from "@/components/navbar/navbar";
import Sidebar from "@/components/sidebar/sidebar";
import CardComponent from "@/components/card";
import { Button } from '@/components/ui/button';
import Footer from "@/components/footer/footer";
export default function Dashboard() {
  return (
    <>
    <div className="grid grid-cols-12 gap-4">
      <div className="col-span-3">
        <Sidebar />
    </div>
    <div className="col-span-9 ">
      <Navbar/>
      <div className='mt-16 flex justify-between mr-5'>
      <h1 className='text-2xl font-bold text-black ml-4 mt-2'>
        HRD Design
        </h1>
        <img className='mt-2 w-[27] h-[27]' src="star.png"></img>
      </div>
      <div className='flex col-span-3 w-[73%] justify-between mt-4'>
      <h1 className='ml-5 mt-8 text-red-500'><u>Not Started</u></h1>
      <h1 className='ml-5 mt-8 text-blue-500'><u>In Progress</u></h1>
      <h1 className='ml-5 mt-8 text-green-500 '><u>Finished</u></h1>
      </div>
      <div className="grid grid-cols-3 gap-4  mr-5">
<CardComponent />
<CardComponent />
<CardComponent />
<CardComponent />
<CardComponent />
      </div>
      </div>
    </div>
    <Footer/>

    </>
  );
}
