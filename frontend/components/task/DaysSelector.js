import React, { useState } from "react";
import { Text, HStack, Box, Pressable, Center } from "native-base";

const DaysSelector = ({ onStateChange }) => {
  const [selectedDays, setSelectedDays] = useState(new Set());
  const days = [
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
  ];

  const selectDay = (day) => {
    const updatedSet = new Set(selectedDays);

    if (selectedDays.has(day)) {
      updatedSet.delete(day);
    } else {
      updatedSet.add(day);
    }

    const repeatOn = {};
    updatedSet.forEach((day) => {
      day = day.toLowerCase();
      repeatOn[day] = true;
    });
    onStateChange(repeatOn);
    setSelectedDays(updatedSet);
  };

  return (
    <HStack space={"0.5"} my={2}>
      {days.map((day) => {
        const isSelected = selectedDays.has(day);
        const bgColor = isSelected ? "#3185F9" : "#F8F9FC";

        return (
          <Box flex={1} key={day} bgColor={bgColor}>
            <Pressable
              onPress={() => {
                selectDay(day);
              }}
            >
              {({ isHovered }) => {
                const color = isSelected
                  ? "white"
                  : isHovered
                  ? "primary.main"
                  : "gray.500";

                return (
                  <Center py={2} borderRadius="md">
                    <Text color={color}>{day.substring(0, 3)}</Text>
                  </Center>
                );
              }}
            </Pressable>
          </Box>
        );
      })}
    </HStack>
  );
};

export default DaysSelector;
