import { Text } from "native-base";
import React from "react";

const SingleLineText = (props) => {
  return (
    <Text
      numberOfLines={1}
      ellipsizeMode="tail"
      {...props}
    >
      {props.children}
    </Text>
  );
};

export default SingleLineText;
