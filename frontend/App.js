import "react-native-gesture-handler";
import * as React from "react";

import Toast from "react-native-toast-message";

import * as eva from "@eva-design/eva";
import { ApplicationProvider } from "@ui-kitten/components";
import { StatusBar } from "expo-status-bar";
import { NativeBaseProvider } from "native-base";
import { AuthContextProvider } from "./store/auth-context";
import { TaskContextProvider } from "./store/task-context";
import { theme } from "./UI/theme";
import AppNavigation from "./AppNavigation";

export default function App() {
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
