// components/HomeCardsGrid.jsx
import HomeCard from './HomeCard';

const cards = [
  {
    title: 'View All Available Books',
    image: '/movie1.png',
    href: '/books',
    label: '🏷️ Book',
  },
  {
    title: 'View All Available Cartoons',
    image: '/movie2.png',
    href: '/cartoons',
    label: '🏷️ Cartoon',
  },
];

export default function HomeCardsGrid() {
  return (
    <div className="flex justify-center items-center h-full">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-10">
        {cards.map((card, index) => (
          <HomeCard key={index} card={card} index={index} />
        ))}
      </div>
    </div>
  );
}