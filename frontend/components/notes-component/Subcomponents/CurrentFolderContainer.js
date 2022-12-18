import React, { useContext, useState } from "react";
import { Ionicons } from '@expo/vector-icons'; 
import { Note } from "../UI-Items/Note";

export const respLgFont = {
    base: "20",
    md: "30",
    lg: "35",
}

export const respMdFont = {
  base: "15",
  md: "20",
  lg: "25",
}

export const respSmFont = {
  base: "15",
  md: "18",
  lg: "20",
}

export const respMiniFont = {
  base: "10",
  md: "13",
  lg: "15",
}

export const respLgContHeight = {
  base: 35,
  md: 42,
  lg: 50
}


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
    TextArea,
    Icon,
    Flex,
    center
  } from "native-base";


  export const CurrentFolderContainer = ({
    navigation,
  }) => {
    // const [isStarred, setIsStarred] = useState(false);
    
    return (

      <VStack 
        flex={{base: 8,md:8, lg:5 }}
        borderColor="black.100"
        borderRightWidth="2"
        >
          <HStack 
            alignContent="center"
            w="full" 
            h={respLgContHeight}
            justifyContent="space-around"
            borderColor="black.100"
            borderBottomWidth="2"
            borderTopWidth="2">
              <Flex ml="3%" mr="auto" justifyContent="center">
                <Text fontSize={respLgFont}>Folder1</Text>
              </Flex>
              <VStack justifyContent="center" pr="5%">
                <Ionicons name="add-circle-sharp" size={30} color="#5BBA59" />
              </VStack>
          </HStack>
          <Note title="Note1" date="embare7"/>
          <Note title="Note2" date="awel embare7"/>
          <Note title="Note3" date="awel awel embare7"/>
          <Note title="Note4" date="awel awel awel embare7"/>
        </VStack>
    );
  };
