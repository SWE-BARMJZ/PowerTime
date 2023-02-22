import React, { useState } from "react";
import { AntDesign} from '@expo/vector-icons';
import { respLgContHeight, respLgFont} from "./CurrentFolderContainer";
import { Folder } from "./Folder";


import {
    Button,
    Text,
    HStack,
    Box,
    VStack,
    Flex,
    IconButton,
    Modal,
    FormControl,
    Input,
    FlatList,
  } from "native-base";

  export const FoldersContainer = ({
    folders, onDelete, onEdit, onAdd, onSelect, iconsColor}) => {
    const [showModal, setShowModal] = useState(false)
    const [folderName, setfolderName] = useState("")
    
    
    return (

          <VStack 
            flex={1}
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
              <Flex flex={10} ml="3%" justifyContent="center">
                <Text numberOfLines={1} fontSize={respLgFont}> 
                    Folders 
                </Text>
              </Flex>
              <VStack flex={1} justifyContent="center" pr="5%">
              <IconButton 
                icon={<AntDesign name="addfolder" size={30} color={iconsColor} />} 
                onPress={() => setShowModal(true)}
                />
                <Modal isOpen={showModal} onClose={() => setShowModal(false)}>
                  <Modal.Content maxWidth="300px">
                    <Modal.CloseButton />
                    <Modal.Header>Create Folder</Modal.Header>
                    <Modal.Body>
                      <FormControl>
                        <FormControl.Label>Name</FormControl.Label>
                        <Input 
                        onChangeText={(text) => setfolderName(text)} 
                        value= {folderName}/>
                      </FormControl>
                    </Modal.Body>
                    <Modal.Footer>
                      <Button.Group space={2}>
                        <Button bg="red.500" color="white" flex={1.5} onPress={() => {
                          setfolderName("")
                        setShowModal(false)
                      }}>
                          Cancel
                        </Button>
                        <Button bg="#5BBA59" flex={2} onPress={() => {
                        onAdd(folderName)
                        setfolderName("")
                        setShowModal(false)
                      }}>
                          Save
                        </Button>
                      </Button.Group>
                    </Modal.Footer>
                  </Modal.Content>
                </Modal>
              </VStack>
            </HStack>
            <FlatList data={folders} renderItem={({item}) => 
              <Box>
                <Folder folder={item} onDelete={onDelete} onEdit={onEdit} onSelect={onSelect} iconsColor={iconsColor}/>
              </Box>} keyExtractor={item => item.id} />
          </VStack>
    );
  };