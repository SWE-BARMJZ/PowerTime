import 'react-native-gesture-handler';
import * as React from "react";

import { StatusBar } from "expo-status-bar";
import { NativeBaseProvider } from "native-base";
import { AuthContextProvider } from "./store/auth-context";
import { theme } from "./UI/theme";
import AppNavigation from "./AppNavigation";

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
            <Stack.Screen name="MindMap" component={MindMapScreen} />
            <Stack.Screen name="Todo" component={TodoScreen} />
            <Stack.Screen name="Notes" component={Notes} />
          </Stack.Navigator>
        </NavigationContainer>
        <AppNavigation />

      </AuthContextProvider>
    </NativeBaseProvider>
  );
}
