import React, { useContext, useState, useEffect } from "react";
import { FoldersContainer } from "./FoldersContainer";
import { CurrentFolderContainer } from "./CurrentFolderContainer";

import AuthContext from "../../store/auth-context";
import { getFolders } from "../../api/notes.api";
import { createFolder } from "../../api/notes.api";
import { renameFolder } from "../../api/notes.api";
import { NavigationButton } from "../../UI/NavigationButton";
import { deleteFolder } from "../../api/notes.api";

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


  
  export const Notes = () => {
    const auth = useContext(AuthContext);
    const toast = useToast();
    const [folders, setFolders] = useState([])

    const FoldersCont = () => {
      return (<FoldersContainer 
                            folders = {folders} 
                            onDelete = {deleteFolderCascade} 
                            onEdit = {editFolder} 
                            onAdd = {addFolder} 
                            onSelect = {selectFolder} />)
      }

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


  useEffect(() => {
    const getFolders = async () => {
      const foldersFromServer = await loadFolders();
      setFolders(foldersFromServer);
    };

    getFolders();
  }, []);

    const deleteFolderCascade = async (id) => {
      try{
        const res = await deleteFolder(id, auth.token)
        const data = await res.text()
        setFolders(folders.filter((folder) => folder.id !== id))
        setSelectedFolder(null)
        console.log("Deleted Folder with ID: ", id)
      }
      catch(error){
        toast.show({
          title: error.message,
          placement: "top",
        });
      }
      
    }


    return data;
  };

  const [selectedFolder, setSelectedFolder] = useState(null);

  const deleteFolder = (id) => {
    setFolders(folders.filter((folder) => folder.id !== id));
    setSelectedFolder(null);
    console.log("Deleted Folder with ID: ", id);
  };

  const editFolder = async (id, newName) => {
    try {
      const res = await renameFolder(id, newName, auth.token);
      const data = await res.text();
      console.log(data);
      setFolders(
        folders.map((folder) =>
          folder.id === id ? { ...folder, name: newName } : folder
        )
      );
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const addFolder = async (name) => {
    try {
      const res = await createFolder(name, auth.token);
      const newFolder = await res.json();
      console.log(newFolder);
      setFolders([...folders, newFolder]);
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const selectFolder = (folder) => {
    setSelectedFolder(folder);
    console.log("Selected Folder with ID: ", folder.id);
  };

  const navigateToFolders = () => {
    setSelectedFolder(null);
  };

  return (
    <Box safeArea flex={1} bg={"primary.bg"} >
      <HStack ml="5" space={2} alignItems="center">
        <NavigationButton />
        <Heading size={"lg"}>Notes</Heading>
      </HStack>
      <HStack flex={1} justifyContent="center">
        {selectedFolder ? (
          <>
          <Hidden from='base' till='md'>
            <FoldersCont/>
          </Hidden>
          <CurrentFolderContainer 
          folder = {selectedFolder} 
          folders={folders}
          onDelete = {deleteFolderCascade}
          onBack = {navigateToFolders}/>

          </>
        ) : (
          <>
            <FoldersCont />
            <Hidden from="base" till="md">
              <HStack flex={2}>
                <VStack h="full" />
              </HStack>
            </Hidden>
          </>
        )}
      </HStack>
    </Box>
  );
};
