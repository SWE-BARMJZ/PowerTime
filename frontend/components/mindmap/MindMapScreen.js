import React, { useState } from "react";
import { Box, Heading, HStack, Fab, Icon } from "native-base";
import { AntDesign } from "@expo/vector-icons";
import { NavigationButton } from "../../UI/NavigationButton";

import AddTaskModal from "../task/AddTaskModal";
import MindMap from "./MindMap";

const MindMapScreen = () => {
  const [isAddTaskModalVisible, setIsAddTaskModalVisible] = useState(false);

  return (
    <Box safeArea flex={1} p={2}>
      <HStack space={2} ml={5} alignItems="center">
        <NavigationButton />
        <Heading size={"lg"}>MindMap</Heading>
      </HStack>

      <MindMap />

      {/* Add task */}
      <Fab
        onPress={() => setIsAddTaskModalVisible(true)}
        renderInPortal={false}
        shadow={4}
        size="lg"
        bgColor="green.500"
        label="Add Task"
        icon={<Icon color="white" as={AntDesign} name="plus" size="sm" />}
      />
      <AddTaskModal
        visible={isAddTaskModalVisible}
        closeModalHandler={() => setIsAddTaskModalVisible(false)}
      />
    </Box>
  );
};

export default MindMapScreen;
