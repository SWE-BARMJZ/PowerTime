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
  VStack,
  Modal,
  Button,
} from "native-base";
import FormInput from "../../UI/FormInput";
import { MaterialIcons } from "@expo/vector-icons";
import Task from "../task/Task";
import SingleLineText from "../../UI/SingleLineText";
import AuthContext from "../../store/auth-context";
import TaskContext from "../../store/task-context";

const color = "black";

const MindMapCategory = (props) => {
  const cxt = useContext(TaskContext);
  const auth = useContext(AuthContext);

  const {
    data: { id, category_name, tasks },
    editable,
  } = props;

  const deleteTask = (taskId) => {
    cxt.deleteTask(taskId, auth.token);
  };

  const tickTask = (taskId) => {
    cxt.tickTask(taskId, auth.token);
  };

  const [newCategoryName, setNewCategoryName] = useState("");
  const renameCategory = () => {
    cxt.renameCategory(id, newCategoryName, auth.token);
  };
  const deleteCategoryHandler = () => {
    cxt.deleteCategory(id, auth.token);
  };

  const addToTodo = (taskId) => {
    cxt.addToTodo(taskId, auth.token);
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
        <VStack space={2} p={2} borderRadius="lg" bgColor={"primary.evenDarkerBg"}>
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
                    {category_name}
                  </SingleLineText>
                </HStack>
              </Pressable>
            </Box>

            {editable && (
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
            )}
          </HStack>

          {isTasksShowing &&
            tasks.map((task) => (
              <HStack key={task.id}>
                <Task
                  flex={1}
                  data={task}
                  onTaskRemoval={deleteTask}
                  onTaskCompletion={tickTask}
                />
                <Button onPress={() => addToTodo(task.id)}>Todo</Button>
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
