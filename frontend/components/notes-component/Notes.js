import React, { useContext, useState } from "react";
import { NoteEditor } from "./NoteEditor";
import { FolderContainer } from "./FolderContainer";
import { NotesContainer } from "./NotesContainer";

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


  
  export const Notes = ({ navigation }) => {
    return (
      <HStack safeArea h="full" justifyContent="center" bg="primary.bg">
        <FolderContainer/>
        <NotesContainer/>
        <NoteEditor/>
      </HStack>
    );
  };
