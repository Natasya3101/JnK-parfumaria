import React, { useState, useEffect } from "react";
import { getProfile, logout, editProfile } from "../service/userService";
import { useNavigate } from "react-router-dom";

const Profile = () => {
  const navigate = useNavigate();
  const [profileData, setProfileData] = useState({
    id: "",
    email: "",
    fullName: "",
    dateOfBirth: new Date(),
    address: "",
    phoneNumber: "",
    gender: "",
  });

  useEffect(() => {
    const fetchProfile = async () => {
      const result = await getProfile();
      setProfileData(result);
    };
    fetchProfile();
  }, []);

  const [isEditing, setIsEditing] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProfileData({ ...profileData, [name]: value });
  };

  const handleSave = () => {
    editProfile(
      profileData.email,
      profileData.fullName,
      profileData.dateOfBirth,
      profileData.address,
      profileData.phoneNumber,
      profileData.gender
    );
    setIsEditing(false);
    navigate("/profile");
  };

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <main className="max-w-5xl mx-auto p-10 m-5 bg-white rounded-lg border border-gray-200">
      <h1 className="text-2xl font-semibold text-center mb-6">Profile</h1>
      
      <form className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div className="mb-4">
          <label className="block text-gray-700">Full Name</label>
          <input
            type="text"
            name="fullName"
            value={profileData.fullName}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Birthday</label>
          <input
            type="date"
            name="dateOfBirth"
            value={
              profileData.dateOfBirth == null
                ? ""
                : new Date(profileData.dateOfBirth).toISOString().split("T")[0]
            }
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Email</label>
          <input
            type="email"
            name="email"
            value={profileData.email}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Phone Number</label>
          <input
            type="text"
            name="phoneNumber"
            value={profileData.phoneNumber}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Address</label>
          <input
            type="text"
            name="address"
            value={profileData.address ? profileData.address : ""}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Gender</label>
          <select
            name="gender"
            value={profileData.gender}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          >
            <option value="Male">Male</option>
            <option value="Female">Female</option>
          </select>
        </div>
      </form>
      <div className="flex justify-between mt-6">
        <button
          onClick={isEditing ? handleSave : () => setIsEditing(true)}
          className="px-4 py-2 bg-pink-500 text-white rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
        >
          {isEditing ? "Save" : "Edit Profile"}
        </button>
        <button
          onClick={handleLogout}
          className="px-4 py-2 bg-red-600 text-white rounded-lg focus:outline-none focus:ring-2 focus:ring-red-600"
        >
          Logout
        </button>
      </div>
    </main>
  );
};

export default Profile;
