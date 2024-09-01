import React from 'react';
import { Instagram, Facebook, Twitter, Youtube } from 'lucide-react'; // Importing Lucide icons

const Footer = () => {
  return (
    <div className="bg-pink-300 p-8 flex justify-between items-center text-white relative bottom-0">
      
      {/* Social Media Icons */}
      <div className="space-x-4 flex items-center">
        <a href="https://www.instagram.com" target="_blank" className="text-pink-500 hover:text-pink-400">
          <Instagram size={28} />
        </a>
        <a href="https://www.twitter.com" target="_blank" className="text-pink-500 hover:text-pink-400">
        <Twitter size={28} />
        </a>
        <a href="https://www.facebook.com" target="_blank" className="text-pink-500 hover:text-pink-400">
        <Facebook size={28} />
        </a>
        <a href="https://www.youtube.com/" target="_blank" className="text-pink-500 hover:text-pink-400">
          <Youtube size={28} />
        </a>
      </div>

      {/* Copyright Notice */}
      <div className="text-white text-sm">
      2024 | &copy; RAJA SALSABILLA ANNATASYA
      </div>
    </div>
  );
};

export default Footer;
