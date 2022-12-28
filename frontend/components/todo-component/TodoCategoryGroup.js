import React from "react";
import { Box, VStack } from "native-base";
import Task from "../Task";
import SingleLineText from "../../UI/SingleLineText";

const TodoCategoryGroup = ({ categoryName, categoryTasks, ...props }) => {
  return (
    <VStack w="full" space={2} p={2} borderRadius="lg" bgColor={"dark.600"}>
      <Box alignItems="center" borderRadius={"lg"} p={2}>
        <SingleLineText bold color="black" fontSize={'md'}>
          {categoryName}
        </SingleLineText>
      </Box>
      <VStack space={2}>
        {categoryTasks.map((task) => (
          <Task
            data={task}
            key={task.id}
            {...props}
          />
        ))}
      </VStack>
    </VStack>
  );
};

export default TodoCategoryGroup;
