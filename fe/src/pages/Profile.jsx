import React, { useState } from 'react';

const Profile = () => {
  const [profileData, setProfileData] = useState({
    fullName: 'Anna Avetisyan',
    dateOfBirth: '2024-09-01',
    email: 'info@aplusdesign.co',
    address: '123 Main St, City, Country',
    phoneNumber: '818 123 4567',
    gender: 'Female',
  });

  const [isEditing, setIsEditing] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProfileData({ ...profileData, [name]: value });
  };

  const handleSave = () => {
    setIsEditing(false);
    // Save the data to your backend or local storage here
  };

  const handleLogout = () => {
    // Handle logout logic here, e.g., clear auth tokens, redirect to login page
    console.log('Logged out');
  };

  return (
    <main className="max-w-md mx-auto p-10 m-5 bg-white rounded-lg border border-gray-200">
      <h1 className="text-2xl font-semibold text-center mb-6">Profile</h1>
      <div className="text-center mb-6">

        
        <h2 className="text-lg font-semibold">{profileData.fullName}</h2>
      </div>
      <form>
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
            value={profileData.dateOfBirth}
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
            type="tel"
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
            value={profileData.address}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700">Gender</label>
          <input
            type="text"
            name="gender"
            value={profileData.gender}
            onChange={handleInputChange}
            disabled={!isEditing}
            className="w-full px-4 py-2 mt-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
          />
        </div>
      </form>
      <div className="flex justify-between mt-6">
        <button
          onClick={isEditing ? handleSave : () => setIsEditing(true)}
          className="px-4 py-2 bg-pink-500 text-white rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
        >
          {isEditing ? 'Save' : 'Edit Profile'}
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
