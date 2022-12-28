import React, { useState } from "react";
import { getUser } from "../api/user.api";

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
  const isLoggedIn = token.length !== 0;

  const loginHandler = async (token) => {
    const response = await getUser(token);
    const user = await response.json();
    setUserInfo({
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
    });
    setToken(token);
  };

  const logoutHandler = () => {
    setToken("");
  };

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
