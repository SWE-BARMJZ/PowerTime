import {
  Button,
  Text,
  View,
} from "react-native";
import React from "react";

export const PasswordRecovery = ({ navigation }) => {
  return (
    <View>
      <Text>Sorry for your loss 😢</Text>
      <Button
        title="Back to login"
        onPress={() => navigation.navigate("Login")}
      />
    </View>
  );
};
