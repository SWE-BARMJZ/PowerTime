import { BACKEND_URL } from "./const";
import base64 from "react-native-base64";

export const requestToken = async ({ email, password }) => {
  const url = `${BACKEND_URL}/api/token`;
  const str = `${email}:${password}`;
  const auth = `Basic ${base64.encode(str)}`;
  
  const response = await fetch(url, {
    method: "POST",
    headers: new Headers({ Authorization: auth }),
  }).catch((error) => {
    throw new Error("Problem connecting with the server!");
  });

  if (response.status == 401) {
    throw new Error("Invalid credentials");
  }
  else if (response.status !== 200) {
    throw new Error(response.status);
  } 
  return response;

};
