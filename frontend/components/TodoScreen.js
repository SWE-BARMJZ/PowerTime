import { Box } from "native-base";
import React from "react";
import TodoList from "./TodoList";
import CompletedList from "./CompletedList";

const TodoScreen = () => {
  return (
    <Box safeArea flex={1} bgColor={"white"} alignItems="center">
      <TodoList maxW={600} />
      <CompletedList maxW={600} /> 
    </Box>
  );
};

export default TodoScreen;
