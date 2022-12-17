import React, { useContext, useState } from "react";
import {
    Button,
    Text,
    Image,
    Heading,
    HStack,
    Link,
    Box,
    VStack,
    Hidden,
    useToast,
  } from "native-base";

  export const Notes = ({ navigation }) => {
    return (
      <Box>
        <Text>Notes Component 😭</Text>
        <Button my={4} size={"lg"} onPress={() => navigation.navigate("Login")}>
            Back to login
        </Button>
      </Box>
    );
  };
  