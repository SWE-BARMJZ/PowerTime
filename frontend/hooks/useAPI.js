import { useContext, useState } from "react";

import AuthContext from "../store/auth-context";
import Toast from "react-native-toast-message";

export const useFetch = (useToken = true) => {
  const [isLoading, setIsLoading] = useState(false);
  const authContext = useContext(AuthContext);

  const trigger = async (
    func,
    { args = [], callback = () => {} } = undefined
  ) => {
    if (useToken) {
      args = [...args, authContext.token];
    }

    setIsLoading(true);
    try {
      const data = await func.apply(null, args);
      if (callback) {
        callback(data);
      }
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "An error has occurred!",
      });
      console.error(error);
    }

    setIsLoading(false);
  };

  return [trigger];
};
