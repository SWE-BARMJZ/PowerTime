import React, { useContext, useState } from "react";
import { MaterialIcons } from '@expo/vector-icons'; 
import { Entypo } from '@expo/vector-icons'; 
import { AntDesign } from '@expo/vector-icons';
import { respLgFont,respLgContHeight,respMdFont,respSmFont,respMiniFont } from "../Subcomponents/CurrentFolderContainer";



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
    Input,
    Pressable,
    
  } from "native-base";


  export const Note = ({
    note, onSelect
  }) => {



    return (
        
        
        <Pressable 
        onPress={() => onSelect(note)}
        _hover={{bg:"gray.300"}}
        w="full" 
        h = "20"
        borderColor="black.100"
        borderBottomWidth="2"
        >
        <VStack w="full" justifyContent="center">
            <Text md="0" ml="10%" fontSize={{base:20,md:23,lg:25}}> 
                {note.title} 
            </Text>
            <Text numberOfLines={2} ml="20%" fontSize={respSmFont} color="gray.500">   
                {note.date} 
            </Text>
        </VStack>
        </Pressable>
        
    
        
    );
};
