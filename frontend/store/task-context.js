import { createContext, useState } from "react";
import { Toast } from "react-native-toast-message/lib/src/Toast";
import { TASK_API } from "../api/task.api";
import { TODO_API } from "../api/todo.api";
import { CATEGORY_API } from "../api/category.api";

const TaskContext = createContext({
  todos: [],
  fetchTodo: () => {},
  addToTodo: () => {},
  removeFromTodo: () => {},
  tickTask: () => {},
  categories: [],
  unCategorized: [],
  fetchMindmap: () => {},
  addTask: () => {},
  deleteTask: () => {},
  addCategory: () => {},
  renameCategory: () => {},
  deleteCategory: () => {},
});

export const TaskContextProvider = (props) => {
  const [todos, setTodos] = useState([]);

  const fetchTodo = async (token) => {
    try {
      setTodos(await TODO_API.fetchTodoList(token));
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to fetch Todo",
      });
    }
  };
  const addToTodo = async (taskId, token) => {
    try {
      await TODO_API.addToTodo(taskId, token);
      setTodos((current) => [...current, task]);
      Toast.show({
        type: "success",
        text1: "Task added to Todo.",
      });
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to add to Todo",
      });
    }
  };

  const removeFromTodo = async (taskId, token) => {
    try {
      await TODO_API.removeFromTodo(taskId, token);
      setTodos((current) => [...current, task]);
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to remove",
      });
    }
    setTodos((current) => current.filter((task) => task.id !== taskId));
  };

  const [categories, setCategories] = useState([]);
  const [unCategorized, setUnCategorized] = useState([]);

  const fetchMindmap = async (token) => {
    try {
      setCategories(await TASK_API.fetchTasks(token));
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to fetch Tasks",
      });
    }
  };

  const addTask = (task) => {
    if (task.category === null) {
      setUnCategorized((uncategorized) => [...uncategorized, task]);
    } else {
      setCategories((categories) => {
        const category = task.category;
        category.tasks = [...category.tasks, task];
        return [...task, category];
      });
    }
  };

  const deleteTask = async (taskId) => {
    try {
      await TASK_API.deleteTask(taskId, auth.token);
      Toast.show({
        type: "success",
        text1: "Task deleted successfully.",
      });
      setCategories((current) => {
        return current
          .map((category) => ({
            ...category,
            tasks: category.tasks.filter((task) => task.id !== taskId),
          }))
          .filter((category) => category.tasks.length > 0);
      });
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "failed to delete",
      });
    }
  };

  const tickTask = async (taskId, token) => {
    try {
      await TASK_API.tickTask(taskId, token);
      Toast.show({
        type: "success",
        text1: "Task completed.",
      });
      setCategories((current) => {
        return current
          .map((category) => ({
            ...category,
            tasks: category.tasks.filter((task) => task.id !== taskId),
          }))
          .filter((category) => category.tasks.length > 0);
      });
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "failed to complete",
      });
    }
  };

  const addCategory = async (category, token) => {
    try {
      const id = await CATEGORY_API.createCategory(category, token);
      setCategories((categories) => [...categories, { ...category, id }]);
      Toast.show({
        type: "success",
        text1: "Category added succesfully.",
      });
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to add category",
      });
      console.log(error);
    }
  };

  const renameCategory = async (categoryId, newName, token) => {
    try {
      await CATEGORY_API.renameCategory(categoryId, newName, token);
      Toast.show({
        type: "success",
        text1: "Category renamed succesfully.",
      });
      setCategories((categories) =>
        categories.map((category) =>
          category.id === categoryId ? { ...category, name: newName } : category
        )
      );
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to rename category",
      });
    }
  };

  const deleteCategory = async (categoryId, token) => {
    try {
      await CATEGORY_API.deleteCategory(categoryId, token);
      setCategories((categories) =>
        categories.filter((category) => category.id !== categoryId)
      );
      Toast.show({
        type: "success",
        text1: "Category deleted.",
      });
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "Failed to delete",
      });
    }
  };

  const contextValue = {
    todos,
    fetchTodo,
    addToTodo,
    removeFromTodo,
    tickTask,
    categories,
    unCategorized,
    fetchMindmap,
    addTask,
    deleteTask,
    addCategory,
    renameCategory,
    deleteCategory,
  };

  return (
    <TaskContext.Provider value={contextValue}>
      {props.children}
    </TaskContext.Provider>
  );
};

export default TaskContext;
