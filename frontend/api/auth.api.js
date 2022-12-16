import { BACKEND_URL } from "./const";

export const requestToken = async ({ email, password }) => {
  const url = `${BACKEND_URL}/api/token`;
  const str = `${email}:${password}`;
  const auth = `Basic ${Buffer.from(str).toString("base64")}`;

  const response = await fetch(url, {
    method: "POST",
    headers: new Headers({ Authorization: auth }),
  }).catch((error) => {
    throw new Error("Problem connecting with the server!");
  });

  if (response.status === 200) return response;
  if (response.status === 401) throw new Error("Invalid credentials");
  else throw new Error();
};
