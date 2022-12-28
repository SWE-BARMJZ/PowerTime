import React, { useEffect, useState, useCallback } from "react";
import { Heading, HStack, Switch, VStack, Text, ScrollView } from "native-base";
import CompletedTask from "./CompletedTask";
import TodoCategoryGroup from "./TodoCategoryGroup";

import { TODO_API } from "../api/todo.api";
import { TASK_API } from "../api/task.api";

const CompletedList = (props) => {
  const [data, setData] = useState(DUMMY_COMPLETED);

  const fetchCompletedTasks = async () => {
    // const data = await TODO_API.fetchTodoList();
    // setData((current) => {
    //   const isGrouped = !current.isGrouped;
    //   return { isGrouped, data };
    // });
  };

  useEffect(() => {
    // fetchTodoList();
  }, []);

  const removeTask = (taskId) => {
    setData((data) => data.filter((task) => task.id !== taskId));
    TODO_API.removeFromTodo(taskId);
  };

  const untickTask = (taskId) => {
    setData((data) => data.filter((task) => task.id !== taskId));
    TASK_API.untickTask(taskId);
  };
  
  return (
    <VStack w="full" flex={1} px={5} py={5} space={5} {...props}>
      <HStack  justifyContent={"center"}>
        <Heading>Completed</Heading>
      </HStack>
      <ScrollView>
        <VStack space={2}>
        {data.map((item) => (
            <CompletedTask
            key={item.id}
            data={item}
            onTaskRemoval={removeTask}
            onTaskDecompletion={untickTask}
            showCategory
            />
        ))}
        </VStack>
      </ScrollView>
    </VStack>
  );
};

export default CompletedList;

const DUMMY_COMPLETED = [
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
];
