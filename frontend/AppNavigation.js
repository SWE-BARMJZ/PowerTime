import React, { useContext } from "react";
import { VStack } from "native-base";
import AuthContext from "./store/auth-context";

import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { createDrawerNavigator } from "@react-navigation/drawer";

const Stack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();

import { Home } from "./components/home-component/Home";
import { Login } from "./components/login-component/LoginScreen";
import { PasswordRecovery } from "./components/login-component/PasswordRecoveryScreen";
import { SignUp } from "./components/login-component/SignUpScreen";
import { NotesScreen } from "./components/notes-component/NotesScreen";
import MindMapScreen from "./components/mindmap-component/MindMapScreen";

const AppNavigation = () => {
  const auth = useContext(AuthContext);

  return (
    <NavigationContainer>
      {auth.isLoggedIn ? (
        <Drawer.Navigator
          screenOptions={{ headerShown: false }}
          drawerContent={CustomDrawerContent}
        >
          <Drawer.Screen name="Home" component={Home} />
          <Drawer.Screen name="Mindmap" component={MindMapScreen} />
          <Drawer.Screen name="Notes" component={NotesScreen} />
        </Drawer.Navigator>
      ) : (
        <Stack.Navigator screenOptions={{ headerShown: false }}>
          <Stack.Screen name="Login" component={Login} />
          <Stack.Screen name="Sign Up" component={SignUp} />
          <Stack.Screen name="Password Recovery" component={PasswordRecovery} />
        </Stack.Navigator>
      )}
    </NavigationContainer>
  );
};

import {
  DrawerContentScrollView,
  DrawerItemList,
  DrawerItem,
} from "@react-navigation/drawer";
import { Ionicons } from "@expo/vector-icons";

function CustomDrawerContent(props) {
  const auth = useContext(AuthContext);

  return (
    <VStack justifyContent="space-between" h={"full"}>
      <DrawerContentScrollView {...props}>
        <DrawerItemList {...props} />
      </DrawerContentScrollView>

      <DrawerItem
        label={"Sign out"}
        icon={() => <Ionicons name={"exit-outline"} size={22} />}
        labelStyle={{ marginLeft: -25 }}
        onPress={() => auth.logout()}
      />
    </VStack>
  );
}
export default AppNavigation;
