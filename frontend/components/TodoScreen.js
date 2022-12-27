import React from "react";
import { Box } from "native-base";
import { NavigationButton } from "../UI/NavigationButton";
import TodoList from "./TodoList";

const TodoScreen = () => {
  return (
    <Box safeArea flex={1} bgColor={"white"} alignItems="center">
      <NavigationButton />
      <TodoList maxW={600} />
    </Box>
  );
};

export default TodoScreen;
