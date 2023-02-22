import { useToast } from "native-base";
import { useContext, useState } from "react";

import AuthContext from "../store/auth-context";
import Toast from "react-native-toast-message";

export const useAPI = (func) => {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const authContext = useContext(AuthContext);

  const trigger = async (args = []) => {
    setIsLoading(true);
    args = [...args, authContext.token];

    try {
      setData(await func.apply(null, args));
    } catch (error) {
      Toast.show({
        type: "error",
        text1: "An error has occurred!",
      });
      console.error(error);
    }

    setIsLoading(false);
  };

  return [trigger, data, isLoading];
};
