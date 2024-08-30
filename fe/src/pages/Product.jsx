import ProductCard from '../componets/ProductCard';

const products = [
  {
    id: 1,
    name: 'Calvin Klein Beauty Sheer 100ml',
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
  return (
    <div className='p-5'>
      <div className='grid grid-cols-4 gap-10'>
        {products.map((product) => (
          <ProductCard key={product.id} item={product} />
        ))}
      </div>
    </div>
  );
}
export default Product;