import React, { useContext, useEffect, useState } from "react";
import { MaterialIcons } from '@expo/vector-icons'; 
import { Entypo } from '@expo/vector-icons'; 
import { AntDesign } from '@expo/vector-icons';
import { FontAwesome } from '@expo/vector-icons'; 
import { respLgFont} from "./CurrentFolderContainer";
import { starNote } from "../../api/notes.api";
import AuthContext from "../../store/auth-context";



import {
    Text,
    HStack,
    VStack,
    Hidden,
    useToast,
    TextArea,
    Flex,
    Input,
    IconButton,
    Menu,
    FlatList,
    Pressable
  } from "native-base";


  export const NoteEditor = ({
    folders,
    note,
    onEdit,
    onDelete,
    onMove,
    onStar,
    iconsColor}) => {

    const auth = useContext(AuthContext)
    const toast = useToast()
    const [titleText, setTitleText] = useState("")
    const [contentText, setContentText] = useState("")
    const [starred, setStarred] = useState(note.starred)   
    
    useEffect( () => {
        setStarred(note.starred)
    }, [note])

    useEffect( () => {
        setTitleText(note.title)
        setContentText(note.content)
    }, [note])

    const alterStar = async (id) => {
        setStarred(!starred)
        onStar(id)
        const res = await starNote(id, auth.token)
        const data = await res.text()
      }

    const formatDate = (date) => {
        const dateSplitted = date.split("T")
        const dateFormatted = dateSplitted[0].split("-").reverse().join("-")
        const timeFormatted = dateSplitted[1].slice(0, 5)
        return dateFormatted + " " + timeFormatted
    }

    return (
        <VStack flex={3}>
            <HStack 
                w="full" 
                justifyContent="space-around"
                h={{base:60, md:42, lg:50}}
                borderColor="black.100"
                borderBottomWidth="2"
                borderTopWidth="2"
                >
                <Flex px="3" flex={1} alignItems="center" justifyContent="center" h="full">
                    <Text numberOfLines={1} fontSize={respLgFont}>{formatDate(note.modifiedDate)}</Text>
                </Flex>
                <Hidden from="base" till="lg">
                    <HStack w = "200" alignItems="center" justifyContent="space-between" mr="2%" h="full">
                        <IconButton
                            onPress={() => {
                                onEdit(note, titleText, contentText)
                            }}
                            icon={<FontAwesome name="save" size={30} color={iconsColor} />} />
                        <Menu w="190" trigger={triggerProps => {
                            return <IconButton
                                icon={<MaterialIcons name="drive-file-move" size={30} color={iconsColor} />}
                                    accessibilityLabel="Move Note" {...triggerProps}
                        />
                        }}>
                            <FlatList data={folders} renderItem={({item}) => 
                                <Menu.Item  onPress={() => onMove(item.id, note.id)}>
                                    {item.name}
                                </Menu.Item>} keyExtractor={item => item.id} />
                        </Menu>
                
                        <IconButton
                            onPress={() => alterStar(note.id)}
                            icon={<Entypo name={ starred ? "star" : "star-outlined"} size={30} color="#D7BE69" />} />
                        <IconButton 
                            onPress={() => onDelete(note.id)}
                            icon={<AntDesign name="delete" size={30} color="#FF5959" />} />
                    </HStack>
                </Hidden>
                <Hidden from="lg">
                    <HStack w = "70" alignItems="center" justifyContent="center" mr="2%" pr="3%">
                    <IconButton
                            onPress={() => {
                                onEdit(note, titleText, contentText)
                            }}
                            icon={<FontAwesome name="save" size={30} color={iconsColor} />} />

                    <Menu w="190" trigger={triggerProps => {
                        return <IconButton
                                icon={<Entypo name="dots-three-horizontal" size={25} color={iconsColor} />} 
                                accessibilityLabel="More options menu" {...triggerProps}
                        />
                        }}>
                            <Menu.Item onPress={() => alterStar(note.id)}>{starred ? 'Unstar Note' : 'Star Note' } </Menu.Item>
                            <Menu.Item onPress={() => onDelete(note.id)}>Delete Note</Menu.Item>
                            <Menu w="190" trigger={triggerProps => {
                            return <Pressable
                                        accessibilityLabel="More options menu"
                                        alignContent={"center"}
                                        p='3'
                                        pl='6'
                                        {...triggerProps}>
                                        <Text>Move Note</Text>
                                    </Pressable>;
                        }}>
            
                            <FlatList data={folders} renderItem={({item}) => 
                                <Menu.Item  onPress={() => onMove(item.id, note.id)}>
                                    {item.name}
                                </Menu.Item>} keyExtractor={item => item.id} />
                            </Menu>

                    </Menu>
                    </HStack>
                </Hidden>
            </HStack>
            <Input placeholder="Title" ontSize="md" fontWeight="bold" onChangeText={(text) => setTitleText(text)}  value={titleText}/>
            <TextArea flex={1} placeholder="Content" fontSize="sm" fontWeight="regular" onChangeText={(text) => setContentText(text)}  value={contentText}/>
        </VStack>
    );
  };
