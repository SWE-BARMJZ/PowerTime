import {
  Text,
  View,
  StyleSheet,
  TextInput,
  Pressable,
  
} from "react-native";
import React, { useRef } from "react";

function Field(props) {
  const textboxRef=useRef()

  return (
    <Pressable onPress={()=>  textboxRef.current.focus()}>
      <View style={Styles.textBoxContainer}>
        <Text style={Styles.text}>{props.name}</Text>
        <TextInput
          ref={textboxRef}
          style={Styles.textBox}
          placeholder={`Enter your ${props.name}`}
          placeholderTextColor={"gray"}
          {...props}
        />
      </View>
    </Pressable>
  );
}

const Styles = StyleSheet.create({
  text: {
    fontSize: 16,
    margin: 3,
  },

  textBoxContainer: {
    backgroundColor: "white",
    marginVertical: "4%",
    borderRadius: 10,
    padding: "2%",
  },

  textBox: {
    paddingHorizontal: "3%",
    fontSize: 16,
  },


});

export default Field;
