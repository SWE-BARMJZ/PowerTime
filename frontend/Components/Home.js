import { useContext } from "react";
import { Button, Text, View } from "react-native";
import AuthContext from "../store/auth-context";

export const Home = ({ navigation }) => {
  const auth = useContext(AuthContext);

  return (
    <View style={{ justifyContent: "center" }}>
      <Text style={{ fonstSize: 40 }}>Welcome Home 😁</Text>
      {auth.isLoggedIn && <Text style={{ fonstSize: 40 }}>logged in Token = {auth.token}</Text>}
      <Button title="Log out" onPress={() => navigation.navigate("Login")} />
    </View>
  );
};
