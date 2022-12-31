import React, { useState, useRef, useEffect, useContext } from "react";
import { FormControl, Input, AddIcon } from "native-base";
import { Select, SelectItem } from "@ui-kitten/components";
import TaskContext from "../../store/task-context";
import AuthContext from "../../store/auth-context";

const CategorySelect = ({ onSelect }) => {
  const fetchCategories = async () => {};

  const auth = useContext(AuthContext);
  const cxt = useContext(TaskContext);
  const categories = cxt.categories;

  useEffect(() => {
    fetchCategories();
  }, []);

  const [selectedIndex, setSelectedIndex] = useState(null);
  const selectedCategory = selectedIndex
    ? categories[selectedIndex.row]["category_name"]
    : null;

  const [isInputShowing, setIsInputShowing] = useState(false);
  const [NewCategoryName, setNewCategoryName] = useState("");
  const showNewCategoryInput = () => {
    setIsInputShowing(true);
  };

  const selectCategory = (index) => {
    setSelectedIndex(index);
    onSelect(categories[index.row]);
  };

  const addCategory = () => {
    const nonEmpty = NewCategoryName.trim().length > 0;
    const alreadyExists = categories.find((item) => item === NewCategoryName);

    if (nonEmpty && !alreadyExists) {
      cxt.addCategory({ category_name: NewCategoryName, tasks: [] }, auth.token);
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
        {categories.map((category) => (
          <SelectItem title={category["category_name"]} key={category.id} />
        ))}
        {isInputShowing && (
          <Input
            value={NewCategoryName}
            onChangeText={(t) => setNewCategoryName(t)}
            onBlur={addCategory}
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
