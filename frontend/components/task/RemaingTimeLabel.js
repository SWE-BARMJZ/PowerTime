import { HStack, Text, VStack } from "native-base";
import React from "react";
import getRemainingTime from "../../utils/RemainingTime";

const RemaingTimeLabel = ({ dueDate }) => {
  const remainingTime = getRemainingTime(dueDate);

  const cur = new Date();
  const due = new Date(dueDate);
  const isOverdue = cur - due >= 0;

  return (
    <HStack space={2.5}>
      {isOverdue ? (
        <Text color="red.500" bold>
          Overdue
        </Text>
      ) : (
        <>
          {remainingTime.days > 0 && (
            <HStack flexWrap="nowrap">
              <Text bold color="primary.accent">
                {remainingTime.days}
              </Text>
              <Text color="primary.accent">{" D"}</Text>
            </HStack>
          )}
          {remainingTime.hours > 0 && (
            <HStack
              alignItems="center"
              justifyContent="center"
              flexWrap="nowrap"
            >
              <Text bold color="primary.accent">
                {remainingTime.hours}
              </Text>
              <Text color="primary.accent">{" H"}</Text>
            </HStack>
          )}
          {remainingTime.minutes > 0 && (
            <HStack alignItems="center" flexWrap="nowrap">
              <Text bold color="primary.accent">
                {remainingTime.minutes}
              </Text>

              <Text color="primary.accent">{" M"}</Text>
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
//     <Text bold color="primary.accent">
//       {remainingTime.days}
//     </Text>
//     <Text color="primary.accent">
//       {remainingTime.days > 1 ? " days" : " day"}
//     </Text>
//   </VStack>
// )}
// {remainingTime.hours > 0 && (
//   <VStack alignItems="center" justifyContent='center'>
//     <Text bold color="primary.accent">
//       {remainingTime.hours}
//     </Text>
//     <Text bold color="primary.accent">
//       {remainingTime.hours > 1 ? " hours" : " hour"}
//     </Text>
//   </VStack>
// )}
// {remainingTime.minutes > 0 && (
//   <VStack alignItems="center">
//     <Text bold color="primary.accent">
//       {remainingTime.minutes}
//     </Text>

//     <Text color="primary.accent">
//       {remainingTime.minutes > 1 ? " mins" : " min"}
//     </Text>
//   </VStack>
// )}
