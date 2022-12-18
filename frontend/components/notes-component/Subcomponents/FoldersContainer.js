import React, { useContext, useState } from "react";
import { AntDesign,Entypo } from '@expo/vector-icons';
import { respLgContHeight, respLgFont,respMdFont } from "./CurrentFolderContainer";
import { Folder } from "./UI Items/Folder"


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
    Flex
  } from "native-base";


  export const FoldersContainer = ({
    navigation,
  }) => {
    // const [isStarred, setIsStarred] = useState(false);
    
    return (

        <Hidden from="base" till="md">
          <VStack 
            flex={{base: 8,md:8, lg:5 }}
            borderColor="black.100"
            borderRightWidth="2"
            >
            <HStack 
              w="full" 
              h={respLgContHeight}
              aignItems="center"
              borderColor="black.100"
              borderBottomWidth="2"
              borderLeftWidth="2"
              borderTopWidth="2"> 
              <Flex ml="3%" mr="auto" justifyContent="center">
                <Text fontSize={respLgFont}> 
                    Folders 
                </Text>
              </Flex>
              <VStack justifyContent="center" pr="5%">
                <AntDesign name="addfolder" size={30} color="#5BBA59" />
              </VStack>
            </HStack>
            <Folder name="Folder1"/>
            <Folder name="Folder2"/>
            <Folder name="Folder3"/>
          </VStack>
        </Hidden>

    );
  };