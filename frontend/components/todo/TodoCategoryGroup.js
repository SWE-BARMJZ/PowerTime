import React from "react";
import { VStack } from "native-base";
import Task from "../task/Task";
import Tag from "../../UI/Tag";

const TodoCategoryGroup = ({
  categoryName,
  categoryTasks,
  taskRightComponent: renderTaskActions,
  ...props
}) => {
  return (
    <VStack w="full" space={1} borderRadius="lg">
      {categoryName ? <Tag size="14">{categoryName}</Tag> : <Tag>-</Tag>}
      <VStack space={1}>
        {categoryTasks.map((task) => (
          <Task
            data={task}
            key={task.id}
            rightComponent={renderTaskActions(task)}
            {...props}
          />
        ))}
      </VStack>
    </VStack>
  );
};

export default TodoCategoryGroup;
