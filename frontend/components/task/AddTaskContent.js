import React, { useState } from "react";
import {
  VStack,
  Box,
  Text,
  HStack,
  Center,
  Heading,
  FormControl,
  TextArea,
} from "native-base";
import { Ionicons } from "@expo/vector-icons";
import { AntDesign } from "@expo/vector-icons";

import FormInput from "../../UI/FormInput";
import Card from "./Card";
import DatePicker from "./DatePicker";
import DaysSelector from "./DaysSelector";

const AddTaskContent = () => {
  const [selectedType, setSelectedType] = useState("One time");

  const DescriptionInput = <FormControl>
    <FormControl.Label>Description</FormControl.Label>
    <TextArea />
  </FormControl>;

  return (
    <VStack w="full" maxW={400} space={4}>
      <FormInput label={"Name"} />
      {DescriptionInput}
      <Box flexDir={"row"} mt={2}>
        <Card
          label="One time"
          icon={{ package: AntDesign, name: "calendar" }}
          isSelected={selectedType === "One time"}
          setSelected={setSelectedType}
        />
        <Card
          label="Repeated"
          icon={{ package: Ionicons, name: "ios-repeat" }}
          isSelected={selectedType === "Repeated"}
          setSelected={setSelectedType}
        />
      </Box>

      {selectedType === "One time" ? <DatePicker /> : <DaysSelector />}
    </VStack>
  );
};

export default AddTaskContent;
