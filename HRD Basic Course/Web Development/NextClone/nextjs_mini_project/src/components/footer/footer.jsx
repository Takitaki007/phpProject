'use client'
import React from 'react';
 function Footer(){
    return(
        <>
    

        <div className=' flex w-full mt-10'>
        <button className="bg-blue-500 flex cursor-pointer justify-around w-[150px] h-[40px] rounded-2xl mt-8 font-semibold text-white p-2 ml-[80%] "><span><img className='w-5 h-5' src="Addnew.png"></img></span>New Task</button>
        <img className='w-[60px] mt-7  h-[60px]' src="frame.png"></img>

        </div>

        </>
    )
}
export default Footer;