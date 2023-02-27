import React, { useState } from "react";
import { Box, Fab, Icon } from "native-base";
import { AntDesign } from "@expo/vector-icons";

import AddTaskModal from "../task/AddTaskModal";
import MindMap from "./MindMap";

const MindMapScreen = () => {
  const [isAddTaskModalVisible, setIsAddTaskModalVisible] = useState(false);

  return (
    <Box flex={1} p={2} px={[0, 0, 4, 6]}>
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
