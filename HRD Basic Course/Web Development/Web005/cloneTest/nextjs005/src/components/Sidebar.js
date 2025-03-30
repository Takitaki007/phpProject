import Link from 'next/link';

const Sidebar = () => {
  return (
    <div className="mt-0 w-64 h-screen p-5 bg-white fixed">
      <div className="flex flex-col items-center">
        <img src="./profile1.png" alt="Profile" className="w-24 h-24 rounded-full" />
        <h2 className="mt-2 text-lg font-semibold">Black Monster</h2>
        <p className="text-sm text-sky-700">blackmonster@gmail.com</p>
      </div>
      <nav className="mt-5">
        <ul>
          <li><Link href="/" className="block p-1  hover:bg-gray-200 rounded"> 🏠Home</Link></li>
          <li className='mt-1'><Link href="/books" className="block p-0.5  hover:bg-gray-200 rounded">📚Book Categories</Link></li>
          <li className='mt-1'><Link href="/cartoons" className="block p-0.5   hover:bg-gray-200 rounded">📺Old-School Cartoons</Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5  hover:bg-gray-200 rounded">🎬Movies & TV Shows</Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5  hover:bg-gray-200 rounded">🎵Music</Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5   hover:bg-gray-200 rounded">📷Photography</Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5  hover:bg-gray-200 rounded">⚽Sport and Fitness</Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5  hover:bg-gray-200 rounded">🖥️Technology and Gadgets </Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5  hover:bg-gray-200 rounded">✈️ Travel and Exploration</Link></li>
          <li className='mt-1'><Link href="/" className="block p-0.5  hover:bg-gray-200 rounded">✍️Writing and Journaling</Link></li>
          <li className='mt-10'><Link href="/" className="block  hover:bg-gray-200 rounded">⚙️Settings</Link></li>

        </ul>
      

      </nav>
    </div>
  );
};

export default Sidebar;