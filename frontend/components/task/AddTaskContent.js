import React, { useRef, useState } from "react";
import {
  VStack,
  Box,
  Text,
  HStack,
  Center,
  Heading,
  FormControl,
  TextArea,
  Button,
} from "native-base";
import { Ionicons } from "@expo/vector-icons";
import { AntDesign } from "@expo/vector-icons";

import FormInput from "../../UI/FormInput";
import Card from "./Card";
import DatePicker from "./DatePicker";
import DaysSelector from "./DaysSelector";

const AddTaskContent = () => {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [selectedType, setSelectedType] = useState("One time");
  const [dueDate, setDueDate] = useState(null);
  const [repeatOn, setRepeatOn] = useState(null);

  const validteInput = () => {
    // display error
    if (name.trim().length === 0) return;
    if (selectedType === "Repeated" && !repeatOn) return;
  };

  const addTask = () => {
    const task = {
      name,
      description,
    };

    if (selectedType === "One time") {
      task.dueDate = dueDate;
    } else {
      task.repeatOn = repeatOn;
    }
    console.log(task);
  };

  return (
    <VStack w="full" maxW={400} space={4}>
      <FormInput
        label={"Name"}
        onChange={setName}
        inputValue={name}
        validationFn={(s) => s.trim().length > 0}
        error={"Task name can't be empty"}
      />
      <FormControl>
        <FormControl.Label>Description</FormControl.Label>
        <TextArea
          onChangeText={(s) => setDescription(s)}
          value={description}
          fontSize="md"
        />
      </FormControl>
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

      {selectedType === "One time" ? (
        <FormControl>
          <FormControl.Label>Deadline</FormControl.Label>
          <DatePicker value={dueDate} setValue={setDueDate} />
        </FormControl>
      ) : (
        <DaysSelector onStateChange={(days) => setRepeatOn(days)} />
      )}
      <Button onPress={addTask} mt={5}>
        Add
      </Button>
    </VStack>
  );
};

export default AddTaskContent;
