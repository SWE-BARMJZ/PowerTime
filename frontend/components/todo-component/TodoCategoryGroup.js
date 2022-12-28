import React from "react";
import { Box, VStack } from "native-base";
import Task from "../Task";
import SingleLineText from "../../UI/SingleLineText";

const TodoCategoryGroup = ({ categoryName, categoryTasks, ...props }) => {
  return (
    <VStack w="full" bgColor={"indigo.200"} borderRadius={"lg"}>
      <Box bg="primary.accent" alignItems="center" borderRadius={"lg"} p={3}>
        <SingleLineText bold color="white">
          {categoryName}
        </SingleLineText>
      </Box>
      <VStack space={2} p={2}>
        {categoryTasks.map((task) => (
          <Task
            data={task}
            key={task.id}
            borderWidth={1}
            borderColor="white"
            {...props}
          />
        ))}
      </VStack>
    </VStack>
  );
};

export default TodoCategoryGroup;
