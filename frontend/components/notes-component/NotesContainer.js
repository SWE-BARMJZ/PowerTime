import React, { useContext, useState } from "react";
import { Ionicons } from '@expo/vector-icons'; 

export const respLgFont = {
    base: "20",
    md: "30",
    lg: "35",
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


  export const NotesContainer = ({
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
            pl={'1%'} 
            w="full" 
            h={respLgContHeight}
            justifyContent="space-around"
            borderColor="black.100"
            borderBottomWidth="2"
            borderTopWidth="2">
              <Flex alignItems="center" justifyContent="center" h="full">
                <Text fontSize={respLgFont}>Notes</Text>
              </Flex>
              <VStack justifyContent="center" h="full">
                <Ionicons name="add-circle-sharp" size={30} color="#5BBA59" />
              </VStack>
          </HStack>
        </VStack>
    );
  };
