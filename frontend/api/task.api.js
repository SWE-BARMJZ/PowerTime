import { async } from "q";
import { BACKEND_URL } from "./const";


const fetchTask = async (id, token) => {
    const url = `${BACKEND_URL}/api/task/${id}`;
  
    const response = await fetch(url, {
      method: "GET",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();
};
const updateTask = async (id, updates, token) => {
    const url = `${BACKEND_URL}/api/task/${id}`;
  
    const response = await fetch(url, {
      method: "PUT",
      headers: { 
        Authorization: `Bearer ${token}`, 
        "Content-type": "application/json" },
      body: JSON.stringify(updates),
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();
};
const deleteTask = async (id, token) => {
    const url = `${BACKEND_URL}/api/task/${id}`;
  
    const response = await fetch(url, {
      method: "DELETE",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.text();

};
const createTask = async (task, token) => {
  const url = `${BACKEND_URL}/api/task/taskType=${task}`;
  
    const response = await fetch(url, {
      method: "POST",
      headers: { 
        Authorization: `Bearer ${token}`,
        "Content-type": "application/json" },
      body: JSON.stringify(task),
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();
};
const tickTask = async (id, token, date, taskType) => {
  const url = `${BACKEND_URL}/api/task/${id}/tick?date=${date}&taskType=${taskType}`;
  
    const response = await fetch(url, {
      method: "PUT",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();

};
const untickTask = async (id, token, date) => {
  const url = `${BACKEND_URL}/api/task/${id}/untick?date=${date}`;
  
  const response = await fetch(url, {
    method: "PUT",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};

const fetchTasks = async (token) => {
  const url = `${BACKEND_URL}/api/task/`;
  
  const response = await fetch(url, {
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};
const fetchCompletedTasks = async (token) => {
  const url = `${BACKEND_URL}/api/task/completed`;
  
  const response = await fetch(url, {
    method: "GET",
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
