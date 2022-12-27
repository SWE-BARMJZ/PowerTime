import React, { useState } from "react";
import {
  Text,
  Box,
  Pressable,
  HStack,
  IconButton,
  Menu,
  Icon,
  ChevronDownIcon,
  ChevronRightIcon,
  VStack,
  Modal,
  Button,
} from "native-base";
import FormInput from "../../UI/FormInput";
import { MaterialIcons } from "@expo/vector-icons";
import Task from "../Task";
import SingleLineText from "../../UI/SingleLineText";

const color = "black";
const bgColor = "dark.600";

const MindMapCategory = (props) => {
  const {
    data: { id, name, tasks },
    onTaskDeletion,
    onTaskCompletion,
    onTaskMoveToTodo,
    onCategoryRename,
    onCategoryDelete,
  } = props;

  const [newCategoryName, setNewCategoryName] = useState("");
  const renameCategory = () => {
    onCategoryRename(id, newCategoryName);
  };
  const deleteCategoryHandler = () => {
    onCategoryDelete(id);
  };

  const [isTasksShowing, setIsTasksShowing] = useState(true);
  const toggleTasksShowing = () => {
    setIsTasksShowing((current) => !current);
  };

  const [isModalShowing, setIsModalShowing] = useState(false);
  const cancelCategoryRenaming = () => {
    setIsModalShowing(false);
    setNewCategoryName("");
  };

  return (
    <>
      <Box w={["full", "full", "1/2", "1/3", "1/4", "1/5"]} p={2}>
        <VStack space={2} p={2} borderRadius="lg" bgColor={bgColor}>
          <HStack alignItems="center" justifyContent="space-between">
            <Box flex={1}>
              <Pressable onPress={toggleTasksShowing} p="2">
                <HStack alignItems={"center"} space={2}>
                  {isTasksShowing ? (
                    <ChevronDownIcon color={color} size={4} />
                  ) : (
                    <ChevronRightIcon color={color} size={4} />
                  )}
                  <SingleLineText color={color} fontSize={18} bold flex={1}>
                    {name}
                  </SingleLineText>
                </HStack>
              </Pressable>
            </Box>

            <Menu
              w="200"
              trigger={(triggerProps) => (
                <IconButton
                  h="10"
                  w="10"
                  icon={
                    <Icon as={MaterialIcons} name="more-vert" color={color} />
                  }
                  alignItems="center"
                  {...triggerProps}
                />
              )}
            >
              <Menu.Item onPress={() => setIsModalShowing(true)}>
                Edit
              </Menu.Item>
              <Menu.Item onPress={deleteCategoryHandler}>Delete</Menu.Item>
            </Menu>
          </HStack>

          {isTasksShowing &&
            tasks.map((task) => (
              <HStack bgColor={"blue.200"} key={task.id}>
                <Task
                  flex={1}
                  data={task}
                  onTaskRemoval={onTaskDeletion}
                  onTaskCompletion={onTaskCompletion}
                />
                <Button onPress={() => onTaskMoveToTodo(task.id)}>Todo</Button>
              </HStack>
            ))}
        </VStack>
      </Box>

      <Modal isOpen={isModalShowing} onClose={cancelCategoryRenaming}>
        <Modal.Content maxWidth="400px">
          <Modal.CloseButton />
          <Modal.Header>Renaming category</Modal.Header>
          <Modal.Body>
            <FormInput
              label="New name"
              onChange={(s) => setNewCategoryName(s)}
              inputValue={newCategoryName}
              validationFn={(s) => s.length > 0}
              error="New name can't be empty"
            />
          </Modal.Body>
          <Modal.Footer>
            <Button.Group space={2}>
              <Button onPress={cancelCategoryRenaming}>Cancel</Button>
              <Button
                disabled={newCategoryName.length <= 0}
                variant={newCategoryName.length <= 0 ? "ghost" : "solid"}
                onPress={() => {
                  setIsModalShowing(false);
                  renameCategory();
                }}
              >
                Save
              </Button>
            </Button.Group>
          </Modal.Footer>
        </Modal.Content>
      </Modal>
    </>
  );
};

export default MindMapCategory;
