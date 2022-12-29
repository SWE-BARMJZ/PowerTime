import React from "react";
import { VStack } from "native-base";
import Task from "../Task";
import Tag from "../../UI/Tag";

const TodoCategoryGroup = ({ categoryName, categoryTasks, ...props }) => {
  return (
    <VStack w="full" space={2} borderRadius="lg" >
      <Tag>Category: {categoryName}</Tag>
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
