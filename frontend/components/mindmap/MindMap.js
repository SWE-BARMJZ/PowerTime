import React, { useEffect } from "react";
import { Box, HStack, ScrollView } from "native-base";
import MindMapCategory from "./MindMapCategory";
import { TASK_API } from "../../api/task.api";
import { useAPI } from "../../hooks/useAPI";

const MindMap = (props) => {
  const [fetchData, data] = useAPI(TASK_API.fetchTasks);

  useEffect(() => {
    fetchData();
  }, []);

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
