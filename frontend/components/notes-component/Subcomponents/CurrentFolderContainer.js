import { NoteEditor } from "./NoteEditor";
import React, { useContext, useState, useEffect } from "react";
import { Ionicons } from '@expo/vector-icons'; 
import { Note } from "../UI-Items/Note";
import { getNotes, MoveNoteToFolder } from "../../../api/notes.api";
import AuthContext from "../../../store/auth-context";
import { createNote } from "../../../api/notes.api";
import { starNote } from "../../../api/notes.api";
import { editNote } from "../../../api/notes.api";

export const respLgFont = {
    base: "20",
    md: "30",
    lg: "35",
}

export const respMdFont = {
  base: "15",
  md: "20",
  lg: "25",
}

export const respSmFont = {
  base: "15",
  md: "18",
  lg: "20",
}

export const respMiniFont = {
  base: "10",
  md: "13",
  lg: "15",
}

export const respLgContHeight = {
  base: 35,
  md: 42,
  lg: 50
}



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
    IconButton,
    FlatList,
    Modal,
    Input,
    FormControl,
  } from "native-base";


  export const CurrentFolderContainer = ({folder, folders,onDelete,onBack}) => {
    
    const auth = useContext(AuthContext)
    const toast = useToast()


    const [notes, setNotes] = useState([])

    useEffect( () => {
      const getNotes = async () => {
        const NotesFromServer = await loadNotes()
        setNotes(NotesFromServer)
      }

      getNotes()
    }, [folder])

    const loadNotes = async () => {
      const res = await getNotes(folder.id, auth.token)
      const data = await res.json()

      console.log(data)

      return data
    }

    const [showModal, setShowModal] = useState(false)

    const [noteTitle, setNoteTitle] = useState("")

    const [selectedNote, setSelectedNote] = useState(null)

    const selectNote = (note) => {
      setSelectedNote(note)
      console.log("Selected Note with ID: ", note.id)
    }


    const changeNote = (note, newTitle, newContent) => {
      setNotes(notes.map((item) => item.id === note.id ? {...item, title: newTitle, content: newContent, } : item) )

      // try{
      //   const newNote = note
      //   newNote.title = newTitle
      //   newNote.content = newContent

      //   const res = await editNote(newNote, folder.id, auth.token)
      //   const data = await res.json()
      //   console.log("data:", data)
      //   setNotes(notes.map((note) => note.id === id ? {...note, title: newTitle, content: newContent, } : note) )
      // }
      // catch(error){
      //   toast.show({
      //     title: error.message,
      //     placement: "top",
      //   });
      // }
    }

    const MoveNote = async (newFolderId, noteId) => {
      try{
        const res = await MoveNoteToFolder(newFolderId, noteId, auth.token)
        const newNote = await res.json()
        console.log(newNote)
        if(newFolderId !== folder.id){
          setNotes(notes.filter((note) => note.id !== noteId))
        }
      }
      catch(error){
        toast.show({
          title: error.message,
          placement: "top",
        });
      }
    }


    const addNote = async (initialTitle) => {

      try{
        const res = await createNote(folder.id, initialTitle, auth.token)
        const newNote = await res.json()
        console.log(newNote)
        setNotes([...notes, newNote])
      }
      catch(error){
        toast.show({
          title: error.message,
          placement: "top",
        });
      }
    }

    const deleteNote = (id) => {
      setNotes(notes.filter((note) => note.id !== id))
      setSelectedNote(null)
    }

    const alterStar = async (id) => {
      const res = await starNote(id, auth.token)
      const data = await res.text()
      setNotes(notes.map( (note) => note.id === id ? {...note, starred: !note.starred} : note ))
      console.log(data)
    }

    const backToFolders = () => {
      setSelectedNote(null)
      onBack()
    }
  
    
    return (
      <HStack flex={2}>
        <VStack 
        flex={{base:2, md:1, lg:1}}
        borderColor="black.100"
        borderRightWidth="2"
        >
          <HStack 
            alignContent="center"
            w="full" 
            h={{base:60, md:42, lg:50}}
            justifyContent="space-around"
            borderColor="black.100"
            borderBottomWidth="2"
            borderTopWidth="2">
              <Flex ml="3%" flex={4} justifyContent="center">
                <Text numberOfLines={1} fontSize={respLgFont}>{folder.name}</Text>
              </Flex>
              <VStack w="50" justifyContent="center">
              <IconButton 
                onPress={()=> setShowModal(true)}
                icon={<Ionicons name="add-circle-sharp" size={30} color="#5BBA59" />} />
                <Modal isOpen={showModal} onClose={() => setShowModal(false)}>
                  <Modal.Content maxWidth="300px">
                    <Modal.CloseButton />
                    <Modal.Header>Create Note</Modal.Header>
                    <Modal.Body>
                      <FormControl>
                        <FormControl.Label>Title</FormControl.Label>
                        <Input 
                        onChangeText={(text) => setNoteTitle(text)} 
                        value= {noteTitle}/>
                      </FormControl>
                    </Modal.Body>
                    <Modal.Footer>
                      <Button.Group space={2}>
                        <Button bg="#FF5959" color="white" flex={1.5} onPress={() => {
                          setNoteTitle("")
                        setShowModal(false)
                      }}>
                          Cancel
                        </Button>
                        <Button bg="#5BBA59" flex={2} onPress={() => {
                        addNote(noteTitle)
                        setNoteTitle("")
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
          <FlatList data={notes} renderItem={({item}) => 
              <Box>
                <Note note={item} onSelect={selectNote}/>
              </Box>} keyExtractor={item => item.id} />
          <Hidden from="md">
            <Button m="3" bgColor="#5BBA59" onPress={() => backToFolders()} size={"lg"}>
              Back To folders
            </Button>
            <Button m="3" bgColor="#FF5959" onPress={() => onDelete(folder.id)} size={"lg"}>
              Delete folder
            </Button>
          </Hidden>
        </VStack>

        {selectedNote 
        ? 
        <NoteEditor 
        folders={folders} 
        note = {selectedNote} 
        onEdit={changeNote}
        onDelete={deleteNote}
        onStar = {alterStar}
        onMove = {MoveNote}/> 
        :
        <VStack flex={3}>
            <HStack w="full"></HStack>
        </VStack>
        }
      </HStack>
        
    );
  };
