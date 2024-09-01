import React, { useState } from "react";

const ProductCard = ({ item }) => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div
      key={item.id}
      className="relative flex flex-col p-4 bg-white border-4 rounded-md overflow-hidden"
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
      onClick={() => setIsHovered(!isHovered)}
    >
      <div className="relative">
        <img src={item.image} width={150} className="m-auto object-cover" alt={item.name} />
        {isHovered && (
          <button
        className="absolute bottom-18 left-1/2 transform -translate-x-1/2 bg-pink-200 text-black py-2 my-3 px-4 rounded-full shadow-lg"
        onClick={() => onAddToCart(product)}
      >
        Add to cart
      </button>
        )}
      </div>
      <div className="pt-4">
        <h1 className="justify-center text-center text-lg font-semibold mt-10">{item.name}</h1>
        <h2 className="text-gray-500 text-center">{item.category}</h2>
        <h2 className=" text-center">{item.price}</h2>
      </div>
    </div>
  );
};

export default ProductCard;
