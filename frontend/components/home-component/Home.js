import { useContext, useState, useEffect } from "react";
import { Text, View } from "react-native";
import AuthContext from "../../store/auth-context";
import { getUser } from "../../api/user.api";
import { NavigationButton } from "../../UI/NavigationButton";

export const Home = () => {
  const [name, setName] = useState("");
  const auth = useContext(AuthContext);

  useEffect(() => {
    if (auth.isLoggedIn) {
      fetchUserName();
    }
  }, [auth, fetchUserName]);

  const fetchUserName = async () => {
    const response = await getUser(auth.token);
    const user = await response.json();
    setName(user.firstName);
  };

  return (
    <View style={{ justifyContent: "center" }}>
      <NavigationButton />
      <Text style={{ fonstSize: 40 }}>Welcome Home 😁</Text>
      {auth.isLoggedIn && <Text style={{ fonstSize: 40 }}>Hello, {name}</Text>}
      {auth.isLoggedIn && (
        <Text style={{ fonstSize: 40 }}>logged in Token = {auth.token}</Text>
      )}
    </View>
  );
};
