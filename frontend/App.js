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
        <StatusBar style="dark" />
        <AppNavigation />
      </AuthContextProvider>
    </NativeBaseProvider>
  );
}
