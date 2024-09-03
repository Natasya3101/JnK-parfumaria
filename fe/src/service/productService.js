export const getAllProducts = async (terms) => {
  try {
    const response = await fetch(
      `/api/products/get-all-products?name=${terms.name}&category=${terms.category}&sortBy=${terms.sortBy}&sortOrder=${terms.sortOrder}&minPrice=${terms.minPrice}&maxPrice=${terms.maxPrice}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const res = await response.json();
    return res.data.items;
  } catch (error) {
    console.error(error);
    return [];
  }
};
export const getProduct = async (id) => {
  try {
    const response = await fetch(
      `/api/products/get-product/${id}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const res = await response.json();
    return res.data;
  } catch (error) {
    console.error(error);
    return {};
  }
};
