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
    localStorage.setItem('token', res.data.token);
};

export const getToken = ()=>{
    return localStorage.getItem('token');
}
