import { HStack, Text, VStack } from "native-base";
import React from "react";
import getRemainingTime from "../../utils/RemainingTime";

const RemaingTimeLabel = ({ dueDate, color="black" }) => {
  const remainingTime = getRemainingTime(dueDate);

  const cur = new Date();
  const due = new Date(dueDate);
  const isOverdue = cur - due >= 0;

  return (
    <HStack space={2.5}>
      {isOverdue ? (
        <Text color="red.500" fontFamily="bold">
          Overdue
        </Text>
      ) : (
        <>
          {remainingTime.days > 0 && (
            <HStack flexWrap="nowrap">
              <Text fontFamily="bold" color={color}>
                {remainingTime.days}
              </Text>
              <Text color={color}>{" D"}</Text>
            </HStack>
          )}
          {remainingTime.hours > 0 && (
            <HStack
              alignItems="center"
              justifyContent="center"
              flexWrap="nowrap"
            >
              <Text fontFamily="bold" color={color}>
                {remainingTime.hours}
              </Text>
              <Text color={color}>{" H"}</Text>
            </HStack>
          )}
          {remainingTime.minutes > 0 && (
            <HStack alignItems="center" flexWrap="nowrap">
              <Text fontFamily="bold" color={color}>
                {remainingTime.minutes}
              </Text>

              <Text color={color}>{" M"}</Text>
            </HStack>
          )}
        </>
      )}
    </HStack>
  );
};

export default RemaingTimeLabel;

// {remainingTime.days > 0 && (
//   <VStack alignItems="center">
//     <Text fontFamily="bold" color="primary.accent">
//       {remainingTime.days}
//     </Text>
//     <Text color="primary.accent">
//       {remainingTime.days > 1 ? " days" : " day"}
//     </Text>
//   </VStack>
// )}
// {remainingTime.hours > 0 && (
//   <VStack alignItems="center" justifyContent='center'>
//     <Text fontFamily="bold" color="primary.accent">
//       {remainingTime.hours}
//     </Text>
//     <Text fontFamily="bold" color="primary.accent">
//       {remainingTime.hours > 1 ? " hours" : " hour"}
//     </Text>
//   </VStack>
// )}
// {remainingTime.minutes > 0 && (
//   <VStack alignItems="center">
//     <Text fontFamily="bold" color="primary.accent">
//       {remainingTime.minutes}
//     </Text>

//     <Text color="primary.accent">
//       {remainingTime.minutes > 1 ? " mins" : " min"}
//     </Text>
//   </VStack>
// )}
