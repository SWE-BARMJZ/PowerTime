const fetchTodoList = async (date, token) => {
    const url = `${BACKEND_URL}/api/todo/${date}`;
  
    const response = await fetch(url, {
      method: "GET",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();
};
const removeFromTodo = async (taskId, token) => {
    const url = `${BACKEND_URL}/api/todo/${taskId}`;
  
    const response = await fetch(url, {
      method: "PUT",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();
};
const addToTodo = async (taskId, taskType, token) => {
    const url = `${BACKEND_URL}/api/todo/${taskId}?taskType=${taskType}`;
  
    const response = await fetch(url, {
      method: "DELETE",
      headers: { Authorization: `Bearer ${token}` },
    });
  
    if (response.status !== 200) {
      throw new Error(response.status);
    }
    return response.json();
};

export const TODO_API = { fetchTodoList, addToTodo, removeFromTodo };
