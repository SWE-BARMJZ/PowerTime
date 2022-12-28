import { Box, VStack,Hidden } from "native-base";
import React from "react";
import TodoList from "./TodoList";
import CompletedList from "./CompletedList";
import { useState } from "react";


const TodoScreen = () => {
  const [showCompleted,setShowCompleted] = useState(true)

  return (
    <VStack safeArea bgColor={"white"}  justifyContent="center">
      <Box flex={1} flexDirection={"row"} justifyContent="center" overflow={"hidden"}>
        <TodoList 
        maxW={600}
        overflow="hidden"
        showCompleted={showCompleted}
        setShowCompleted={setShowCompleted} />
        <Hidden from="base" till="md">
          {
            showCompleted &&
            <CompletedList maxW={600} /> 
          }
        </Hidden>
      </Box>
      <Hidden from="md">
        {
          showCompleted &&
          <CompletedList maxW={600} /> 
        }
      </Hidden>
    </VStack>
  );
};

export default TodoScreen;
