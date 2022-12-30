import React from "react";
import { Modal } from "@ui-kitten/components";
import AddTaskContent from "./AddTaskContent";

const AddTaskModal = ({ visible, onClose }) => {
  return (
    <Modal
      visible={visible}
      style={{ width: "100%", maxWidth: 600 }}
      backdropStyle={{ backgroundColor: "rgba(0, 0, 0, 0.5)" }}
      onBackdropPress={onClose}
    >
      <AddTaskContent onClose={onClose}/>
    </Modal>
  );
};

export default AddTaskModal;
