import React, { useState, useEffect } from "react";

const images = [
  'https://i.pinimg.com/474x/36/cb/4a/36cb4ae34e522e195480218a03e7012c.jpg',
  'https://i.pinimg.com/474x/90/11/77/90117727339d615be5ddb6203b6a3c9e.jpg',
  'https://i.pinimg.com/736x/9e/8d/94/9e8d94d2e22f20aa63884630b895f138.jpg',
];

const ImageSlider = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 3000); 

    return () => clearInterval(interval);
  }, []);

  return (
    <section className="relative w-full h-screen overflow-hidden">
      <div className="relative w-full h-full">
        <img
          className=" transition-transform duration-1000"
          style={{ backgroundImage: `url(${images[currentIndex]})` }}
        />
      </div>
    </section>
  );
};

export default ImageSlider;
