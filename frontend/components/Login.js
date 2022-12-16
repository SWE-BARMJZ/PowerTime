import { StyleSheet } from "react-native";
import React, { useContext, useState } from "react";
import Field from "../UI/Field";
import Title from "../UI/Title";
import { requestToken } from "../api/auth.api";
import AuthContext from "../store/auth-context";
import FormInput from "../UI/FormInput";
import {
  Button,
  Text,
  Image,
  Center,
  Heading,
  HStack,
  Link,
  Box,
  VStack,
  ScrollView,
  Hidden,
  useToast,
  View,
} from "native-base";

const logoPath = require("../assets/images/LOGO.png");

export const Login = ({ navigation }) => {
  const toast = useToast();
  const auth = useContext(AuthContext);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const emailChangeHandler = (email) => setEmail(email);
  const passwordChangeHandler = (password) => setPassword(password);

  const loginHandler = async () => {
    try {
      const response = await requestToken({ email, password });
      const token = await response.text();
      auth.login(token);
      navigation.navigate("Home");
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const forgotPassword = () => {
    navigation.navigate("Password Recovery");
  };

  const goToSignUp = () => {
    navigation.navigate("Sign Up");
  };

  return (
    <Box flex="1" safeArea bgColor={"#DFFAF0"}>
      <HStack justifyContent="center" flex="1" w="full">
        <VStack
          flex={1}
          space={10}
          mt={-10}
          justifyContent="center"
          alignItems="center"
        >
          <Box h={16} w="full">
            <Image source={logoPath} resizeMode="contain" h="full" />
          </Box>
          <VStack w="85%" maxW={400} space={3}>
            <Heading alignSelf="center">Login</Heading>

            <FormInput label="Email" onChange={emailChangeHandler} />
            <FormInput
              label="Password"
              inputType="password"
              onChange={passwordChangeHandler}
            />

            <Link onPress={forgotPassword} alignSelf={"start"}>
              Forgot your password?
            </Link>

            <Button onPress={loginHandler} my={4} size={"lg"}>
              Log in
            </Button>

            <HStack
              alignItems="center"
              justifyContent="center"
              flexWrap="wrap"
              space="1"
            >
              <Text>Don't have an account?</Text>
              <Link
                onPress={goToSignUp}
                _text={{ fontWeight: "bold", color: "primary.main" }}
              >
                Sign up
              </Link>
            </HStack>
          </VStack>
        </VStack>

        <Hidden from="base" till="lg">
          <Box flex="1" h="full">
            <Image
              source={require("../assets/images/themeImage.png")}
              resizeMode="contain"
              h="full"
            />
          </Box>
        </Hidden>
      </HStack>
    </Box>
  );
};

const Styles = StyleSheet.create({
  container: {
    marginHorizontal: "5%",
    flex: 2,
    justifyContent: "flex-start",
    backgroundColor: "#dffaef",
    height: "100%",
  },

  logo: {
    height: "15%",
    width: "54%",
    resizeMode: "contain",
  },

  button: {
    marginVertical: "5%",
    backgroundColor: "#0066F9",
    borderRadius: 20,
    alignItems: "center",
    justifyContent: "center",
    height: "8%",
  },

  buttonText: {
    color: "white",
    fontSize: 20,
    fontWeight: "bold",
  },

  smallLink: {
    fontWeight: "bold",
    textDecorationLine: "underline",
    fontSize: 14,
  },

  mediumLink: {
    fontWeight: "bold",
    textDecorationLine: "underline",
    fontSize: 16,
  },
});
