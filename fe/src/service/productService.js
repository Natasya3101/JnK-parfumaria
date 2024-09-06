
import { getToken } from "./userService";

export const getAllProducts = async (filter) => {
  try {
    const response = await fetch(
      `/api/products/get-all-products?name=${
        filter.name ? filter.name : ""
      }&category=${filter.category ? filter.category : ""}&sortBy=${
        filter.sortBy ? filter.sortBy : ""
      }&sortOrder=${filter.sortOrder ? filter.sortOrder : ""}&minPrice=${
        filter.minPrice ? filter.minPrice : ""
      }&maxPrice=${filter.maxPrice ? filter.maxPrice : ""}`,
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
  const response = await fetch("/api/cart/add-cart-items", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      productId: id,
      quantity: qty,
    }),
  });
  const res = await response.json();
  return res;
};

export const decreaseQuantity = async (id, qty) => {
  const token = getToken();
  const response = await fetch(`/api/cart/decrease-quantity`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      productId: id,
      quantity: qty,
    }),
  });
};
export const IncreaseQuantity = async (id, qty) => {
  const token = getToken();
  const response = await fetch(`/api/cart/increase-quantity`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      productId: id,
      quantity: qty,
    }),
  });
};

export const deleteCartItems = async (id) => {
  const token = getToken();
  const response = await fetch(`/api/cart/delete-cart-items/${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });
  const res = await response.json();
  alert(res.message);
};

export const getCart = async () => {
  const token = getToken();
  const response = await fetch(`/api/cart/find-all`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });
  const res = await response.json();
  return res.data;
};
