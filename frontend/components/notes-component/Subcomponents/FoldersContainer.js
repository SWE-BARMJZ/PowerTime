import React, { useContext, useState } from "react";
import { AntDesign,Entypo } from '@expo/vector-icons';
import { respLgContHeight, respLgFont,respMdFont } from "./CurrentFolderContainer";
import { Folder } from "../UI-Items/Folder";


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
    IconButton,
    Modal,
    FormControl,
    Input,
    FlatList,
  } from "native-base";


  export const FoldersContainer = ({
    folders, onDelete, onEdit, onAdd, onSelect}) => {
    const [showModal, setShowModal] = useState(false)
    const [folderName, setfolderName] = useState("")
    
    
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
              <IconButton 
                icon={<AntDesign name="addfolder" size={30} color="#5BBA59" />} 
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
                <Folder folder={item} onDelete={onDelete} onEdit={onEdit} onSelect={onSelect}/>
              </Box>} keyExtractor={item => item.id} />
          </VStack>
        </Hidden>

    );
  };