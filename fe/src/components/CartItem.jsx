import React from 'react';

const CartItem = ({ item, onIncrease, onDecrease, onDelete }) => {
  return (
    <div className="flex items-center justify-between p-4 border-b">
      <div className="flex items-center">
        {/* <input
          type="checkbox"
          className="form-checkbox h-4 w-4 text-yellow-00"
          checked={item.selected}
          onChange={onSelect}
        /> */}
        <img src={`data:image/jpeg;base64,${item.product.image}`} alt={item.product.name} className="w-26 h-24 ml-2" />
        <div className="ml-4">
          <p className="text-sm text-gray-500">{item.product.name}</p>
          <p className="text-sm">Rp. {item.amount.toLocaleString("id-ID")}</p>
        </div>
      </div>
      <div className="flex items-center">
        <div className="flex items-center ml-4">
          <button
            className="px-2"
            onClick={onDecrease}
            disabled={item.qty <= 1} // Disable if quantity is 1
          >
            -
          </button>
          <span className="px-2">{item.qty}</span>
          <button className="px-2" onClick={onIncrease}>
            +
          </button>
        </div>
        <button
          className="ml-4 p-2 bg-pink-600 text-white rounded-lg"
          onClick={onDelete}
        >
          Delete
        </button>
      </div>
    </div>
  );
};

export default CartItem;
