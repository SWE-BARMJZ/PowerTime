import { useState, useEffect } from "react";
import React from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {getUser} from "../api/user.api"

const AuthContext = React.createContext({
  token: "",
  isLoggedIn: false,
  userInfo: { firstName: "", lastName: "", email: "" },
  login: (token) => {},
  logout: () => {},
});

export const AuthContextProvider = (props) => {
  const [token, setToken] = useState("");
  const [userInfo, setUserInfo] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });
  const isLoggedIn = token.length !== 0

  async function fetchUserData(token) {
    const response = await getUser(token)
    const user = await response.json()

    setUserInfo({
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
    });

  }


  const loginHandler = async (token) => {
    await fetchUserData(token);
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
        if (savedToken){
          await fetchUserData(savedToken)
          setToken(savedToken);
        }
        
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
    userInfo,
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


