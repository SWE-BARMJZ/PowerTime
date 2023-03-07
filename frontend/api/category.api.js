import { BACKEND_URL } from "./const";

const sendRequest = async (method, url, token, body) => {
  const response = await fetch(url, {
    method,
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    },
    body,
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.text();
};

const endpoint = `${BACKEND_URL}/api/category`;

const fetchCategories = async (token) => {
  return sendRequest("GET", `${endpoint}/`, token);
};

const createCategory = async (categoryName, token) => {
  const category = { name: categoryName };
  return sendRequest("POST", `${endpoint}/`, token, JSON.stringify(category));
};

const renameCategory = async (categoryId, newName, token) => {
  return sendRequest("PUT", `${endpoint}/${categoryId}`, token, newName);
};

const deleteCategory = async (categoryId, token) => {
  return sendRequest("DELETE", `${endpoint}/${categoryId}`, token);
};

export const CATEGORY_API = {
  fetchCategories,
  createCategory,
  renameCategory,
  deleteCategory,
};
