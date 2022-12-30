import { BACKEND_URL } from "./const";

const createCategory = async (category, token) => {
  const url = `${BACKEND_URL}/api/category/`;

  const response = await fetch(url, {
    method: "POST",
    headers: { Authorization: `Bearer ${token}`,
    "Content-type": "application/json" },
    body: JSON.stringify(category)
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.text;
};

const renameCategory = async (categoryId, newName, token) => {
    const url = `${BACKEND_URL}/api/category/${categoryId}`;
  
    const response = await fetch(url, {
      method: "PUT",
      headers: { Authorization: `Bearer ${token}`,
      "Content-type": "application/json" },
      body: newName
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.text();
};
const deleteCategory = async (categoryId, token) => {
    const url = `${BACKEND_URL}/api/category/${categoryId}`;
  
    const response = await fetch(url, {
      method: "DELETE",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.text();
};

export const CATEGORY_API = {
    createCategory,
    renameCategory, 
    deleteCategory
};