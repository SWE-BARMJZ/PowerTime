import * as React from "react";

import { Home } from "./components/Home";
import { Login } from "./components/Login";
import { PasswordRecovery } from "./components/PasswordRecovery";
import { SignUp } from "./components/SignUp";
import { Notes } from "./components/notes-component/Notes-Main";
import { StatusBar } from 'expo-status-bar';


import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NativeBaseProvider } from "native-base";
import { AuthContextProvider } from "./store/auth-context";
import { theme } from './UI/theme'

const Stack = createNativeStackNavigator();


export default function App() {
  return (
    <NativeBaseProvider theme={theme}>
      <AuthContextProvider>
        <NavigationContainer>
          <StatusBar style="dark" />
          <Stack.Navigator screenOptions={{ headerShown: false }}>
            <Stack.Screen name="Login" component={Login} />
            <Stack.Screen name="Sign Up" component={SignUp} />
            <Stack.Screen
              name="Password Recovery"
              component={PasswordRecovery}
            />
            <Stack.Screen name="Home" component={Home} />
            <Stack.Screen name="Notes" component={Notes} />
          </Stack.Navigator>
        </NavigationContainer>
      </AuthContextProvider>
    </NativeBaseProvider>
  );
}
