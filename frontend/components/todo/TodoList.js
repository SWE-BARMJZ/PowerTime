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
import Task from "../task/Task";
import TodoCategoryGroup from "./TodoCategoryGroup";

import { TODO_API } from "../../api/todo.api";
import { TASK_API } from "../../api/task.api";
import { useFetch } from "../../hooks/useAPI";
import { useFocusEffect } from "@react-navigation/native";

const TodoList = (props) => {
  const [isGrouped, setIsGrouped] = useState(false);
  const [callAPI] = useFetch();
  const [todos, setTodos] = useState([]);

  const updateTodos = async (promise) => {
    setTodos(await promise);
  };

  const fetchData = useCallback(() => {
    updateTodos(callAPI(TODO_API.fetchTodoList));
  }, []);

  useFocusEffect(fetchData);

  const removeTask = (task) => {
    callAPI(TODO_API.removeFromTodo, task);
    setTodos((data) => data.filter((curTask) => curTask.id !== task.id));
  };

  const completeTask = (task) => {
    setData((data) => data.filter((task) => task.id !== taskId));
    callAPI(TASK_API.tickTask, task);
  };

  const toggleGrouping = () => {
    setIsGrouped((current) => !current);
  };

  const groupByCategory = useCallback(
    (data) => {
      const none = [];

      const obj = data.reduce((groups, task) => {
        if (!task.category) {
          none.push(task);
          return groups;
        }

        groups[task.category.name] = [
          ...(groups[task.category.name] || []),
          task,
        ];
        return groups;
      }, {});

      // arr will be in an array of pairs in the format
      //    [ [category, categoryTasks], [.., ..], [.., ..] ]
      const arr = Object.entries(obj);

      // sort groups by name to enforce consistency
      arr.sort((a, b) => a[0].localeCompare(b[0]));
      // add uncategorized to the end
      if (none.length > 0) arr.push([null, none]);

      return arr;
    },
    [todos]
  );

  console.log(groupByCategory(todos));

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

      {todos.length === 0 ? (
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
              {groupByCategory(todos).map(([categoryName, categoryTasks]) => (
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
              {todos.map((todo) => (
                <Task
                  key={todo.id}
                  data={todo}
                  onTaskRemoval={() => {
                    removeTask(todo);
                  }}
                  onTaskCompletion={() => {
                    completeTask(todo);
                  }}
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
