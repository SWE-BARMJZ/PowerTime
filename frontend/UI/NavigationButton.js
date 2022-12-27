import React from "react";
import { IconButton, HamburgerIcon } from "native-base";
import { DrawerActions, useNavigation } from "@react-navigation/native";

export const NavigationButton = ({ navigation }) => {
  const navigate = useNavigation();

  const toggleDrawer = () => {
    navigate.dispatch(DrawerActions.toggleDrawer());
    console.log("lol")
  };

  return (
    <IconButton
      onPress={toggleDrawer}
      icon={<HamburgerIcon color="primary.accent" size="md" />}
    >
      Toggle
    </IconButton>
  );
};
