import { useContext, useState } from "react";

import AuthContext from "../store/auth-context";
import Toast from "react-native-toast-message";

export const useFetch = (useToken = true) => {
  const [isLoading, setIsLoading] = useState(false);
  const [hasError, setHasError] = useState(false);
  const authContext = useContext(AuthContext);

  const trigger = async (func, ...args) => {
    if (useToken) {
      args = [...args, authContext.token];
    }

    let data = undefined;
    setIsLoading(true);

    try {
      data = await func.apply(null, args);
      setHasError(false);
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "An error has occurred!",
      });
      console.error(error);
      setHasError(true);
    }
    
    setIsLoading(false);
    return data;
  };

  return [trigger, { isLoading, hasError }];
};
