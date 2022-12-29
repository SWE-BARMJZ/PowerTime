import React from "react";
import { Box, Text, Center, Icon, Pressable } from "native-base";

const Card = ({ isSelected, setSelected, label, icon, ...props }) => {

  const select = () => {
    setSelected(label);
  };
  
  return (
    <Box flex={1} {...props} >
      <Pressable onPress={select}>
        {({ isHovered }) => {
          const color = isSelected ? "black" :  isHovered ? "primary.main" : "gray.500";

          return (
            <Center py={2} borderRadius="md">
              {icon && <Icon as={icon.package} name={icon.name} color={color} size={6} />}
              <Text color={color}>{label}</Text>
            </Center>
          );
        }}
      </Pressable>
    </Box>
  );
};

export default Card;
