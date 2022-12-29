import React, { useEffect, useState, useCallback } from "react";
import {
  Heading,
  HStack,
  Switch,
  VStack,
  Text,
  ScrollView,
  Center,
} from "native-base";
import Task from "../Task";
import TodoCategoryGroup from "./TodoCategoryGroup";

import { TODO_API } from "../../api/todo.api";
import { TASK_API } from "../../api/task.api";

const TodoList = (props) => {
  const [data, setData] = useState(DUMMY_TODOS);
  const [isGrouped, setIsGrouped] = useState(false);

  const toggleGrouping = () => {
    setIsGrouped((current) => !current);
  };

  const fetchTodoList = async () => {
    const data = await TODO_API.fetchTodoList();
    setData((current) => {
      const isGrouped = !current.isGrouped;
      return { isGrouped, data };
    });
  };

  useEffect(() => {
    // fetchTodoList();
  }, []);

  const groupedTasks = useCallback(() => {
    const none = [];

    const obj = data.reduce((groups, task) => {
      if (!task.category) {
        none.push(task);
        return groups;
      }

      groups[task.category] = [...(groups[task.category] || []), task];
      return groups;
    }, {});

    // arr will be in an array of pairs in the format
    //    [ [categoryName, categoryTasks], [.., ..], [.., ..] ]
    const arr = Object.entries(obj);

    // sort groups by name to enforce consistency
    arr.sort((a, b) => a[0].localeCompare(b[0]));
    // add uncategorized to the end
    if (none.length > 0) arr.push(["None", none]);

    return arr;
  }, [data]);

  const removeTask = (taskId) => {
    setData((data) => data.filter((task) => task.id !== taskId));
    TODO_API.removeFromTodo(taskId);
  };

  const completeTask = (taskId) => {
    // setData((data) => data.filter((task) => task.id !== taskId));
    TASK_API.tickTask(taskId);
  };

  return (
    <VStack w="full" flex={1} space={5} {...props}>
      <HStack justifyContent="space-between">
        <Heading ml={2}>Todo</Heading>
        <HStack alignItems="center" space={2}>
          <Text fontSize="12" color="gray.600">
            Group by category
          </Text>
          <Switch onToggle={toggleGrouping} isChecked={isGrouped} />
        </HStack>
      </HStack>

      {data.length === 0 ? (
        <Center w="full" flex={1}>
          <VStack alignItems="center">
            <Text italic>Todo list is empty.</Text>
            <Text italic>Add tasks from mindmap</Text>
          </VStack>
        </Center>
      ) : (
        <ScrollView>
          {isGrouped ? (
            <VStack space={4}>
              {groupedTasks().map(([categoryName, categoryTasks]) => (
                <TodoCategoryGroup
                  key={categoryName}
                  categoryName={categoryName}
                  categoryTasks={categoryTasks}
                  onTaskRemoval={removeTask}
                  onTaskCompletion={completeTask}
                />
              ))}
            </VStack>
          ) : (
            <VStack space={2}>
              {data.map((item) => (
                <Task
                  key={item.id}
                  data={item}
                  onTaskRemoval={removeTask}
                  onTaskCompletion={completeTask}
                  showCategory
                />
              ))}
            </VStack>
          )}
        </ScrollView>
      )}
    </VStack>
  );
};

export default TodoList;

const DUMMY_TODOS = [
  { id: 1, label: "Random task", type: "repeated" },
  { id: 2, label: "Study algorithms", type: "repeated", category: "College" },
  { id: 3, label: "Simple task", type: "onetime" },
  {
    id: 4,
    label: "SWE Sheet",
    type: "onetime",
    dueDate: "2 days, 23 hours, 59 mins left",
    category: "College",
  },
  {
    id: 5,
    label: "Pay internet bills",
    type: "onetime",
    dueDate: "4 days, 5 hours",
  },
  {
    id: 6,
    label: "REALLY Looooooooooooooooooooooooooooooooooong task",
    type: "repeated",
    category:
      "CollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollege",
  },
  {
    id: 7,
    label: "REALLY Looooooooooooooooooooooooooooooooooong task",
    type: "repeated",
    category:
      "CollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollegeCollege",
  },
];
