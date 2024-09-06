import React, { useState } from "react";
import ProductDetailPopup from "./ProductDetailPopup"; // Import the popup component
import { addCart } from "../service/productService";
import { getToken } from "../service/userService";
import { useEffect } from "react";

const ProductCard = ({ item, onAddCart }) => {
  const [isHovered, setIsHovered] = useState(false);
  const [showPopup, setShowPopup] = useState(false); // State to control popup visibility

  const formatRupiah = (amount) => {
    const formatter = new Intl.NumberFormat("id-ID", {
      style: "currency",
      currency: "IDR",
      minimumFractionDigits: 0,
      maximumFractionDigits: 0,
    });

    return formatter.format(amount);
  };
  const [token, setToken] = useState();
  useEffect(() => {
    const fecthToken = () => {
      setToken(getToken());
    };
    fecthToken();
  });
  return (
    <>
      <div
        key={item.id}
        className="relative flex flex-col p-2 bg-white border-4 rounded-md overflow-hidden"
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
        // Open popup on click
      >
        <div className="relative">
          <img
            src={`data:image/jpeg;base64,${item.image}`}
            width={150}
            className="m-auto object-cover"
            alt={item.name}
          />
          {isHovered && token && (
            <button
              className="absolute bottom-18 left-1/2 transform -translate-x-1/2 bg-pink-200 text-black py-2 my-3 px-4 rounded-full shadow-lg"
              onClick={onAddCart}
            >
              Add to cart
            </button>
          )}
        </div>
        <div className="pt-4">
          <h1
            onClick={() => setShowPopup(true)}
            className="cursor-pointer justify-center text-center text-lg font-semibold mt-10"
          >
            {item.name}
          </h1>
          <h2 className="text-gray-500 text-center">{item.category}</h2>
          <h2 className="text-center">{formatRupiah(item.price)}</h2>
        </div>
      </div>

      {showPopup && (
        <ProductDetailPopup
          product={item}
          onClose={() => setShowPopup(false)} // Close popup on close button click
        />
      )}
    </>
  );
};

export default ProductCard;
