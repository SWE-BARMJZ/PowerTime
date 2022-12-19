import { Box } from "native-base";
import React from "react";
import TodoList from "./TodoList";

const TodoScreen = () => {
  return (
    <Box safeArea flex={1} bgColor={"white"} alignItems="center">
      <TodoList maxW={600} />
    </Box>
  );
};

export default TodoScreen;
