

export const getAllProducts = async (terms) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/products/get-all-products?name=${terms.name}&category=${terms.category}&sortBy=${terms.sortBy}&sortOrder=${terms.sortOrder}&minPrice=${terms.minPrice}&maxPrice=${terms.maxPrice}`
    );
    if (!response.ok) {
      throw new Error('Failed to fetch products');
    }
    return await response.json();
  } catch (error) {
    console.error(error);
    return [];
  }
};
