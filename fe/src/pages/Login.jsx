// src/components/Login.jsx
import { useState } from "react";
import { X } from "lucide-react";
import { useNavigate } from "react-router-dom";

const Login = ({ isOpen, onClose }) => {
  const [isRegistering, setIsRegistering] = useState(false);
  const navigate = useNavigate();

  if (!isOpen) return null;

  const handleSubmit = (event) => {
    event.preventDefault();
    // Here, you would add your login logic (e.g., API call).
    // On successful login, navigate to the home page.
    navigate("/home");
    onClose(); // Close the modal after login
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div className="relative bg-white p-6 rounded-lg shadow-lg w-96">
        {/* Close Icon */}
        <button
          onClick={onClose}
          className="absolute top-2 right-2 text-gray-500 hover:text-gray-700"
        >
          <X size={24} />
        </button>

        <h2 className="text-xl font-bold mb-4">
          {isRegistering ? "Register" : "Login"}
        </h2>

        <form onSubmit={handleSubmit}>
        {isRegistering && (
            <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Nama
            </label>
            <input
              type="text" // Changed from email to text for 'Nama'
              placeholder="Enter your name"
              className="w-full p-2 border border-gray-500 rounded"
            />
          </div>
          )}

          <div className="mb-6">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Email
            </label>
            <input
              type="email"
              placeholder="Enter your email"
              className="w-full p-2 border border-gray-500 rounded"
            />
          </div>

          <div className="mb-6">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Password
            </label>
            <input
              type="password"
              placeholder="Enter your password"
              className="w-full p-2 border border-gray-500 rounded"
            />
          </div>

          

          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="bg-gray-800 text-white p-2 rounded hover:bg-gray-500"
            >
              {isRegistering ? "Register" : "Login"}
            </button>
          </div>
        </form>

        <div className="mt-4 text-center">
          <p className="text-gray-700">
            {isRegistering
              ? "Already have an account?"
              : "Don't have an account?"}{" "}
            <button
              onClick={() => setIsRegistering(!isRegistering)}
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
