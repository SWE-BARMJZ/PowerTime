import React, { useContext, useState } from "react";
import { MaterialIcons } from '@expo/vector-icons'; 
import { Entypo } from '@expo/vector-icons'; 
import { AntDesign } from '@expo/vector-icons';
import { respLgFont,respLgContHeight,respMdFont } from "../CurrentFolderContainer";



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
    Center,
    Input
  } from "native-base";


  export const Folder = ({
    navigation,
    name
  }) => {
    return (
        <HStack 
            py="5"
            w="full" 
            aignItems="center"
            justifyContent="space-around"
            borderColor="black.100"
            borderBottomWidth="2"
            > 
            <Flex ml="10%" mr="auto" justifyContent="center">
            <Text fontSize={respMdFont}> 
                {name} 
            </Text>
            </Flex>
            <VStack justifyContent="center" pr="5%">
                <Entypo name="dots-three-horizontal" size={25} color="#5BBA59" />
            </VStack>
        </HStack>
    );
};