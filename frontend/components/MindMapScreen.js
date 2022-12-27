import React from "react";
import { Box, Heading, HStack } from "native-base";
import { NavigationButton } from "../UI/NavigationButton";
import MindMap from "./MindMap";

const MindMapScreen = () => {
  return (
    <Box safeArea flex={1} p={2}>
      <HStack space={2} ml={2} alignItems="center">
        <NavigationButton />
        <Heading>MindMap</Heading>
      </HStack>
      <MindMap />
    </Box>
  );
};

export default MindMapScreen;
