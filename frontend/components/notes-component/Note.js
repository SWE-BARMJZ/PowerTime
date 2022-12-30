import React, { useContext, useState } from "react";
import { respLgFont,respLgContHeight,respMdFont,respSmFont,respMiniFont } from "./CurrentFolderContainer";



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

    const formatDate = (date) => {
        const dateSplitted = date.split("T")
        const dateFormatted = dateSplitted[0].split("-").reverse().join("-")
        const timeFormatted = dateSplitted[1].slice(0, 5)
        return dateFormatted + " " + timeFormatted
    }

    return (
        
        <Pressable 
            onPress={() => {
                onSelect(note)
            }}
            _hover={{bg:"gray.300"}}
            w="full" 
            borderColor="black.100"
            borderBottomWidth="2"
            >
            <VStack w="full" justifyContent="center" alignContent={"center"}>
                <Text numberOfLines={1} md="0" ml="10%" fontSize={{base:20,md:23,lg:25}}> 
                    {note.title} 
                </Text>
                <Text numberOfLines={1} ml="20%" fontSize={respSmFont} color="gray.500">   
                    {formatDate(note.modifiedDate)} 
                </Text>
            </VStack>
        </Pressable>
        
    
        
    );
};
export const noteSize = {
    base: 40,
    md: 45,
    lg: 50,
  }
