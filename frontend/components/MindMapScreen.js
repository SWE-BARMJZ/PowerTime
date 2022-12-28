import { Box, Heading, VStack } from "native-base";
import React from "react";
import MindMap from "./MindMap";

const MindMapScreen = () => {
  return (
    <Box safeArea flex={1} p={2}>
      <Heading m={4}>MindMap</Heading>
      <MindMap />
    </Box>
  );
};

export default MindMapScreen;
