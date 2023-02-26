import React, { useState, useRef, useEffect, useContext } from "react";
import { FormControl, Input, AddIcon } from "native-base";
import { Select, SelectItem } from "@ui-kitten/components";
import TaskContext from "../../store/task-context";
import { useFetch } from "../../hooks/useAPI";
import { CATEGORY_API } from "../../api/category.api";

const CategorySelect = ({ onSelect }) => {
  const cxt = useContext(TaskContext);
  const [callAPI, { hasError }] = useFetch();

  const [selectedIndex, setSelectedIndex] = useState(null);
  const selectedCategory = selectedIndex ? cxt.getAllCategories()[selectedIndex.row].name : null;

  const [isInputShowing, setIsInputShowing] = useState(false);
  const [NewCategoryName, setNewCategoryName] = useState("");
  const showNewCategoryInput = () => {
    setIsInputShowing(true);
  };

  const selectCategory = (index) => {
    setSelectedIndex(index);
    onSelect(cxt.getAllCategories()[index.row]);
  };

  const addCategoryHandler = async () => {
    const isEmpty = NewCategoryName.trim().length === 0;
    const alreadyExists = cxt.getAllCategories().find((item) => item === NewCategoryName);

    if (!isEmpty && !alreadyExists) {
      const createdCategoryID = await callAPI(
        CATEGORY_API.createCategory,
        NewCategoryName
      );

      if (!hasError) {
        const category = { id: createdCategoryID, name: NewCategoryName };
        cxt.addCategory(category);
      }
    }

    setIsInputShowing(false);
    setNewCategoryName("");
  };

  return (
    <FormControl>
      <FormControl.Label>Category</FormControl.Label>
      <Select
        style={{ flex: 1 }}
        value={selectedCategory}
        selectedIndex={selectedIndex}
        onSelect={selectCategory}
      >
        {cxt.getAllCategories().map((category) => (
          <SelectItem title={category.name} key={category.id} />
        ))}
        {isInputShowing && (
          <Input
            value={NewCategoryName}
            onChangeText={(t) => setNewCategoryName(t)}
            onBlur={addCategoryHandler}
            autoFocus
          />
        )}
        <SelectItem
          title="New category"
          accessoryLeft={<AddIcon />}
          onPress={showNewCategoryInput}
        />
      </Select>
    </FormControl>
  );
};

export default CategorySelect;
