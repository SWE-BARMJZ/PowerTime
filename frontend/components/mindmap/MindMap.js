import React, { useContext, useCallback } from "react";
import { Box, HStack, ScrollView } from "native-base";
import { useFocusEffect } from "@react-navigation/native";
import { useFetch } from "../../hooks/useAPI";
import { TASK_API } from "../../api/task.api";
import TaskContext from "../../store/task-context";
import MindMapCategory from "./MindMapCategory";

const MindMap = (props) => {
  const [callAPI] = useFetch();
  const { data, setData } = useContext(TaskContext);

  const fetchData = async () => {
    callAPI(TASK_API.fetchTasks, { callback: setData });
  };

  useFocusEffect(
    useCallback(() => {
      fetchData();
    }, [])
  );

  return (
    <Box flex={1} {...props}>
      <ScrollView>
        <HStack flexWrap="wrap">
          {data.map((element) => (
            <MindMapCategory
              data={element}
              key={element.category ? element.category.id : -1}
            />
          ))}
        </HStack>
      </ScrollView>
    </Box>
  );
};

export default MindMap;
