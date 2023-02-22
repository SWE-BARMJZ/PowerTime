import { BACKEND_URL } from "./const";

const endpoint = `${BACKEND_URL}/api/tasks/`;


const fetchTasks = async (token) => {
  const response = await fetch(`${endpoint}`, {
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return await response.json();
};


const fetchCompletedTasks = async (token) => {
  const response = await fetch(`${endpoint}/completed`, {
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};


const createTask = async (task, taskType, token) => {
  const response = await fetch(`${endpoint}/?taskType=${taskType}`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    },
    body: JSON.stringify(task),
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }

  return await response.text();
};


const fetchTask = async (id, token) => {
  const response = await fetch(`${endpoint}/${id}`, {
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};


const updateTask = async (id, updates, token) => {
  const response = await fetch(`${endpoint}/${id}`, {
    method: "PUT",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-type": "application/json",
    },
    body: JSON.stringify(updates),
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};


const deleteTask = async (id, token) => {
  const response = await fetch(`${endpoint}/${id}`, {
    method: "DELETE",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.text();
};


const tickTask = async (id, token, date, taskType) => {
  const response = await fetch(
    `${endpoint}/${id}/tick?date=${date}&taskType=${taskType}`,
    {
      method: "PUT",
      headers: { Authorization: `Bearer ${token}` },
    }
  );

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};


const untickTask = async (id, token, date) => {
  const response = await fetch(`${endpoint}/${id}/untick?date=${date}`, {
    method: "PUT",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};


export const TASK_API = {
  fetchTask,
  updateTask,
  deleteTask,
  createTask,
  tickTask,
  untickTask,
  fetchTasks,
  fetchCompletedTasks,
};
