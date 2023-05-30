import React from "react";
import ReactDOM from "react-dom";
import EventForm from "./EventForm";
import "./css/ModalButton.css";
export const Modal = ({
  modalRef,
  buttonRef,
  closeModal,
  id,
  date,
  time,
  name,
  description,
  numOfPhotos,
  program,
  onEdit,
  refresh
}) => {
  return ReactDOM.createPortal(
    <div className="modal-area" ref={modalRef}>
      <button
        ref={buttonRef}
        aria-label="Close Modal"
        aria-labelledby="close-modal"
        className="btn-event"
        onClick={closeModal}
        id="close-modal"
      >
        Close
      </button>
      <div className="modal-body">
        <EventForm
          id={id}
          date={date}
          time={time}
          name={name}
          description={description}
          numOfPhotos={numOfPhotos}
          program={program}
          onEdit={onEdit}
          refresh={refresh}
        />
      </div>
    </div>,
    document.body
  );
};
export default Modal;
