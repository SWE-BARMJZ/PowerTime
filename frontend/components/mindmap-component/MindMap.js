import React, { useContext, useState, useEffect } from "react";
import { Box, HStack, ScrollView, useToast } from "native-base";
import MindMapCategory from "./MindMapCategory";

import TaskContext from "../../store/task-context";

const MindMap = (props) => {
  const { categories, unCategorized } = useContext(TaskContext);

  return (
    <Box flex={1} {...props}>
      <ScrollView>
        <HStack flexWrap="wrap">
          {categories.map((category) => (
            <MindMapCategory data={category} key={category.id} editable />
          ))}
          {unCategorized.length > 0 && (
            <MindMapCategory data={{ category_name: "None", tasks: unCategorized }} />
          )}
        </HStack>
      </ScrollView>
    </Box>
  );
};

export default MindMap;
