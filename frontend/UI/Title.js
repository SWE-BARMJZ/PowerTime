import {
  Text,
  View,
  StyleSheet,
  Image,
  TextInput,
  Pressable,
  Platform,
} from "react-native";
import React from "react";

function Title(props) {
  return (
    <View style={Styles.titleContainer}>
      <Text style={Styles.title} adjustsFontSizeToFit>
        {props.children}
      </Text>
    </View>
  );
}

const Styles = StyleSheet.create({
  title: {
    fontSize: 36,
    fontWeight: "bold",
  },

  titleContainer: {
    width: "100%",
    justifyContent: "center",
    paddingLeft: Platform.OS !== "web" ? "10%" : "40%",
  },
});

export default Title;
