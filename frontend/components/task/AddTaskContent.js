import React, { useState } from "react";
import { VStack, Box, Text, HStack, Center } from "native-base";
import { Ionicons } from "@expo/vector-icons";
import { AntDesign } from "@expo/vector-icons";

import FormInput from "../../UI/FormInput";
import Card from "./Card";
import DatePicker from "./DatePicker"
import DaysSelector from "./DaysSelector";

const AddTaskContent = () => {
  const [selectedType, setSelectedType] = useState("Repeated");

  return (
    <VStack w="full" maxW={400} space={4}>
      <FormInput label={"Name"} />
      <FormInput label={"Description"} />
      <HStack flex={1} mt={4}>
        <Card
          label="One time"
          icon={{ package: AntDesign, name: "calendar" }}
          isSelected={selectedType === 'One time'}
          setSelected={setSelectedType}
        />
        <Card
          label="Repeated"
          icon={{ package: Ionicons, name: "ios-repeat" }}
          isSelected={selectedType === 'Repeated'}
          setSelected={setSelectedType}
        />
      </HStack>
      {selectedType === 'One time' ? (<DatePicker />) : (<DaysSelector/>)}
    </VStack>
  );
};

export default AddTaskContent;
