// src/components/Header.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ShoppingCart, LogInIcon, CircleUserRound  } from "lucide-react";
import Login from "./Login";
import logo from "../assets/logo.png";

function Header() {
  const navigate = useNavigate();
  const [isLoginOpen, setIsLoginOpen] = useState(false);

  const openLogin = () => setIsLoginOpen(true);
  const closeLogin = () => setIsLoginOpen(false);

  return (
    <header className="bg-pink-300 p-2 flex justify-between items-center z-40 text-black sticky top-0">
      <div className="flex items-center w-full max-w-lg mx-4 font-serif font-bold">
        <img
          src={logo}
          alt="logo"
          width={100}
          onClick={() => navigate("/")}
        />
        <h1 className="text-white text-xl font-bold">J&K Parfumaria</h1> 
        
        
      </div>
      
      <div className="flex items-center space-x-10 text-white font-serif p-8">
      <button onClick={() => navigate("/")}>HOME</button>
        <button onClick={() => navigate("/product")}>PRODUCT</button>
        <button onClick={() => navigate("/man")}>MAN</button>
        <button onClick={() => navigate("/women")}>WOMEN</button>
        <input
          type="text"
          placeholder="Search"
          className="w-full p-2 rounded border border-pink-300 mt-2 text-black"
        />
        <button
          onClick={() => navigate("/cart")}
          className="flex items-center space-x-3"
        >
          <ShoppingCart size={30} />
        </button>
        <button onClick={openLogin} className="flex items-center space-x-3">
          <CircleUserRound size={30} /> <h1></h1>
        </button>
        
      </div>

      {/* Login Modal */}
      <Login isOpen={isLoginOpen} onClose={closeLogin} />
    </header>
  );
}

export default Header;
