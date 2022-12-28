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
  import SingleLineText from "../UI/SingleLineText";
  import Tag from "../UI/Tag";
  
  const CompletedTask = (props) => {
    const { data, showCategory, onTaskRemoval, onTaskDecompletion } = props;
  
    const [isDone, setIsDone] = useState(false);
    const decompleteTask = () => {
      setIsDone(current => !current);
      onTaskDecompletion(data.id);
    };
    
  
    const removeTaskHandler = () => {
      onTaskRemoval(data.id);
    };
  
    const viewTaskDetails = (taskId) => {
          // TODO
    };
  
    return (
      <HStack
        bgColor="gray.200"
        justifyContent="space-between"
        p={2}
        py={3}
        space={2}
        borderRadius={"sm"}
        {...props}
      >
        <VStack space={3} flex={1} justifyContent="center">
          <HStack space={2} pl={1} alignItems="center" maxW="full">
            <Checkbox
              size={"md"}
              onChange={decompleteTask}
              accessibilityLabel={data.label}
              defaultIsChecked 
            />
            <Pressable onPress={viewTaskDetails} flex={1}>
              <SingleLineText fontSize="md" strikeThrough={isDone}>
                {data.label}
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
  
  export default CompletedTask;
  