import React from "react";
import { Box, Heading, HStack, Fab, Icon } from "native-base";
import { NavigationButton } from "../../UI/NavigationButton";
import { AntDesign } from "@expo/vector-icons";
import MindMap from "./MindMap";
import AddTaskModal from "../task/AddTaskModal";

const MindMapScreen = () => {
  const [visible, setVisible] = React.useState(false);

  return (
    <Box safeArea flex={1} px={4}>
      <HStack space={2} ml={2} alignItems="center" py={4}>
        <NavigationButton />
        <Heading>MindMap</Heading>
      </HStack>
      <MindMap />

      {/* Add task */}
      <Fab
        onPress={() => setVisible(true)}
        renderInPortal={false}
        shadow={4}
        size="lg"
        bgColor='green.500'
        label="Add Task"
        icon={<Icon color="white" as={AntDesign} name="plus" size="sm" />}
      />
      <AddTaskModal visible={visible} onClose={() => setVisible(false)} />
    </Box>
  );
};

export default MindMapScreen;
