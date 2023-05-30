import React from "react";
import "./css/ModalButton.css";

const Trigger = ({ triggerText, buttonRef, showModal }) => {
  return (
    <button
      className="btn-event"
      id = "triggerButton"
      ref={buttonRef}
      onClick={showModal}
    >
      {triggerText}
    </button>
  );
};
export default Trigger;
