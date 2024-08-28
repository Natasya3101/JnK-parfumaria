// src/components/Header.jsx
import { useState} from "react";
import { useNavigate } from "react-router-dom";
import { ShoppingCart, LogInIcon } from "lucide-react";
import Login from "../pages/Login";
import logo from "../assets/logo.png";

function Header() {
  const navigate = useNavigate();
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  

  const openLogin = () => setIsLoginOpen(true);
  const closeLogin = () => setIsLoginOpen(false);



  return (
    <header className="bg-gray-500 p-2 flex justify-between items-center z-40 text-black sticky top-0">
      
      <div className="flex flex-col w-full max-w-lg mx-4 font-serif font-bold">
       
          <img
            src={logo}
            alt="logo"
            className="w-36 cursor-pointer mb-2" // Adjust the size as needed
            onClick={() => navigate("/")}
          />
        
        <h1 className="text-white text-xl font-bold">J&K Parfumaria</h1>
        
      </div>
      

      
      

     
      <div className="flex items-center space-x-10 text-white p-10 mt-2 font-serif">
      <button 
          onClick={() => navigate("/home")}
          
        >
          HOME
        </button>
        <button
          onClick={() => navigate("/category/man")}
          
        >
          MAN
        </button>
        <button 
          onClick={() => navigate("/category/women")}
          
        >
          WOMEN
        </button>
      <input
          type="text"
          placeholder="Search"
          className="w-full p-2 rounded border border-gray-300 mt-2"
        />
        <button
          onClick={openLogin}
          className="flex items-center space-x-2"
        >
          <LogInIcon size={30} /> <h1>LOGIN</h1>
        </button>
        <button
          onClick={() => navigate("/cart")}
          className="flex items-center space-x-2"
        >
          <ShoppingCart size={30} />
        </button>
      </div>

      {/* Login Modal */}
      <Login isOpen={isLoginOpen} onClose={closeLogin} />
    </header>
    
  );
}

export default Header;
