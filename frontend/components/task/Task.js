import React from "react";
import { Text, HStack, VStack, Pressable, Box } from "native-base";
import SingleLineText from "../../UI/SingleLineText";
import Tag from "../../UI/Tag";
import RemaingTimeLabel from "./RemaingTimeLabel";

const Task = (props) => {
  const { data, showCategory, rightComponent = <></> } = props;
  const isRepeatedTask = data.sunday !== undefined;

  const viewTaskDetails = () => {};

  return (
    <HStack
      bgColor="primary.darkerBg"
      justifyContent="space-between"
      alignItems="center"
      p={3}
      py={3}
      space={2}
      borderRadius={"sm"}
      {...props}
    >
      {showCategory && data.category && <Tag>{data.category.name}</Tag>}

      <VStack space={2} flex={1} pl={1} justifyContent="center">
        {isRepeatedTask ? (
          <Text fontFamily="bold" color="primary.accent">
            Repeated
          </Text>
        ) : (
          data.dueDate && <RemaingTimeLabel dueDate={data.dueDate} />
        )}
        <HStack space={2} alignItems="center" maxW="full">
          <Pressable onPress={viewTaskDetails} flex={1}>
            <SingleLineText fontSize="md" fontFamily="md">
              {data.name}
            </SingleLineText>
            {data.description && data.description !== "" ? (
              <SingleLineText fontSize="xs">{data.description}</SingleLineText>
            ) : (
              <></>
            )}
          </Pressable>
        </HStack>
      </VStack>

      {rightComponent}
    </HStack>
  );
};

export default Task;
