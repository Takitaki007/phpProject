// src/app/page.js
import Sidebar from '../components/Sidebar';
import SearchBar from '../components/SearchBar';
import HomeTitleSection from '../components/HomeTitleSection';
import HomeCardsGrid from '../components/HomeCardsGrid';

export default function Home() {
  return (
    
    <div className="flex min-h-screen bg-gray-100">
      <Sidebar />
      <div className="ml-64 p-8 flex-1">
        <SearchBar />
        <HomeTitleSection />
        <div className='bg-white h-screen rounded-2xl'>
        <HomeCardsGrid />
        </div>
      </div>
    </div>
   
  );
}