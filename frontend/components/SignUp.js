import React, { useState } from "react";
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
  Pressable,
  Icon,
  useToast,
} from "native-base";
import { MaterialIcons } from "@expo/vector-icons";

import FormInput from "../UI/FormInput";
import { registerUser } from "../api/user.api";

const logoPath = require("../assets/images/LOGO.png");

export const SignUp = ({ navigation }) => {
  const toast = useToast();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const emailChangeHandler = (str) => setEmail(str);
  const passwordChangeHandler = (str) => setPassword(str);
  const firstNameChangeHandler = (str) => setFirstName(str);
  const lastNameChangeHandler = (str) => setLastName(str);
  const toggleShowPassword = () => {
    setShowPassword((current) => !current);
  };

  const signupHandler = async () => {
    validate();
    try {
      const response = await registerUser({
        email,
        password,
        firstName,
        lastName,
      });
      const message = await response.text();
      toast.show({
        title: message, 
        placement: "top",
      });
      goToLogin();
    } catch (error) {
      toast.show({
        title: error.message,
        placement: "top",
      });
    }
  };

  const validate = () => {};

  const goToLogin = () => {
    navigation.navigate("Login");
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
            <Heading alignSelf="center">Sign Up</Heading>

            <FormInput label="Email" onChange={emailChangeHandler} />
            <FormInput label="First name" onChange={firstNameChangeHandler} />
            <FormInput label="First name" onChange={lastNameChangeHandler} />
            <FormInput
              label="Password"
              onChange={passwordChangeHandler}
              inputType={showPassword ? "text" : "password"}
              InputRightElement={
                <Pressable onPress={toggleShowPassword}>
                  <Icon
                    as={
                      <MaterialIcons
                        name={showPassword ? "visibility" : "visibility-off"}
                      />
                    }
                    size={5}
                    mr={2}
                    color="black"
                  />
                </Pressable>
              }
            />

            <Button onPress={signupHandler} my={4} size={"lg"}>
              Sign Up
            </Button>

            <HStack
              alignItems="center"
              justifyContent="center"
              flexWrap="wrap"
              space="1"
            >
              <Text>Already have an account?</Text>
              <Link
                onPress={goToLogin}
                _text={{ fontWeight: "bold", color: "primary.main" }}
              >
                Log in
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
              alt="hero"
            />
          </Box>
        </Hidden>
      </HStack>
    </Box>
    // <View style={Styles.device}>
    //   <View style={Styles.container}>
    //     <Image source={logoPath} style={Styles.logo} />

    //     <Title>Sign Up</Title>
    //     <Field name={"Email"} onChangeText={emailChangeHandler} />
    //     <Field name={"First Name"} onChangeText={firstNameChangeHandler} />
    //     <Field name={"Last Name"} onChangeText={lastNameChangeHandler} />
    //     <Field
    //       name={"Password"}
    //       onChangeText={passwordChangeHandler}
    //       secureTextEntry={true}
    //     />
    //     <Pressable style={Styles.button} onPress={signupHandler}>
    //       <Text style={Styles.buttonText}>Sign Up</Text>
    //     </Pressable>
    //     <View style={Styles.lastLine}>
    //       <Text style={{ fontSize: 16 }}>
    //         Already have an account?
    //         <View style={Styles.inLineLink}>
    //           <Pressable onPress={goToLogin}>
    //             <Text style={Styles.mediumLink}>Log in</Text>
    //           </Pressable>
    //         </View>
    //       </Text>
    //     </View>
    //   </View>
    //   {Platform.OS == "web" && (
    //     <View style={Styles.imgContainer}>
    //       <Image
    //         source={require("../assets/images/themeImage.png")}
    //         style={Styles.themeImage}
    //       />
    //     </View>
    //   )}
    // </View>
  );
};

// const Styles = StyleSheet.create({
//     device: {
//       flexDirection: "row",
//       backgroundColor: "#dffaef",
//       alignItems: "flex-start",
//       flex: 1
//     },

//     container: {
//       marginHorizontal: "5%",
//       flex: 2,
//       justifyContent: "flex-start",
//       backgroundColor: "#dffaef",
//       height: "100%",
//     },

//     logo: {
//       height: "15%",
//       width: "54%",
//       resizeMode: "contain",
//     },

//     imgContainer: {
//       marginHorizontal: "5%",
//       flex: 4,
//       justifyContent: "center",
//       backgroundColor: "#dffaef",
//       height: "100%",
//     },

//     themeImage: {
//       resizeMode: "contain",
//       height: "100%",
//       borderRadius: 38,
//     },

//     button: {
//       marginVertical: "5%",
//       backgroundColor: "#0066F9",
//       borderRadius: 20,
//       alignItems: "center",
//       justifyContent: "center",
//       height: "8%",
//     },

//     buttonText: {
//       color: "white",
//       fontSize: 20,
//       fontWeight: "bold",
//     },

//     farLink: {
//       marginVertical: "4%",
//       marginLeft: "5%",
//       width: "50%",
//     },

//     lastLine: {
//       marginVertical: "4%",
//       justifyContent: "center",
//       alignItems: "center",
//     },

//     smallLink: {
//       fontWeight: "bold",
//       textDecorationLine: "underline",
//       fontSize: 14,
//     },

//     inLineLink: {},

//     mediumLink: {
//       fontWeight: "bold",
//       textDecorationLine: "underline",
//       fontSize: 16,
//     },
//   });
