import React, {useState } from "react";
import { Entypo } from '@expo/vector-icons'; 
import {respMdFont } from "./CurrentFolderContainer";



import {
    Button,
    Text,
    HStack,
    VStack,
    Flex,
    Input,
    IconButton,
    Menu,
    Pressable,
    Modal,
    FormControl,
  } from "native-base";


  export const Folder = ({
    folder, onDelete, onEdit, onSelect, iconsColor}) => {

      const [showModal, setShowModal] = useState(false);
      const [newName, setNewName] = useState("")


    return (
      <Pressable 
        onPress={() => onSelect(folder)}
        _hover={{bg:"primary.evenDarkerBg"}}>
        <HStack 
          py="5"
          w="full" 
          aignItems="center"
          justifyContent="space-around"
          borderColor="black.100"
          borderBottomWidth="2"
          > 
          <Flex flex={10} ml="10%" justifyContent="center">
            <Text numberOfLines={1} fontSize={respMdFont}> 
                {folder.name} 
            </Text>
          </Flex>
          <VStack flex={1} justifyContent="center" pr="5%" >
            <Menu w="190" trigger={triggerProps => {
                return <IconButton
                        icon={<Entypo name="dots-three-horizontal" size={25} color={iconsColor} />} 
                        accessibilityLabel="More options menu" {...triggerProps}
                />
                }}>
                    <Menu.Item onPress={() => setShowModal(true)}>Rename Folder</Menu.Item>
                    <Menu.Item onPress={() => onDelete(folder.id)}>Delete Folder</Menu.Item>
            </Menu>
            <Modal isOpen={showModal} onClose={() => setShowModal(false)}>
              <Modal.Content maxWidth="300px">
                <Modal.CloseButton />
                <Modal.Header>Rename Folder</Modal.Header>
                <Modal.Body>
                  <FormControl>
                    <FormControl.Label>New Name</FormControl.Label>
                    <Input onChangeText={(text) => setNewName(text)}/>
                  </FormControl>
                </Modal.Body>
                <Modal.Footer>
                  <Button.Group space={2}>
                    <Button bg="red.500" color="white" flex={1.5} onPress={() => {
                    setShowModal(false);
                  }}>
                      Cancel
                    </Button>
                    <Button bg="#5BBA59" flex={2} onPress={() => {
                    onEdit(folder.id, newName)
                    setShowModal(false);
                  }}>
                      Save
                    </Button>
                  </Button.Group>
                </Modal.Footer>
              </Modal.Content>
            </Modal>
          </VStack>
        </HStack>
      </Pressable>
        
    );
};