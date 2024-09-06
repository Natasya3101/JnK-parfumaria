// src/components/Header.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ShoppingCart, CircleUserRound } from "lucide-react";
import Login from "./Login";
import logo from "../assets/logo.png";
import { useEffect } from "react";
import { getToken } from "../service/userService";
import { Link } from "react-router-dom";

function Header() {
  const navigate = useNavigate();
  const [isLoginOpen, setIsLoginOpen] = useState(false);
  const [token, setToken] = useState();
  useEffect(() => {
    const fecthToken = () => {
      setToken(getToken());
    };
    fecthToken();
  });

  const openLogin = () => setIsLoginOpen(true);
  const closeLogin = () => setIsLoginOpen(false);

  return (
    <header className="bg-pink-300  flex justify-between items-center z-40 text-black sticky top-0 p-1">
      <div className="flex items-center w-full max-w-lg mx-4 font-serif font-bold">
        <img src={logo} alt="logo" width={100} onClick={() => navigate("/")} />
        <h1 className="text-white text-xl font-bold">J&K Parfumaria</h1>
      </div>

      <div className="flex items-center space-x-10 text-white font-serif p-8">
        <Link to={"/"}>HOME</Link>
        <Link to={"/product"}>PRODUCT</Link>
        {token && (
          <Link to={"/cart"} className="flex items-center space-x-3">
            <ShoppingCart size={30} />
          </Link>
        )}
        {token ? (
          <Link to={"/profile"}>
            <CircleUserRound size={30} /> <h1></h1>
          </Link>
        ) : (
          <Link onClick={openLogin} className="flex items-center space-x-3">
            <CircleUserRound size={30} /> <h1></h1>
          </Link>
        )}
      </div>
      <Login isOpen={isLoginOpen} onClose={closeLogin} />
    </header>
  );
}

export default Header;
