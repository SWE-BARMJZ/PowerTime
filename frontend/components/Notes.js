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
    Center
  } from "native-base";

  import { AntDesign } from '@expo/vector-icons'; 

  export const Notes = ({ navigation }) => {
    return (
      <HStack m={4} safeArea h="full" justifyContent="center" bg="primary.bg">
        <Hidden from="base" till="lg">
          <VStack flex={5}>
            <HStack 
              p={'1%'} 
              w="full" 
              justifyContent="space-around"
              borderColor="black.100"
              borderBottomWidth="2"
              borderLeftWidth="2"
              borderTopWidth="2">
                <Heading size={"lg"}> 
                    Folders 
                </Heading>
              <Box>
                <AntDesign name="addfolder" size={35} color="#5BBA59" />
              </Box>
            </HStack>
          </VStack>
        </Hidden>
        <VStack flex={5}>
          <HStack 
            p={'1%'} 
            w="full" 
            justifyContent="space-around"
            borderColor="black.100"
            borderBottomWidth="2"
            borderLeftWidth="2"
            borderTopWidth="2">
              <Heading size={"lg"}> 
                  Notes 
              </Heading>  
            <Box>
              <AntDesign name="addfolder" size={35} color="#5BBA59" />
            </Box>
          </HStack>
        </VStack>
        <EditNote   flex={18} bg="primary.700"/>
      </HStack>
    );
  };
  