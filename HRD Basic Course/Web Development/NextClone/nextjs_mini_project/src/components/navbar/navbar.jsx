
import React from "react";
function Navbar(){
    return (

        <>
        {/* Top Bar */}
<div className="flex justify-between  items-center mr-5 mt-[-130px] bg-white drop-shadow-light-steel-blue p-4 rounded-3xl">
<div className="text-sm text-gray-400 flex">
  <span className="text-gray-600 font-lg">Workspace</span>&nbsp;&nbsp;&nbsp;&nbsp;  <span><img className="w-[25] h-[20]" src="bigger.png"></img></span> &nbsp;&nbsp;&nbsp;&nbsp;  <span className="text-blue-600 font-semibold"><u>HRD Design</u></span>
</div>
<div className="flex items-center gap-4">
  {/* <Bell className="w-5 h-5 text-gray-500" /> */}
  <div className="flex items-center gap-2">
    <img className="w-7 h-7" src="alarm.png"/>&nbsp;
    <img src="https://i.pravatar.cc/32?img=1" className="w-8 h-8 rounded-full" alt="User" />
    <div className="text-sm">
      <div className="font-semibold text-gray-800">Monster</div>
      <div className="text-green-500">blackmonster@gmail.com</div>
    </div>
  </div>
</div>
</div>
        </>
    )
}
export default Navbar;