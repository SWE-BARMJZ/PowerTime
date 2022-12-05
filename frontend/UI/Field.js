import {
  Text,
  View,
  StyleSheet,
  TextInput,
  Platform,
} from "react-native";
import React from "react";

function Field(props) {
  return (
    <View style={Styles.textBoxContainer}>
      <Text style={Styles.text}>{props.name}</Text>
      <TextInput
        style={Styles.textBox}
        placeholder={`Enter your ${props.name}`}
        placeholderTextColor={"gray"}
        {...props}
      />
    </View>
  );
}

const Styles = StyleSheet.create({
  text: {
    fontSize: 16,
    margin: 3,
  },

  textBoxContainer: {
    backgroundColor: "white",
    height: Platform.OS === "web" ? "15%" : "8%",
    marginVertical: "5%",
    borderRadius: 10,
    padding: "2%",
  },

  textBox: {
    paddingHorizontal: "3%",
    fontSize: 16,
  },
});

export default Field;
