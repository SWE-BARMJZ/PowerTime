import { useContext } from "react";
import {
  Text,
  Box,
  HStack,
  Heading,
  VStack,
  Center,
  useMediaQuery,
} from "native-base";

import AuthContext from "../../store/auth-context";
import { NavigationButton } from "../../UI/NavigationButton";
import TodoList from "../todo-component/TodoList";
import StarredNotes from "../notes-component/StarredNotes";

// dummy component
const Pomodoro = () => (
  <Box h={[100, 100, 200, 200]} w="full" bg={"primary.darkerBg"}>
    <Center flex={1}>
      <Text >Pomodoro</Text>
    </Center>
  </Box>
);

export const Home = () => {
  const auth = useContext(AuthContext);
  const name = `${auth.userInfo.firstName}`;
  console.log(auth);

  const [isSmallScreen] = useMediaQuery({
    minWidth: 0,
    maxWidth: 768,
  });

  return (
    <Box safeArea style={{ justifyContent: "center" }} flex={1} bg={"primary.bg"}>
      <HStack
        alignItems="center"
        p={2}
        borderBottomColor="gray.200"
        borderBottomWidth={2}
        px={[2, 2, 6, 10]}
      >
        <NavigationButton />
        <Heading size={"lg"}> Hello, {name} 👋</Heading>
      </HStack>

      {isSmallScreen ? (
        <VStack alignItems="center" space={2} flex={1}>
          <Pomodoro />
          <TodoList p={4}/>
        </VStack>
      ) : (
        <HStack flex={1} mt={2} p={4} px={[0, 0, 8, 12]} space={[0, 0, 8, 12]}>
          <VStack flex={1} space={"lg"}>
            <Pomodoro />
            <StarredNotes />
          </VStack>
          <TodoList flex={2} />
        </HStack>
      )}
    </Box>
  );
};
