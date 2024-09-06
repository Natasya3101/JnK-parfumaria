import React, { useState } from "react";
import CartItem from "../components/CartItem";
import { useEffect } from "react";
import {
  addCart,
  decreaseQuantity,
  deleteCartItems,
  getCart,
} from "../service/productService";

const Cart = () => {
  const [cart, setCart] = useState();

  const fetchCart = async () => {
    const res = await getCart();
    setCart(res);
  };
  useEffect(() => {
    fetchCart();
  }, []);

  // const toggleSelectAll = () => {
  //   const allSelected = items.every((item) => item.selected);
  //   const updatedItems = items.map((item) => ({
  //     ...item,
  //     selected: !allSelected,
  //   }));
  //   setItems(updatedItems);
  // };

  // const handleSelectItem = (id) => {
  //   const updatedItems = items.map((item) =>
  //     item.id === id ? { ...item, selected: !item.selected } : item
  //   );
  //   setItems(updatedItems);
  // };

  const handleIncreaseQuantity = async (id) => {
    await addCart(id, 1);
    await fetchCart();
  };

  const handleDecreaseQuantity = async (id) => {
    await decreaseQuantity(id, 1);
    await fetchCart();
  };

  const handleDeleteItem = async (id) => {
    await deleteCartItems(id);
    await fetchCart();
  };

  return (
    <div className=" bg-white">
      <header className="p-4 border-b">
        <h2 className="text-xl font-semibold">
          Keranjang Saya ({cart?.cartItems.length})
        </h2>
      </header>
      <div className="mt-5">
        {cart?.cartItems.map((item) => (
          <CartItem
            key={item.product.id}
            item={item}
            onIncrease={() => handleIncreaseQuantity(item.product.id)}
            onDecrease={() => handleDecreaseQuantity(item.product.id)}
            onDelete={() => handleDeleteItem(item.id)}
          />
        ))}
      </div>
    </div>
  );
};

export default Cart;
