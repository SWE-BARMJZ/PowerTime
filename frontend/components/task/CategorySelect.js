import React, { useState, useRef, useEffect } from "react";
import { FormControl, Input, AddIcon } from "native-base";
import { Select, SelectItem } from "@ui-kitten/components";
import { CATEGORY_API } from "../../api/category.api";

const CategorySelect = ({ onSelect }) => {
  const [categories, setCategories] = useState([]);
  const fetchCategories = async () => {};

  useEffect(() => {
    fetchCategories();
  }, []);

  const [selectedIndex, setSelectedIndex] = useState(null);
  const selectedCategory = selectedIndex ? categories[selectedIndex.row] : null;

  const [isInputShowing, setIsInputShowing] = useState(false);
  const [NewCategoryName, setNewCategoryName] = useState("");
  const showNewCategoryInput = () => {
    setIsInputShowing(true);
  };

  const selectCategory = (index) => {
    setSelectedIndex(index);
    onSelect(categories[selectedIndex.row]);
  };

  const addCategory = () => {
    const nonEmpty = NewCategoryName.trim().length > 0;
    const alreadyExists = categories.find((item) => item === NewCategoryName);

    if (nonEmpty && !alreadyExists) {
      setCategories((current) => [...current, NewCategoryName]);
      // add category api
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
          <SelectItem title={category} key={category} />
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
