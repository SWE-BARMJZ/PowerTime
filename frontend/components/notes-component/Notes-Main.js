import React, { useContext, useState } from "react";
import { NoteEditor } from "./Subcomponents/NoteEditor";
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


  
  export const Notes = ({ navigation }) => {
    return (
      <HStack safeArea h="full" justifyContent="center" bg="primary.bg">
        <FoldersContainer/>
        <CurrentFolderContainer/>
        <NoteEditor/>
      </HStack>
    );
  };
