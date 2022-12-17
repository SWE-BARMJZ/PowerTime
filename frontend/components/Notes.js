import React, { useContext, useState } from "react";
import { EditNote } from "./EditNote";
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
      <Box h="full" >
        <Text>Notes Component 😭</Text>
        <EditNote  />
    
      </Box>
    
    );
  };
  