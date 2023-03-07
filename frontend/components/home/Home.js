import { useContext } from "react";
import { useWindowDimensions } from "react-native";

import AuthContext from "../../store/auth-context";
import { Text, Box, HStack, VStack, Center } from "native-base";

import TodoList from "../todo/TodoList";
import StarredNotes from "../notes/StarredNotes";

// dummy component
const Pomodoro = () => (
  <Box h={[100, 100, 200, 200]} w="full" bg={"primary.darkerBg"}>
    <Center flex={1}>
      <Text>Pomodoro</Text>
    </Center>
  </Box>
);

export const Home = () => {
  const auth = useContext(AuthContext);
  const name = auth.userInfo.firstName;

  const dimensions = useWindowDimensions();
  const isSmallScreen = dimensions.width <= 768;

  return (
    <Box style={{ justifyContent: "center" }} flex={1} bg={"primary.bg"}>
      {isSmallScreen ? (
        <VStack alignItems="center" space={2} flex={1}>
          <Pomodoro />
          <TodoList p={4} />
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
