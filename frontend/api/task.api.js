const fetchTask = (id, token) => {};
const updateTask = (id, updates, token) => {};
const deleteTask = (id, token) => {};
const createTask = (task, token) => {};
const tickTask = (id, token) => {};
const untickTask = (id, token) => {};

const fetchTasks = (token) => {};
const fetchCompletedTasks = (token) => {};

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
