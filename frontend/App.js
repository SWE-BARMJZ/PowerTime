import * as React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { StatusBar } from "expo-status-bar";
import { View, StyleSheet } from "react-native";

import { Home } from "./components/Home";
import { Login } from "./components/Login";
import { SignUp } from "./components/SignUp";
import { PasswordRecovery } from "./components/PasswordRecovery";

import { AuthContextProvider } from './store/auth-context'

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <AuthContextProvider>
      <View style={{ backgroundColor: "#dffaef", flex: 1 }}>
        <View style={Styles.container}>
          <StatusBar style="dark" />
          <NavigationContainer>
            <Stack.Navigator screenOptions={{ headerShown: false }}>
              <Stack.Screen name="Login" component={Login} />
              <Stack.Screen name="Sign Up" component={SignUp} />
              <Stack.Screen
                name="Password Recovery"
                component={PasswordRecovery}
              />
              <Stack.Screen name="Home" component={Home} />
            </Stack.Navigator>
          </NavigationContainer>
        </View>
      </View>
    </AuthContextProvider>
  );
}

const Styles = StyleSheet.create({
  container: {
    marginTop: 25,
    flex: 1,
    backgroundColor: "#dffaef",
  },
});
