import {
  Text,
  HStack,
  Checkbox,
  VStack,
  CloseIcon,
  IconButton,
  Pressable,
} from "native-base";
import React, { useState } from "react";
import SingleLineText from "../../UI/SingleLineText";
import Tag from "../../UI/Tag";

const Task = (props) => {
  const { data, showCategory, onTaskRemoval, onTaskCompletion } = props;

  const [isDone, setIsDone] = useState(false);
  const completeTask = () => {
    setIsDone((current) => !current);
    onTaskCompletion(data.id);
  };


  const removeTaskHandler = () => {
    onTaskRemoval(data.id);
  };

  // TODO
  const viewTaskDetails = () => {};

  return (
    <HStack
      bgColor="primary.darkerBg"
      justifyContent="space-between"
      p={2}
      py={3}
      space={2}
      borderRadius={"sm"}
      {...props}
    >
      <VStack space={3} flex={1} justifyContent="center">
        {data.type === "repeated" ? (
          <Text fontSize={"12"} ml={1} color="primary.accent">
            REPEATED TASK
          </Text>
        ) : (
          data.dueDate && (
            <Text fontSize={"12"} ml={1} color="primary.accent">
              {data.dueDate}
            </Text>
          )
        )}

        <HStack space={2} pl={1} alignItems="center" maxW="full">
          <Checkbox
            size={"md"}
            onChange={completeTask}
            accessibilityLabel={data.label}
          />
          <Pressable onPress={viewTaskDetails} flex={1}>
            <SingleLineText fontSize="md" strikeThrough={isDone}>
              {data.taskName}
            </SingleLineText>
          </Pressable>
        </HStack>

        {showCategory && data.category && <Tag>Category: {data.category}</Tag>}
      </VStack>
      <IconButton
        icon={<CloseIcon />}
        colorScheme="red"
        onPress={removeTaskHandler}
      />
    </HStack>
  );
};

export default Task;
