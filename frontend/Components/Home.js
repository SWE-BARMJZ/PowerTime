import { Button, Text, View } from "react-native";

export const Home = ({ navigation }) => {
  return (
    <View style={{ justifyContent: "center" }}>
      <Text style={{ fonstSize: 40 }}>Welcome Home 😁</Text>
      <Button title="Log out" onPress={() => navigation.navigate("Login")} />
    </View>
  );
};
