import { BACKEND_URL } from "./const";

export const requestToken = async ({ email, password }) => {
  console.log(email, password);
  const url = `${BACKEND_URL}/api/token`;
  const str = `${email}:${password}`;
  const auth = `Basic ${Buffer.from(str).toString("base64")}`;

  const response = await fetch(url, {
    method: "POST",
    headers: new Headers({ Authorization: auth }),
  });

  if (response.status == 401) {
    throw new Error("Invalid credentials");
  }
  else if (response.status !== 200) {
    throw new Error(response.status);
  } 
  return response;
};
