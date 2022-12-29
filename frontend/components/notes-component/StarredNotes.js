import { FlatList, VStack, Heading, Box } from "native-base";
import React from "react";
import SingleLineText from "../../UI/SingleLineText";

const StarredNotes = (props) => {
  return (
    <VStack flex={1} w="full" space={4} {...props}>
      <Heading ml={2}>Starred notes</Heading>

      <FlatList
        data={DUMMY_DATA}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <VStack p={3} mb={2}  borderWidth={1}
          borderColor="gray.200">
            <SingleLineText fontWeight="bold">{item.label}</SingleLineText>
            <SingleLineText numberOfLines={3}>{item.description}</SingleLineText>
          </VStack>
        )}
        
        // borderRadius={5}
      />
    </VStack>
  );
};

export default StarredNotes;

const DUMMY_DATA = [
  {
    id: 0,
    label: "Note 1",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 1,
    label: "Note 2",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 2,
    label: "Note 3",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit.",
  },
  {
    id: 3,
    label: "Note 4",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 4,
    label: "Note 5",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 5,
    label: "Note 5",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 6,
    label: "Note 5",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 7,
    label: "Note 5",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 8,
    label: "Note 5",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
  {
    id: 9,
    label: "Note 5",
    description:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore, nesciunt quas quos corrupti ex unde sapiente magni quidem consectetur illum similique sed, libero, repellat optio minus delectus. Dolorum, quidem magnam?",
  },
];
