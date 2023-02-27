import "react-native-gesture-handler";
import * as React from "react";
import { Text } from "react-native";

import Toast from "react-native-toast-message";

import * as eva from "@eva-design/eva";
import { ApplicationProvider } from "@ui-kitten/components";
import { StatusBar } from "expo-status-bar";
import { NativeBaseProvider } from "native-base";
import { AuthContextProvider } from "./store/auth-context";
import { TaskContextProvider } from "./store/task-context";
import { theme } from "./UI/theme";
import AppNavigation from "./AppNavigation";
import { useFonts } from "expo-font";

export default function App() {
  const [loaded] = useFonts({
    "Montserrat-Regular": require("./assets/fonts/Montserrat-Regular.otf"),
    "Montserrat-Bold": require("./assets/fonts/Montserrat-Bold.otf"),
    "Montserrat-Black": require("./assets/fonts/Montserrat-Black.otf"),
    "Montserrat-Medium": require("./assets/fonts/Montserrat-Medium.otf"),
    "Montserrat-Italic": require("./assets/fonts/Montserrat-Italic.otf"),
  });

  if (!loaded) {
    return null;
  }
  return (
    <>
      <NativeBaseProvider theme={theme}>
        <AuthContextProvider>
          <TaskContextProvider>
            <StatusBar style="dark" />
            <ApplicationProvider {...eva} theme={eva.light}>
              <AppNavigation />
            </ApplicationProvider>
          </TaskContextProvider>
        </AuthContextProvider>
      </NativeBaseProvider>
      <Toast />
    </>
  );
}
