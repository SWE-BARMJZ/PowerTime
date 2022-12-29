import React from "react";
import { View, Text, Center, Heading } from "native-base";
import AddTaskContent from "./AddTaskContent";

const AddTaskScreen = (props) => {
  return (
    <View flex={1}>
      <Center flex={1} p={6}>
        <Heading mb={6}>Add task</Heading>
        <AddTaskContent />
        
      </Center>
    </View>
  );
};

export default AddTaskScreen;
