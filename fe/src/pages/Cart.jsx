import React, { useState } from "react";
import CartItem from "../components/CartItem";

const Cart = () => {
  const [items, setItems] = useState([
    {
      id: 1,
      name: "Calvin Klein Beauty Sheer 100 ML",
      image:
        "https://i.pinimg.com/474x/65/b4/d9/65b4d91dfd2aca8ecbe7a4995a3ffece.jpg",
      price: "Rp62.888",
      quantity: 1,
      selected: false,
    },
    {
      id: 2,
      name: "Calvin Klein Beauty Sheer 100 ML",
      image:
        "https://i.pinimg.com/474x/65/b4/d9/65b4d91dfd2aca8ecbe7a4995a3ffece.jpg",
      price: "Rp28.990",
      quantity: 1,
      selected: false,
    },
  ]);

  const toggleSelectAll = () => {
    const allSelected = items.every((item) => item.selected);
    const updatedItems = items.map((item) => ({
      ...item,
      selected: !allSelected,
    }));
    setItems(updatedItems);
  };

  const handleSelectItem = (id) => {
    const updatedItems = items.map((item) =>
      item.id === id ? { ...item, selected: !item.selected } : item
    );
    setItems(updatedItems);
  };

  const handleIncreaseQuantity = (id) => {
    const updatedItems = items.map((item) =>
      item.id === id ? { ...item, quantity: item.quantity + 1 } : item
    );
    setItems(updatedItems);
  };

  const handleDecreaseQuantity = (id) => {
    const updatedItems = items.map((item) =>
      item.id === id && item.quantity > 1
        ? { ...item, quantity: item.quantity - 1 }
        : item
    );
    setItems(updatedItems);
  };

  const handleDeleteItem = (id) => {
    const updatedItems = items.filter((item) => item.id !== id);
    setItems(updatedItems);
  };

  const selectedItems = items.filter((item) => item.selected);
  const totalAmount = selectedItems.reduce(
    (total, item) =>
      total + parseInt(item.price.replace("Rp", "").replace(".", "")) * item.quantity,
    0
  );

  return (
    <div className="bg-white">
      <header className="p-4 border-b">
        <h2 className="text-xl font-semibold">
          Keranjang Saya ({items.length})
        </h2>
      </header>
      <div>
        {items.map((item) => (
          <CartItem
            key={item.id}
            item={item}
            onSelect={() => handleSelectItem(item.id)}
            onIncrease={() => handleIncreaseQuantity(item.id)}
            onDecrease={() => handleDecreaseQuantity(item.id)}
            onDelete={() => handleDeleteItem(item.id)}
          />
        ))}
      </div>
      <div className="fixed bottom-0 left-0 w-full p-4 bg-pink-50 border-t">
        <div className="flex justify-between">
          <button
            className="bg-pink-600 text-white py-2 px-4 rounded-lg"
            onClick={toggleSelectAll}
          >
            Semua
          </button>
          <span className="text-lg font-semibold">Total Rp{totalAmount.toLocaleString("id-ID")}</span>
          <button className="bg-pink-600 text-white py-2 px-4 rounded-lg">
            Checkout ({selectedItems.length})
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
