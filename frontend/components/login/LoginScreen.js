import React, { useContext, useState } from "react";
import { requestToken } from "../../api/auth.api";
import AuthContext from "../../store/auth-context";
import FormInput from "../../UI/FormInput";
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
} from "native-base";

const logoPath = require("../../assets/images/LOGO-g.png");

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
    <Box flex="1" safeArea bgColor={"primary.bg"}>
      <HStack justifyContent="center" flex="1" w="full">
        <VStack
          flex={1}
          space={10}
          mt={-10}
          justifyContent="center"
          alignItems="center"
        >
          <Box h={16} w="full">
            <Image source={logoPath} resizeMode="contain" h="full" alt="logo" />
          </Box>
          <VStack w="85%" maxW={400} space={3}>
            <Heading alignSelf="center" color="black">
              Login
            </Heading>

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
          <Box flex="1" h="full" mx={4}>
            <Image
              source={require("../../assets/images/themeImage.png")}
              resizeMode="contain"
              h="full"
              alt="hero"
            />
          </Box>
        </Hidden>
      </HStack>
    </Box>
  );
};
