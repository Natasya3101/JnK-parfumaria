import React from 'react';
import { X } from 'lucide-react'; // Import the close icon from Lucide React

const ProductDetailPopup = ({ product, onClose }) => {
  const formatRupiah = (amount) => {
    const formatter = new Intl.NumberFormat('id-ID', {
      style: 'currency',
      currency: 'IDR',
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    });
    return formatter.format(amount);
  };

  if (!product) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-pink-100 rounded-lg shadow-lg p-6 max-w-sm w-full relative">
        {/* Close Button */}
        <button
          onClick={onClose}
          className="absolute top-2 right-2 text-pink-600 hover:text-pink-800 text-2xl"
        >
          <X size={24} />
        </button>

        {/* Product Image */}
        <div className="mb-4">
          <img
            src={`data:image/jpeg;base64,${product.image}`}
            alt={product.name}
            className="object-cover w-1/1 h-48 rounded-lg m-auto"
            style={{ aspectRatio: '1 / 1' }} // Force the image to be square
          />
        </div>

        {/* Product Details */}
        <div className="mb-4">
          <h2 className="text-2xl font-bold mb-2 text-pink-700">{product.name}</h2>
          <p className="text-lg mb-1 text-pink-600">
            <strong>Price:</strong> {formatRupiah(product.price)}
          </p>
          <p className="text-lg mb-1 text-pink-600">
            <strong>Stock:</strong> {product.stock}
          </p>
          <p className="text-lg text-pink-600">
            <strong>Category:</strong> {product.category}
          </p>
        </div>

        
      </div>
    </div>
  );
};

export default ProductDetailPopup;
