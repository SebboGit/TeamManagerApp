import React from "react";
import Modal from "./Modal";
import TriggerButton from "./Trigger";

class Container extends React.Component {
  state = { isVisible: false };

  showModal = () => {
    this.setState({ isVisible: true }, () => {
      this.closeButton.focus();
    });
  };

  closeModal = () => {
    this.setState({ isVisible: false });
  };

  onKeyDown = (event) => {
    if (event.keyCode === 27) {
      this.closeModal();
    }
  };

  render() {
    return (
      <>
        <TriggerButton
          showModal={this.showModal}
          triggerText={this.props.triggerText}
        />
        {this.state.isVisible ? (
          <Modal
            buttonRef={(n) => (this.closeButton = n)}
            closeModal={this.closeModal}
            onKeyDown={this.onKeyDown}
            id={this.props.id}
            date={this.props.date}
            time={this.props.time}
            name={this.props.name}
            description={this.props.description}
            numOfPhotos={this.props.numOfPhotos}
            program={this.props.program}
            onEdit={this.props.onEdit}
            refresh={this.props.refresh}
          ></Modal>
        ) : null}
      </>
    );
  }
}

export default Container;
