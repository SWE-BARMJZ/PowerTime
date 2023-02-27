import React from "react";
import { Platform } from "react-native";
import { Heading, Box } from "native-base";
import { getHeaderTitle } from "@react-navigation/elements";
import { NavigationButton } from "./NavigationButton";

const Header = ({ navigation, route, options }) => {
  const title = getHeaderTitle(options, route.name);
  const isWeb = Platform.OS === "web";

  return (
    <Box
      flexDirection="row"
      alignItems="center"
      borderBottomColor="gray.200"
      borderBottomWidth={2}
      px={[2, 2, 6, 10]}
      py={2}
      safeAreaTop={!isWeb}
      safeAreaX={!isWeb}
    >
      <NavigationButton />
      <Heading size={"md"} ml={2}>
        {title}
      </Heading>
    </Box>
  );
};

export default Header;
