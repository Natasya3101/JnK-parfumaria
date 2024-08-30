// src/App.jsx
import Header from "./componets/Header";
import { Outlet } from "react-router-dom";

function App() {
  return (
    <div>
      <Header />
      <Outlet />
    </div>
  );
}

export default App;
