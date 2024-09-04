import axios from "axios";
import { getToken } from "./userService";

export const getAllProducts = async (terms) => {
  try {
    const response = await fetch(
      `/api/products/get-all-products?name=${
        terms.name ? terms.name : ""
      }&category=${terms.category ? terms.category : ""}&sortBy=${
        terms.sortBy ? terms.sortBy : ""
      }&sortOrder=${terms.sortOrder ? terms.sortOrder : ""}&minPrice=${
        terms.minPrice ? terms.minPrice : ""
      }&maxPrice=${terms.maxPrice ? terms.maxPrice : ""}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const res = await response.json();
    return res.data.items;
  } catch (error) {
    console.error(error);
    return [];
  }
};

export const getProduct = async (id) => {
  try {
    const response = await fetch(`/api/products/get-product/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const res = await response.json();
    return res.data;
  } catch (error) {
    console.error(error);
    return {};
  }
};

export const addCart = async (id, qty) => {
  const token = getToken();
  console.log(id, qty);
  const req = JSON.stringify({
    productId: id,
    quantity: qty,
  });
  const response = await axios.post(
    "/api/cart/add-cart-items",
    req,
    {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    }
  );
  console.log(await response.json());
  return await response.json();

  // fetch("/api/cart/add-cart-items", {
  //   method: "POST",
  //   body: JSON.stringify({
  //     productId: id,
  //     quantity: qty
  //   }),
  //   headers: {
  //     Authorization: `Bearer ${token}`,
  //     "Content-Type": "application/json",
  //   },
  // });
  // console.log(await response.json());
  // return await response.json();
};
