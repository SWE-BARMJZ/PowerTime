import React, { useContext, useState } from "react";
import { FoldersContainer } from "./Subcomponents/FoldersContainer";
import { CurrentFolderContainer } from "./Subcomponents/CurrentFolderContainer";

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


  
  export const Notes = ({}) => {

    const [folders, setFolders] = useState([
      {
        id: 1,
        name: `Main Folder`
      },
      {
        id: 2,
        name: `Folder 2`
      },
      {
        id: 3,
        name: `Folder 3`
      },
      {
        id: 4,
        name: `Folder 4`
      }
    ])


    const [idCounter, setIdCounter] = useState(folders.length)
    const [selectedFolder, setSelectedFolder] = useState(null)
    

    const deleteFolder = (id) => {
      setFolders(folders.filter((folder) => folder.id !== id))
    }

    const editFolder = (id, newName) => {
      setFolders(folders.map((folder) => folder.id === id ? {...folder, name: newName} : folder) )
    }

    const addFolder = (name) => {
      const newCounter = idCounter + 1
      setIdCounter(newCounter)
      const newFolder = {id: newCounter, name: name}
      setFolders([...folders, newFolder])
    }

    const selectFolder = (folder) => {
      setSelectedFolder(folder)
      console.log("Selected Folder with ID: ", folder.id)
    }


    

    return (
      <HStack safeArea h="full" justifyContent="center" bg="primary.bg">
        <FoldersContainer 
        folders = {folders} 
        onDelete = {deleteFolder} 
        onEdit = {editFolder} 
        onAdd = {addFolder} 
        onSelect = {selectFolder} />

        {selectedFolder!==null && 
        <CurrentFolderContainer 
        folder = {selectedFolder} 
        folders={folders}/>}
      </HStack>
    );
  };
