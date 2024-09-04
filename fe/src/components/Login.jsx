// src/components/Login.jsx
import { useState } from "react";
import { X } from "lucide-react";
import { useNavigate } from "react-router-dom";
import {
  login as loginService,
  register as registerService,
} from "../service/userService";

const Login = ({ isOpen, onClose }) => {
  const [isRegistering, setIsRegistering] = useState(false);
  const [login, setLogin] = useState({
    email: "",
    password: "",
  });
  const [register, setRegister] = useState({
    fullName: "",
    gender: "",
    phoneNumber: "",
    email: "",
    password: "",
  });

  const handleChangeRegister = (e) => {
    const { name, value } = e.target;
    setRegister((prevRegister) => ({
      ...prevRegister,
      [name]: value,
    }));
  };

  const handleChangeLogin = (e) => {
    const { name, value } = e.target;
    setLogin((prevLogin) => ({
      ...prevLogin,
      [name]: value,
    }));
  };
  const navigate = useNavigate();

  if (!isOpen) return null;

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log(isRegistering);
    if (isRegistering) {
      await registerService(
        register.fullName,
        register.gender,
        register.phoneNumber,
        register.email,
        register.password
      );
      handleChangeForm();
    } else {
      await loginService(login.email, login.password);
      setLogin({})
      navigate("/");
      onClose();
    }
  };

  const handleChangeForm = () => {
    setLogin({
      email: "",
      password: "",
    });
    setRegister({
      fullName: "",
      gender: "Male",
      phoneNumber: "",
      email: "",
      password: "",
    });
    setIsRegistering(!isRegistering);
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="relative bg-white p-5 rounded-lg shadow-lg w-96">
        {/* Close Icon */}
        <button
          onClick={onClose}
          className="absolute top-2 right-2 text-black hover:text-black"
        >
          <X size={24} />
        </button>

        <h2 className="text-xl font-bold mb-4 text-center">
          {isRegistering ? "Register" : "Login"}
        </h2>

        <form onSubmit={handleSubmit}>
          {isRegistering && (
            <div className="flex flex-col gap-2 mb-2">
              <div>
                <label className="block text-black text-sm font-bold ">
                  Nama Lengkap
                </label>
                <input
                  name="fullName"
                  id="fullName"
                  value={register.fullName}
                  onChange={handleChangeRegister}
                  type="text" // Changed from email to text for 'Nama'
                  placeholder="Enter your name"
                  className="w-full p-2 border border-black rounded"
                />
              </div>
              <div>
                <label className="block text-black text-sm font-bold ">
                  Gender
                </label>
                <select
                  name="gender"
                  id="gender"
                  value={register.gender}
                  onChange={handleChangeRegister}
                  className="w-full p-2 border border-black rounded"
                >
                  <option value="Male">Male</option>
                  <option value="Famale">Famale</option>
                </select>
              </div>
              <div>
                <label className="block text-black text-sm font-bold ">
                  No.HP
                </label>
                <input
                  type="text"
                  name="phoneNumber"
                  value={register.phoneNumber}
                  onChange={handleChangeRegister}
                  placeholder="Enter your name"
                  className="w-full p-2 border border-black rounded"
                />
              </div>
            </div>
          )}
          <div className="mb-2">
            <label className="block text-black text-sm font-bold">Email</label>
            <input
              type="email"
              name="email"
              value={isRegistering ? register.email : login.email}
              onChange={
                isRegistering ? handleChangeRegister : handleChangeLogin
              }
              placeholder="Enter your email"
              className="w-full p-2 border border-black rounded"
            />
          </div>

          <div className="mb-2">
            <label className="block text-black text-sm font-bold">
              Password
            </label>
            <input
              value={isRegistering ? register.password : login.password}
              onChange={
                isRegistering ? handleChangeRegister : handleChangeLogin
              }
              name="password"
              type="password"
              placeholder="Enter your password"
              className="w-full p-2 border border-black rounded"
            />
          </div>

          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="bg-gray-500 text-white p-2 rounded hover:bg-gray-700"
            >
              {isRegistering ? "Register" : "Login"}
            </button>
          </div>
        </form>

        <div className="mt-4 text-center">
          <p className="text-gray-500">
            {isRegistering
              ? "Already have an account?"
              : "Don't have an account?"}{" "}
            <button
              onClick={handleChangeForm}
              className="text-gray-500 hover:underline"
            >
              {isRegistering ? "Login" : "Register"}
            </button>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Login;
