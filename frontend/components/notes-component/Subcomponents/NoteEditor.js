import React, { useContext, useEffect, useState } from "react";
import { MaterialIcons } from '@expo/vector-icons'; 
import { Entypo } from '@expo/vector-icons'; 
import { AntDesign } from '@expo/vector-icons';
import { FontAwesome } from '@expo/vector-icons'; 
import { respLgFont,respLgContHeight } from "./CurrentFolderContainer";



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
    IconButton,
    Menu,
    FlatList,
  } from "native-base";


  export const NoteEditor = ({
    folders, note, onEdit, onDelete
  }) => {
    const [isStarred, setIsStarred] = useState(false);
    const [titleText, setTitleText] = useState("")
    const [contentText, setContentText] = useState("")
    useEffect( () => {
        setTitleText(note.title)
        setContentText(note.content)
    }, [note])


    return (
        <VStack flex={18}>
            <HStack 
                w="full" 
                justifyContent="space-around"
                h={respLgContHeight}
                borderColor="black.100"
                borderBottomWidth="2"
                borderTopWidth="2"
                >
                <Flex flex="4" alignItems="center" justifyContent="center" h="full">
                    <Text fontSize={respLgFont}>{note.date}</Text>
                </Flex>
                <Hidden from="base" till="md">
                    <HStack flex="1" alignItems="center" justifyContent="space-between" mr="2%" h="full">
                        <IconButton
                            onPress={() => onEdit(note.id, titleText, contentText)}
                            icon={<FontAwesome name="save" size={24} color="black" />} />
                        <Menu w="190" trigger={triggerProps => {
                            return <IconButton
                                icon={<MaterialIcons name="drive-file-move" size={30} color="#5BBA59" />}
                                    accessibilityLabel="Move Note" {...triggerProps}
                        />
                        }}>
                            <FlatList data={folders} renderItem={({item}) => 
                                <Menu.Item>
                                    {item.name}
                                </Menu.Item>} keyExtractor={item => item.id} />
                        </Menu>
                
                        <IconButton
                            onPress={() => setIsStarred(!isStarred)}
                            icon={<Entypo name={ isStarred ? "star" : "star-outlined"} size={30} color="#D7BE69" />} />
                        <IconButton 
                            onPress={() => onDelete(note.id)}
                            icon={<AntDesign name="delete" size={30} color="#FF5959" />} />
                    </HStack>
                </Hidden>
                <Hidden from="md">
                    <HStack flex="1" alignItems="center" justifyContent="center" mr="2%">
                    <IconButton icon={<Entypo name="dots-three-horizontal" size={25} color="#5BBA59" />} />
                    </HStack>
                </Hidden>
            </HStack>
            <Input placeholder="Title" ontSize="md" fontWeight="bold" onChangeText={(text) => setTitleText(text)}  value={titleText}/>
            <TextArea flex={1} placeholder="Content" fontSize="sm" fontWeight="regular" onChangeText={(text) => setContentText(text)}  value={contentText}/>
        </VStack>
    );
  };
