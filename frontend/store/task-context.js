import { createContext, useState } from "react";

const TaskContext = createContext({
  data: undefined,
  setData: () => {},

  addTask: (task) => {},
  deleteTask: (task) => {},
  setTaskAsTodo: (task) => {},

  getAllCategories: () => {},
  addCategory: (category) => {},
  renameCategory: (id, newName) => {},
  deleteCategory: (id) => {},
});

export const TaskContextProvider = (props) => {
  const [data, setData] = useState([]);

  const addTask = (task) => {
    setData((groups) =>
      groups.map((group) => {
        if (group.category === task.category) {
          return { ...group, tasks: [...group.tasks, task] };
        }
        return group;
      })
    );
  };

  const deleteTask = (task) => {
    setData((groups) =>
      groups.map((group) => {
        if (
          group.category === task.category ||
          group.category?.id === task.category?.id
        ) {
          return {
            ...group,
            tasks: group.tasks.filter((curTask) => curTask.id !== task.id),
          };
        }
        return group;
      })
    );
  };

  const setTaskAsTodo = (task) => {
    setData((groups) =>
      groups.map((group) => {
        if (
          group.category === task.category ||
          group.category?.id === task.category?.id
        ) {
          return {
            ...group,
            tasks: group.tasks.map((curTask) => {
              if (curTask.id !== task.id) return curTask;
              return { ...curTask, todo: true };
            }),
          };
        }
        return group;
      })
    );
  };

  const getAllCategories = () => {
    return data
      .filter((group) => group.category !== null)
      .map((group) => group.category);
  };

  const addCategory = (category) => {
    const group = {
      category,
      tasks: [],
    };
    setData((groups) => [...groups, group]);
  };

  const renameCategory = (id, newName) => {
    setData((groups) =>
      groups.map((group) => {
        if (group.category?.id === id) {
          const updated = { ...group };
          updated.category.name = newName;
          return updated;
        }
        return group;
      })
    );
  };

  const deleteCategory = (id) => {
    setData((groups) =>
      groups.filter((group) => !group.category || group.category.id !== id)
    );
  };

  const contextValue = {
    data,
    setData,

    addTask,
    deleteTask,
    setTaskAsTodo,
    getAllCategories,
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
