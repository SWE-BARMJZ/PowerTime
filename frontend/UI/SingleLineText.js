import { Text } from "native-base";
import React from "react";

const SingleLineText = (props) => {
  return (
    <Text
      numberOfLines={1}
      ellipsizeMode="middle"
      {...props}
      bgColor="indigo.100"
    >
      {props.children}
    </Text>
  );
};

export default SingleLineText;
