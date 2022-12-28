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
  // const [token, setToken] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [token, setToken] = useState(null);

  const loginHandler = (token) => {
    setIsLoading(true);
    setToken(token);
    AsyncStorage.setItem('token',token);
    setIsLoading(false);
  };

  const logoutHandler = () => {
    setIsLoading(true);
    setToken(null);
    AsyncStorage.removeItem('token');
    setIsLoading(false);
  };

  const isLoggedIn = async () => {
      try {
        setIsLoading(true);
        let userToken = AsyncStorage.getItem('token');
        setToken(userToken);
        setIsLoading(false);        
      } catch (error) {
        console.log('is logged error ${error}');
      }
  }

  useEffect(() => {
    isLoggedIn();
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
