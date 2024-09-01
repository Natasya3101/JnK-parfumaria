// src/Home.js
import React from "react";

import VideoSlider from "../components/VideoSlider";
import Footer from "../components/Footer";

function Home() {
  return (
    <div>
      <VideoSlider />
      <div className="min-h-screen bg-pink-50 flex flex-col items-center py-8">
        <h1 className="text-4xl font-bold text-pink-600">J&K PARFUMARIA</h1>
        <p className="mt-4 text-center max-w-md text-gray-700">
          Welcome to J&K Parfumaria! We are delighted to have you here. At J&K
          Parfumaria, we specialize in curating the finest luxury perfumes that
          elevate your senses and define your unique essence. Our platform
          offers a meticulously selected range of high-end fragrances from the
          world's most prestigious brands, ensuring that every scent tells a
          story of elegance, sophistication, and timeless beauty.
        </p>
        <p className="mt-4 text-center max-w-md text-gray-700">
          Explore our
          collection and discover the perfect fragrance that resonates with your
          style and personality. Thank you for choosing J&K Parfumaria—where
          luxury meets the art of perfumery.
        </p>
      

        <div className="mt-6 grid grid-cols-3 gap-5">
          <img
            src="https://i.pinimg.com/564x/23/8e/cb/238ecb1e8d9045dcf9c5d062f6fba7cb.jpg"
            alt="Image 1"
            className="w-40 h-60 object-cover rounded-md"
          />
          <img
            src="https://i.pinimg.com/564x/e9/c0/8f/e9c08f7faa4e1aaba3e69d75369042fd.jpg"
            alt="Image 2"
            className="w-40 h-60 object-cover rounded-md"
          />
          <img
            src="https://i.pinimg.com/564x/8d/ff/fa/8dfffadf05bd6144d6e4f2a6e9cbbe80.jpg"
            alt="Image 3"
            className="w-40 h-60 object-cover rounded-md"
          />
        </div>
      </div>
      <Footer/>
    </div>
  );
}

export default Home;
