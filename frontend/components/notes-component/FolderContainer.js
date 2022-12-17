import React, { useContext, useState } from "react";
import { AntDesign } from '@expo/vector-icons';
import { respLgContHeight, respLgFont } from "./NotesContainer";


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


  export const FolderContainer = ({
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
              pl={'1%'} 
              w="full" 
              h={respLgContHeight}
              aignItems="center"
              justifyContent="space-around"
              borderColor="black.100"
              borderBottomWidth="2"
              borderLeftWidth="2"
              borderTopWidth="2"> 
              <Flex alignItems="center" justifyContent="center">
                <Text fontSize={respLgFont}> 
                    Folders 
                </Text>
              </Flex>
              <VStack justifyContent="center">
                <AntDesign name="addfolder" size={30} color="#5BBA59" />
              </VStack>
            </HStack>
          </VStack>
        </Hidden>

    );
  };