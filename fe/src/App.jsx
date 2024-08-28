// src/App.jsx
import { BrowserRouter as Router} from "react-router-dom";
import Header from "./componets/Header";
import ImageSlider from "./componets/ImageSlider";



function App() {
  return (
    <Router>
      <div >
        <Header />
      <ImageSlider/>
      </div>
    </Router>
  );
}

export default App;
