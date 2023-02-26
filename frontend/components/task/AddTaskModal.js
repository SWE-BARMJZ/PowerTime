import React from "react";
import { Modal } from "@ui-kitten/components";
import AddTaskContent from "./AddTaskContent";

const AddTaskModal = ({ visible, closeModalHandler }) => {
  return (
    <Modal
      visible={visible}
      style={{ width: "100%", maxWidth: 600 }}
      backdropStyle={{ backgroundColor: "rgba(0, 0, 0, 0.5)" }}
      onBackdropPress={closeModalHandler}
    >
      <AddTaskContent closeModalHandler={closeModalHandler} />
    </Modal>
  );
};

export default AddTaskModal;
