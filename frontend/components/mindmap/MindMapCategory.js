import React, { useContext, useState } from "react";
import {
  Box,
  Pressable,
  HStack,
  IconButton,
  Menu,
  Icon,
  ChevronDownIcon,
  ChevronRightIcon,
  DeleteIcon,
  VStack,
  Modal,
  Button,
} from "native-base";
import { MaterialIcons } from "@expo/vector-icons";
import FormInput from "../../UI/FormInput";
import Task from "../task/Task";
import SingleLineText from "../../UI/SingleLineText";
import TaskContext from "../../store/task-context";

import { useFetch } from "../../hooks/useAPI";
import { TASK_API } from "../../api/task.api";
import { TODO_API } from "../../api/todo.api";
import { CATEGORY_API } from "../../api/category.api";

const color = "black";

const MindMapCategory = ({ data: { category, tasks } }) => {
  const [callAPI] = useFetch();
  const cxt = useContext(TaskContext);

  const addToTodo = (task) => {
    callAPI(TODO_API.addToTodo, {
      args: [task.id],
      callback: () => {
        cxt.setTaskAsTodo(task);
      },
    });
  };

  const deleteTask = (task) => {
    callAPI(TASK_API.deleteTask, {
      args: [task.id],
      callback: () => {
        cxt.deleteTask(task);
      },
    });
  };

  const [newCategoryName, setNewCategoryName] = useState("");
  const renameCategory = () => {
    callAPI(CATEGORY_API.renameCategory, {
      args: [category.id, newCategoryName],
      callback: () => {
        cxt.renameCategory(category.id, newCategoryName);
      },
    });
  };

  const deleteCategoryHandler = () => {
    callAPI(CATEGORY_API.deleteCategory, {
      args: [category.id],
      callback: () => {
        cxt.deleteCategory(category.id);
      },
    });
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

  const isNonCategorisedAndEmpty = !category && tasks.length == 0;

  return isNonCategorisedAndEmpty ? (
    <></>
  ) : (
    <>
      <Box w={["full", "full", "1/2", "1/3", "1/4", "1/5"]} p={2}>
        <VStack
          space={2}
          p={2}
          borderRadius="lg"
          bgColor={"primary.evenDarkerBg"}
        >
          {category && (
            <HStack alignItems="center" justifyContent="space-between">
              <Box flex={1}>
                <Pressable onPress={toggleTasksShowing} p="2">
                  <HStack alignItems={"center"} space={2}>
                    {isTasksShowing ? (
                      <ChevronDownIcon color={color} size={4} />
                    ) : (
                      <ChevronRightIcon color={color} size={4} />
                    )}
                    <SingleLineText
                      color={color}
                      fontSize={18}
                      fontFamily="bold"
                      flex={1}
                    >
                      {category.name}
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
                        variant={
                          newCategoryName.length <= 0 ? "ghost" : "solid"
                        }
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
            </HStack>
          )}

          {isTasksShowing &&
            tasks.map((task) => (
              <HStack key={task.id}>
                <Task
                  flex={1}
                  data={task}
                  rightComponent={
                    <IconButton
                      icon={<DeleteIcon color="gray.500" />}
                      colorScheme="red"
                      onPress={() => {
                        deleteTask(task);
                      }}
                    />
                  }
                />
                {task.sunday === undefined && (
                  <Button
                    onPress={() => addToTodo(task)}
                    isDisabled={task.todo}
                  >
                    Todo
                  </Button>
                )}
              </HStack>
            ))}
        </VStack>
      </Box>
    </>
  );
};

export default MindMapCategory;
