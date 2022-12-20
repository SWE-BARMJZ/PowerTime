import React, { useContext, useState, useEffect } from "react";
import { FoldersContainer } from "./Subcomponents/FoldersContainer";
import { CurrentFolderContainer } from "./Subcomponents/CurrentFolderContainer";
import { createFolder } from "../../api/notes.api";
import AuthContext from "../../store/auth-context";
import { getFolders } from "../../api/notes.api";
import { renameFolder } from "../../api/notes.api";

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


  
  export const Notes = ({navigation}) => {
    const auth = useContext(AuthContext);
    const toast = useToast();
    const [folders, setFolders] = useState([])

    useEffect( () => {
      const getFolders = async () => {
        const foldersFromServer = await loadFolders()
        setFolders(foldersFromServer)
      }

      getFolders()
    }, [])

    const loadFolders = async () => {
      const res = await getFolders(auth.token)
      const data = await res.json()

      return data
    }


    const [idCounter, setIdCounter] = useState(folders.length)
    const [selectedFolder, setSelectedFolder] = useState(null)
    

    const deleteFolder = (id) => {
      setFolders(folders.filter((folder) => folder.id !== id))
    }

    const editFolder = async (id, newName) => {
      try{
        const res = await renameFolder(id, newName, auth.token)
        const data = await res.text()
        console.log(data)
        setFolders(folders.map((folder) => folder.id === id ? {...folder, name: newName} : folder) )
      }
      catch(error){
        toast.show({
          title: error.message,
          placement: "top",
        });
      }
    }

    const addFolder = async (name) => {
  
      try{
        const res = await createFolder(name, auth.token)
        const newFolder = await res.json()
        console.log(newFolder)
        setFolders([...folders, newFolder])
      }
      catch(error){
        toast.show({
          title: error.message,
          placement: "top",
        });
      }
      
    }

    const selectFolder = (folder) => {
      setSelectedFolder(folder)
      console.log("Selected Folder with ID: ", folder.id)
    }


    

    return (
      <HStack safeArea h="full" justifyContent="center" bg="primary.bg">
        <FoldersContainer folders = {folders} onDelete = {deleteFolder} onEdit = {editFolder} onAdd = {addFolder} onSelect = {selectFolder} />
        {selectedFolder!==null && <CurrentFolderContainer folder = {selectedFolder} folders={folders}/>}
      </HStack>
    );
  };
