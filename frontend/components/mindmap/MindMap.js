import React, { useEffect, useContext } from "react";
import { Box, HStack, ScrollView } from "native-base";
import MindMapCategory from "./MindMapCategory";
import { TASK_API } from "../../api/task.api";
import { useFetch } from "../../hooks/useAPI";
import TaskContext from "../../store/task-context";
import { useFocusEffect } from "@react-navigation/native";

const MindMap = (props) => {
  const [fetchData] = useFetch();
  const { data, setData } = useContext(TaskContext);

  useEffect(() => {
    const updateData = async () => {
      setData(await fetchData(TASK_API.fetchTasks));
    };
    updateData();
  }, []);
  // useFocusEffect(useCallback(fetchData, []));

  return (
    <Box flex={1} {...props}>
      <ScrollView>
        <HStack flexWrap="wrap">
          {data.map((element, index) => (
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
