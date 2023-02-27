import { Box } from "native-base";
import React from "react";
import SingleLineText from "./SingleLineText";

const Tag = (props) => {
  return (
    <Box
      bgColor="primary.accent"
      justifyContent="center"
      alignSelf="start"
      borderRadius="md"
      maxW="full"
      px={2}
      py={1}
    >
      <SingleLineText color="white" fontSize={"12"} fontFamily="bold">
        {props.children}
      </SingleLineText>
    </Box>
  );
};

export default Tag;
