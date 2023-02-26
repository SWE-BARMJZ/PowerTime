import { BACKEND_URL } from "./const";

const sendRequest = async (method, url, token) => {
  const response = await fetch(url, {
    method,
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
  return response.json();
};

const endpoint = `${BACKEND_URL}/api/todos`;

const fetchTodoList = async (token) => {
  const date = new Date();
  return sendRequest("GET", `${endpoint}/${date.getTime()}`, token);
};

const addToTodo = async (taskId, token) => {
  return sendRequest("PUT", `${endpoint}/${taskId}`, token);
};

const removeFromTodo = async (task, token) => {
  const taskType = "dueDate" in task ? "onetime" : "repeated";

  const response = await fetch(`${endpoint}/${task.id}?taskType=${taskType}`, {
    method: "DELETE",
    headers: { Authorization: `Bearer ${token}` },
  });

  if (response.status !== 200) {
    throw new Error(response.status);
  }
};

export const TODO_API = { fetchTodoList, addToTodo, removeFromTodo };
