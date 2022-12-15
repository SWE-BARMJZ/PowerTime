import {
  Text,
  View,
  StyleSheet,
  Image,
  Pressable,
  Platform,
} from "react-native";
import React, { useContext, useState } from "react";
import Field from "../UI/Field";
import Title from "../UI/Title";
import { requestToken } from "../api/auth.api";
import AuthContext from '../store/auth-context'

const logoPath = require("../assets/images/LOGO.png");

export const Login = ({ navigation }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const emailChangeHandler = (email) => setEmail(email);
  const passwordChangeHandler = (password) => setPassword(password);

  const auth = useContext(AuthContext);

  const loginHandler = async () => {
    try {
      const response = await requestToken({ email, password });
      const token = await response.text();
      console.log(token);
      auth.login(token);
      navigation.navigate("Home");
    } catch (error) {
      alert(error);
      console.log(error);
    }
  };

  const forgotPassword = () => {
    navigation.navigate("Password Recovery");
  };

  const goToSignUp = () => {
    navigation.navigate("Sign Up");
  };

  return (
    <View style={Styles.device}>
      <View style={Styles.container}>
        <Image source={logoPath} style={Styles.logo} />
        <Title>Login</Title>
        <Field name={"Email"} onChangeText={emailChangeHandler} />
        <Field
          name={"Password"}
          onChangeText={passwordChangeHandler}
          secureTextEntry={true}
        />
        <Pressable style={Styles.button} onPress={loginHandler}>
          <Text style={Styles.buttonText}>Log in</Text>
        </Pressable>

        <Pressable style={Styles.farLink} onPress={forgotPassword}>
          <Text style={Styles.smallLink}>Forgot your password?</Text>
        </Pressable>

        <View style={Styles.lastLine}>
          <Text style={{ fontSize: 16 }}>
            Don't have an account?
            <View style={Styles.inLineLink}>
              <Pressable onPress={goToSignUp}>
                <Text style={Styles.mediumLink}>Sign Up</Text>
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
    flex: 1
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
