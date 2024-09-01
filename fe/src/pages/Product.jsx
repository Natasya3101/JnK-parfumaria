import ProductCard from '../components/ProductCard';
import { useState } from 'react';
const products = [
  {
    id: 1,
    name: 'Calvin Klein Beauty Sheer 100 ML',
    image: 'https://i.pinimg.com/474x/65/b4/d9/65b4d91dfd2aca8ecbe7a4995a3ffece.jpg',
    price: 1000,
    category: "Woman",
    Stock: 10,
  },
  {
    id: 2,
    name: 'Chanel Coco Mademoiselle 100 ML',
    image: 'https://i.pinimg.com/564x/c7/27/0b/c7270b0aad7b3e3819f5004cef8d0eaa.jpg', // Ubah ke URL gambar yang berbeda
    price: 1500,
    category: "Woman",
    Stock: 10,
  },
  {
    id: 1,
    name: 'Balenciaga L Eau Rose 50 ML',
    image: 'https://i.pinimg.com/564x/0a/46/50/0a4650aacea287ae2bfd54e069e73181.jpg',
    price: 1000,
    category: "Woman",
    Stock: 10,
  },
  {
    id: 2,
    name: 'Maison Alhambra Tobacco Touch 80 ML',
    image: 'https://i.pinimg.com/564x/0a/40/4b/0a404be49015f4bc337e36f2318afa81.jpg', // Ubah ke URL gambar yang berbeda
    price: 1500,
    category: "Men",
    Stock: 10,
  },
  {
    id: 1,
    name: 'Product 1',
    image: 'https://i.pinimg.com/474x/65/b4/d9/65b4d91dfd2aca8ecbe7a4995a3ffece.jpg',
    price: 1000,
    category: "Woman",
    Stock: 10,
  },
  {
    id: 2,
    name: 'Product 2',
    image: 'https://i.pinimg.com/474x/f4/e4/ab/f4e4ab07f644fe60bcae7bacb9e2c104.jpg', // Ubah ke URL gambar yang berbeda
    price: 1500,
    category: "Woman",
    Stock: 10,
  },
];

function Product() {

  const [filter, setFilter] = useState('all');
  const [sort, setSort] = useState('default');

  // Filtering Logic
  const filteredProducts = products.filter((product) => {
    if (filter === 'all') return true;
    return product.category === filter;
  });

  // Sorting Logic
  const sortedProducts = [...filteredProducts].sort((a, b) => {
    if (sort === 'lowPrice') return a.price - b.price;
    if (sort === 'highPrice') return b.price - a.price;
    if (sort === 'a-z') return a.name.localeCompare(b.name);
    if (sort === 'z-a') return b.name.localeCompare(a.name);
    return 0;
  });

  return (
    <div className='p-5'>
      {/* Filter and Sort Controls */}
      <div className='flex space-x-4 mb-5'>
        <select
          onChange={(e) => setFilter(e.target.value)}
          className='border p-2 rounded bg-pink-50'
        >
          <option value="all">All</option>
          <option value="Woman">Women's Perfume</option>
          <option value="Men">Men's Perfume</option>
          <option value="Unisex">Unisex's Perfume</option>
        </select>

        <select
          onChange={(e) => setSort(e.target.value)}
          className='border p-2 rounded bg-pink-50'
        >
          <option value="default">Default</option>
          <option value="lowPrice">Low Price</option>
          <option value="highPrice">High Price</option>
          <option value="a-z">A-Z</option>
          <option value="z-a">Z-A</option>
        </select>
      </div>

      {/* Product Grid */}
      <div className='grid grid-cols-4 gap-10'>
        {sortedProducts.map((product) => (
          <ProductCard key={product.id} item={product} />
        ))}
      </div>
      
    </div>
    
  );
}
export default Product;