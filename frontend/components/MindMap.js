import React, { useContext, useState, useEffect } from "react";
import { Box, HStack, ScrollView, useToast } from "native-base";
import MindMapCategory from "./MindMapCategory";

import AuthContext from "../store/auth-context";
import { TASK_API } from "../api/task.api";
import { TODO_API } from "../api/todo.api";
import { CATEGORY_API } from "../api/category.api";

const MindMap = (props) => {
  const toast = useToast();
  const auth = useContext(AuthContext);

  const [categories, setCategories] = useState([]);

  useEffect( () => {
      const fetchTasks = async () => {
        const categoriesFromServer = await loadTasks()
        setCategories(categoriesFromServer)
      }

      fetchTasks()
    }, [])

    const loadTasks = async () => {
      const res = await TASK_API.fetchTasks(auth.token)
      const data = await res
      console.log(res);

      return data
    }

  const deleteTaskFromState = (taskId) => {
    setCategories((current) => {
      return current
        .map((category) => ({
          ...category,
          tasks: category.tasks.filter((task) => task.id !== taskId),
        }))
        .filter((category) => category.tasks.length > 0);
    });
  };

  const renameCategoryInState = (categoryId, newName) => {
    setCategories((categories) =>
      categories.map((category) =>
        category.id === categoryId ? { ...category, name: newName } : category
      )
    );
  };

  const deleteCategoryFromState = (categoryId) => {
    setCategories((categories) =>
      categories.filter((category) => category.id !== categoryId)
    );
  };

  const deleteTaskHandler = async (taskId) => {
    try {
      const response = await TASK_API.deleteTask(taskId, auth.token);
      const message = await response.text();
      toast.show({
        title: message,
        placement: "top",
      });
      deleteTaskFromState(taskId);
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const completeTaskHandler = async (taskId) => {
    try {
      const response = await TASK_API.tickTask(taskId, auth.token);
      const message = await response.text();
      toast.show({
        title: message,
        placement: "top",
      });
      deleteTaskFromState(taskId);
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const renameCategoryHandler = async (categoryId, newName) => {
    try {
      const response = await CATEGORY_API.renameCategory(categoryId, newName, auth.token);
      const message = await response;
      toast.show({
        title: message,
        placement: "top",
      });
      renameCategoryInState(categoryId, newName);
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const deleteCategoryHandler = async (categoryId) => {
    try {
      const response = await CATEGORY_API.deleteCategory(categoryId, auth.token);
      const message = await response;
      toast.show({
        title: message,
        placement: "top",
      });
      deleteCategoryFromState(categoryId);
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const moveToTodoHandler = async (taskId) => {
    try {
      const respone = await deleteTaskFromState(taskId, auth.token);
      TODO_API.addToTodo(taskId);
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  return (
    <Box flex={1} {...props}>
      <ScrollView>
        <HStack flexWrap="wrap">
          {categories.map((category) => (
            <MindMapCategory
              data={category}
              key={category.id}
              onTaskDeletion={deleteTaskHandler}
              onTaskCompletion={completeTaskHandler}
              onTaskMoveToTodo={moveToTodoHandler}
              onCategoryRename={renameCategoryHandler}
              onCategoryDelete={deleteCategoryHandler}
            />
          ))}
        </HStack>
      </ScrollView>
    </Box>
  );
};

export default MindMap;

const DUMMY_DATA = [
  {
    id: 1,
    name: "Gym",
    tasks: [
      { id: 1, label: "Push workout", type: "repeated" },
      { id: 2, label: "Renew subscription", type: "onetime", dueDate: "" },
    ],
  },
  {
    id: 2,
    name: "College",
    tasks: [
      { id: 3, label: "SWE Sheet", type: "onetime", dueDate: "" },
      { id: 4, label: "Study algo", type: "repeated" },
      { id: 5, label: "Study algo", type: "repeated" },
      { id: 6, label: "Study algo", type: "repeated" },
    ],
  },
  {
    id: 14,
    name: "College ASDASDASDASDASDASDASASDASDASDASDASDASDASASDASDASDASDASDASDASASDASDASDASDASDASDAS",
    tasks: [{ id: 6, label: "Study algo", type: "repeated" }],
  },
  {
    id: 3,
    name: "College",
    tasks: [
      { id: 7, label: "SWE Sheet", type: "onetime", dueDate: "" },
      { id: 8, label: "Study algo", type: "repeated" },
    ],
  },
  {
    id: 4,
    name: "College",
    tasks: [
      { id: 9, label: "SWE Sheet", type: "onetime", dueDate: "" },
      { id: 10, label: "Study algo", type: "repeated" },
      { id: 11, label: "Study algo", type: "repeated" },
      { id: 12, label: "Study algo", type: "repeated" },
      { id: 13, label: "Study algo", type: "repeated" },
    ],
  },
  {
    id: 5,
    name: "College",
    tasks: [
      { id: 14, label: "SWE Sheet", type: "onetime", dueDate: "" },
      { id: 15, label: "Study algo", type: "repeated" },
      { id: 16, label: "Study algo", type: "repeated" },
    ],
  },
  {
    id: 6,
    name: "College",
    tasks: [
      { id: 17, label: "SWE Sheet", type: "onetime", dueDate: "" },
      { id: 18, label: "Study algo", type: "repeated" },
    ],
  },
  {
    id: 7,
    name: "College",
    tasks: [
      { id: 19, label: "SWE Sheet", type: "onetime", dueDate: "" },
      { id: 20, label: "Study algo", type: "repeated" },
    ],
  },
  {
    id: 13,
    name: "Default",
    tasks: [{ id: 21, label: "Fix the car", type: "onetime" }],
  },
  {
    id: 8,
    name: "Default",
    tasks: [{ id: 22, label: "Fix the car", type: "onetime" }],
  },
  {
    id: 9,
    name: "Default",
    tasks: [{ id: 23, label: "Fix the car", type: "onetime" }],
  },
  {
    id: 10,
    name: "Default",
    tasks: [{ id: 24, label: "Fix the car", type: "onetime" }],
  },
  {
    id: 11,
    name: "Default",
    tasks: [{ id: 25, label: "Fix the car", type: "onetime" }],
  },
  {
    id: 12,
    name: "Default",
    tasks: [{ id: 26, label: "Fix the car", type: "onetime" }],
  },
];
