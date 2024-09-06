import React, { useState, useEffect } from "react";
import ProductCard from "../components/ProductCard";
import { addCart, getAllProducts } from "../service/productService";

function Product() {
  const [filter, setFilter] = useState({
    name: "",
    category: "",
    filter: "",
    sortBy: "",
    sortOrder: "",
    minPrice: "",
    maxPrice: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "sort") {
      setFilter((prevFilter) => ({
        ...prevFilter,
        filter: value,
        sortBy: value.includes("Price") ? "price" : "name",
        sortOrder:
          value === "lowPrice"
            ? "asc"
            : value === "highPrice"
            ? "desc"
            : value === "a-z"
            ? "asc"
            : value === "z-a"
            ? "desc"
            : "",
      }));
    } else {
      setFilter((prevFilter) => ({
        ...prevFilter,
        [name]: value,
      }));
    }
  };

  const [products, setProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      const result = await getAllProducts(filter);
      setProducts(result);
    };
    fetchProducts();
  }, [filter]);

  const handleAddCart = async (id) => {
    console.log(id);
    const res = await addCart(id, 1);
    alert(res.message)
  };

  return (
    <div className="p-5">
      {/* Filter and Sort Controls */}
      <div className="flex flex-col sm:flex-row sm:space-x-4 mb-5">
        <input
          type="text"
          placeholder="Search"
          name="name"
          onChange={handleChange}
          className="outline-4 outline outline-pink-100 focus:outline-pink-400 w-full p-2 rounded border border-white mt-2 text-black sm:mt-0"
        />
        <select
          id="category"
          name="category"
          value={filter.category}
          onChange={handleChange}
          className="border p-2 rounded bg-pink-50 mt-2 sm:mt-0 sm:w-1/3"
        >
          <option value="">All</option>
          <option value="Women">Women's Perfume</option>
          <option value="Men">Men's Perfume</option>
          <option value="Unisex">Unisex's Perfume</option>
        </select>

        <select
          id="sort"
          value={filter.filter}
          name="sort"
          onChange={handleChange}
          className="border p-2 rounded bg-pink-50 mt-2 sm:mt-0 sm:w-1/3"
        >
          <option value="">Default</option>
          <option value="lowPrice">Low Price</option>
          <option value="highPrice">High Price</option>
          <option value="a-z">A-Z</option>
          <option value="z-a">Z-A</option>
        </select>
      </div>

      {/* Product Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-10">
        {products.map((product) => (
          <ProductCard key={product.id} item={product} onAddCart={() => handleAddCart(product.id)} />
        ))}
      </div>
    </div>
  );
}

export default Product;
