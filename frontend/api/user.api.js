import { BACKEND_URL } from "./const";

export const registerUser = async (user) => {
  const url = `${BACKEND_URL}/api/user/register`;

  const response = await fetch(url, {
    method: "POST",
    headers: { "Content-type": "application/json" },
    body: JSON.stringify(user),
  });

  if (response.status !== 200) {
    console.log("error")
    throw new Error(response.status);
  } 
  return response;
};
