import React, { useRef, useState } from "react";
import {
  VStack,
  Box,
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
import Toast from "react-native-toast-message";
import { TASK_API } from "../../api/task.api";

const AddTaskContent = () => {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [selectedType, setSelectedType] = useState("One time");
  const [dueDate, setDueDate] = useState(null);
  const [repeatOn, setRepeatOn] = useState(null);

  const validateInput = () => {
    if (name.trim().length === 0) {
      Toast.show({
        type: "error",
        text1: "Task name can't be empty",
      });
      return false;
    }

    if (selectedType === "Repeated" && !repeatOn) {
      Toast.show({
        type: "error",
        text1: "You must select atleast 1 day to repeat on.",
      });
      return false;
    }

    return true;
  };

  const addTask = async () => {
    if (!validateInput()) return;

    const task = {
      name,
      description,
    };

    if (selectedType === "One time") {
      task.dueDate = dueDate;
    } else {
      task.repeatOn = repeatOn;
    }

    try {
      await TASK_API.createTask(task);
      Toast.show({
        type:"success",
        text1: "Task added successfully."
      })
    } catch (error) {
      Toast.show({
        type:"error",
        text1: "Error connecting with the server",
        text2: error
      })
    }

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
