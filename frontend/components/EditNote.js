import React, { useContext, useState } from "react";
import { MaterialIcons } from '@expo/vector-icons'; 
import { Entypo } from '@expo/vector-icons'; 
import { AntDesign } from '@expo/vector-icons';



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
  } from "native-base";


  export const EditNote = ({
    navigation,
    ...props
  }) => {

    return (
        <VStack space={0} >
            <HStack h = "30"  justifyContent="space-between" alignItems="center">
                <Text ml="10" fontWeight="semibold">Date Modified</Text>
                <HStack alignItems="center"  space={10} justifyContent="space-between" mr="5">
                    <MaterialIcons name="drive-file-move" size={24} color="black" />
                    <Entypo name="star-outlined" size={24} color="black" />
                    <AntDesign name="delete" size={24} color="black" />
                </HStack>
                
            </HStack>
            <TextArea h="34" numberOfLines={1} placeholder="Title"  fontSize="md"  fontWeight="bold" pb="5"/>
            <TextArea flex={1} placeholder="Content" fontSize="sm" fontWeight="regular" />

        </VStack>
    );
  };