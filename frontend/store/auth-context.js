import { useState, useEffect } from "react";
import React from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";

const AuthContext = React.createContext({
  token: "",
  isLoggedIn: false,
  login: (token) => {},
  logout: () => {},
});

export const AuthContextProvider = (props) => {
  const [token, setToken] = useState("");
  const isLoggedIn = token.length !== 0

  const loginHandler = (token) => {
    setToken(token);
    AsyncStorage.setItem('token',token);
  };

  const logoutHandler = () => {
    setToken("");
    AsyncStorage.setItem('token',"");
  };

  const isLogIn = async () => {
      try {
        let savedToken = await AsyncStorage.getItem('token');
        if (savedToken) setToken(savedToken);
      } catch (error) {
        console.log('is logged error ${error}');
      }
  }

  useEffect(() => {
    isLogIn();
  },[])

  const contextValue = {
    token,
    isLoggedIn,
    login: loginHandler,
    logout: logoutHandler,
  };

  return (
    <AuthContext.Provider value={contextValue}>
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
