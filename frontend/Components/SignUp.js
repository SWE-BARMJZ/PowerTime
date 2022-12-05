import {
  Text,
  View,
  StyleSheet,
  Image,
  TextInput,
  Pressable,
  Platform,
} from "react-native";
import React, { useState } from "react";
import Field from "../UI/Field";
import Title from "../UI/Title";
import { registerUser } from "../api/user.api";

const logoPath = require("../assets/images/LOGO.png");

export const SignUp = ({ navigation }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const emailChangeHandler = (str) => setEmail(str);
  const passwordChangeHandler = (str) => setPassword(str);
  const firstNameChangeHandler = (str) => setFirstName(str);
  const lastNameChangeHandler = (str) => setLastName(str);

  const signupHandler = async () => {
    validate();
    try {
      const response = await registerUser({ email, password, firstName, lastName });
      const msg = await response.text();
      console.log(msg)
      goToLogin();
    } catch (error) {
      console.log(error)
    }
  };

  const validate = () => {};

  const goToLogin = () => {
    navigation.navigate("Login");
  }

  return (
    <View style={Styles.device}>
      <View style={Styles.container}>
        <Image source={logoPath} style={Styles.logo} />
        
        <Title>Sign Up</Title>
        <Field name={"Email"} onChangeText={emailChangeHandler} />
        <Field name={"First Name"} onChangeText={firstNameChangeHandler} />
        <Field name={"Last Name"} onChangeText={lastNameChangeHandler} />
        <Field
          name={"Password"}
          onChangeText={passwordChangeHandler}
          secureTextEntry={true}
        />

        <Pressable style={Styles.button} onPress={signupHandler}>
          <Text style={Styles.buttonText}>Sign Up</Text>
        </Pressable>
        <View style={Styles.lastLine}>
          <Text style={{ fontSize: 16 }}>
            Already have an account?
            <View style={Styles.inLineLink}>
              <Pressable onPress={goToLogin}>
                <Text style={Styles.mediumLink}>Log in</Text>
              </Pressable>
            </View>
          </Text>
        </View>
      </View>
      {Platform.OS == "web" && (
        <View style={Styles.imgContainer}>
          <Image
            source={require("../assets/images/themeImage.png")}
            style={Styles.themeImage}
          />
        </View>
      )}
    </View>
  );
};

const Styles = StyleSheet.create({
  device: {
    flexDirection: "row",
    backgroundColor: "#dffaef",
    alignItems: "flex-start",
  },

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

  imgContainer: {
    marginTop: "5%",
    marginHorizontal: "5%",
    flex: 4,
    justifyContent: "center",
    backgroundColor: "#dffaef",
    height: "100%",
  },

  themeImage: {
    resizeMode: "contain",
    height: "100%",
    borderRadius: 38,
  },

  button: {
    marginVertical: "4%",
    backgroundColor: "#0066F9",
    borderRadius: 20,
    alignItems: "center",
    justifyContent: "center",
    height: Platform.OS === "web" ? "12%" : "8%",
  },

  buttonText: {
    color: "white",
    fontSize: 20,
    fontWeight: "bold",
  },

  farLink: {
    marginVertical: "4%",
    marginLeft: "5%",
    width: "50%",
  },

  lastLine: {
    marginVertical: "4%",
    justifyContent: "center",
    alignItems: "center",
  },

  smallLink: {
    fontWeight: "bold",
    textDecorationLine: "underline",
    fontSize: 14,
  },

  inLineLink: {},

  mediumLink: {
    fontWeight: "bold",
    textDecorationLine: "underline",
    fontSize: 16,
  },
});
