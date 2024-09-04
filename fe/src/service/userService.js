export const login = async (email, password) => {
  const response = await fetch("/api/user/auth/login", {
    method: "POST",
    body: JSON.stringify({
      email: email,
      password: password,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  });
  const res = await response.json();
  localStorage.setItem("token", res.data.token);
  alert(res.message);
};

export const register = async (
  fullName,
  gender,
  phoneNumber,
  email,
  password
) => {
  const response = await fetch("/api/user/register", {
    method: "POST",
    body: JSON.stringify({
      fullName: fullName,
      gender: gender,
      phoneNumber: phoneNumber,
      email: email,
      password: password,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  });
  const res = await response.json();
  alert(res.message);
};

export const getProfile = async () => {
  const token = getToken();
  const response = await fetch("/api/user/auth/profile", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });
  const res = await response.json();
  console.log(res.data);
  return res.data;
};

export const editProfile = async (email, fullName, dateOfBirth, address, phoneNumber, gender) => {
  const token = getToken();
  const response = await fetch("/api/user/auth/edit-profile", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      email: email,
      fullName: fullName,
      dateOfBirth: dateOfBirth,
      address: address,
      phoneNumber: phoneNumber,
      gender: gender,
    }),
  });
  const res = await response.json();
  alert(res.message);
};

export const getToken = () => {
  return localStorage.getItem("token");
};

export const logout = () => {
  localStorage.removeItem("token");
};
