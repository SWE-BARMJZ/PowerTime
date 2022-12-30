import { FlatList, VStack, Heading, Box } from "native-base";
import React from "react";
import SingleLineText from "../../UI/SingleLineText";
import { getStarredNotes } from "../../api/notes.api";
import AuthContext from "../../store/auth-context";
import { useContext, useState, useEffect } from "react";

const StarredNotes = (props) => {

  const auth = useContext(AuthContext);

  const [starrredNotes, setStarredNotes] = useState([])

  useEffect( () => {
    const getStarredNotes = async () => {
      const starredNotesFromServer = await loadStarredNotes()
      setStarredNotes(starredNotesFromServer)
    }

    getStarredNotes()
  }, [])

  const loadStarredNotes = async () => {
    const res = await getStarredNotes(auth.token)
    const data = await res.json()

    console.log(data)

    return data
  }

  return (
    <VStack flex={1} w="full" space={4} {...props}>
      <Heading ml={2} fontFamily={"heading"}>Starred notes</Heading>

      <FlatList
        data={starrredNotes}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <VStack p={3} mb={2}  borderWidth={1}
          borderColor="gray.200">
            <SingleLineText fontWeight="bold">{item.title}</SingleLineText>
            <SingleLineText numberOfLines={3}>{item.content}</SingleLineText>
          </VStack>
        )}
        
      />
    </VStack>
  );
};

export default StarredNotes;

