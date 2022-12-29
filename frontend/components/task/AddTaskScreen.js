import React from "react";
import { View, Text, Center } from "native-base";
import AddTaskContent from "./AddTaskContent";

const AddTaskScreen = (props) => {
  return (
    <View flex={1} >
      <Center flex={1} p={4}>
        <AddTaskContent />
      </Center>
    </View>
  );
};

export default AddTaskScreen;
