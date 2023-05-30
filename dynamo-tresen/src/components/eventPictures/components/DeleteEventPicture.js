import React, {Component} from "react";
import EventPictureService from "../services/EventPictureService";
import Popup from "../Popup";
import '../css/GeneralEventPicture.css';
import '../css/Popup.css';

/**
 * This class represents the functionality to delete an Event Picture.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
class DeleteEventPicture extends Component {

    state = {
        id: 0,
        message: "Ooops, something went wrong!\nPlease check following:\n\t- Do you have a stable connection to the server?",
        showPopup: false
    }

    /**
     * This method handles sets the state showPopup, if it should pop up or should be closed.
     *
     * @param showPopup
     */
    handleShowPopup = (showPopup) => {
        this.setState({
            showPopup: showPopup
        })
    }

    /**
     * This method handles the submit button.
     * It calls the deleteEventPicture() method from the EventPictureService.js and passes the actual ID.
     * If the response from the server has the status OK (200), then the corresponding message will be set.
     * Otherwise the other message will be set and gives the Client hints.
     *
     * @param event
     * @returns {Promise<string>}
     */
    handleSubmit = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.deleteEventPicture(this.state.id);
        if (response.status === 200) {
            this.setState({
                message: `Event Picture with ID '${this.state.id}' deleted successfully!`
            })
        } else {
            this.setState({
                message: "Ooops, something went wrong!\nPlease check following:\n\t- Have you entered an ID?\n\t- The ID should be at least 1.\n\t- Maybe the ID is not present in the Database."
            })
        }
    }

    /**
     * This method handles the change event, when a value in the id field is changed.
     *
     * @param evt
     */
    handleChange = (evt) => {
        this.setState({
            id: evt.target.value
        });
    }

    render() {
        return (
            <div className="ep-layout">
                <h1>Here you can delete an Event Picture!</h1>
                <form onSubmit={this.handleSubmit} className="ep-form">
                    <p>
                        <label htmlFor="id" className="ep-lbl">ID:</label>
                        <input type="number" id="id" onChange={this.handleChange}/>
                    </p>
                    <input type="submit" value="Submit" className="ep-btn" onClick={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                    <input type="button" value="Overview" className="ep-btn"
                           onClick={() => this.props.history.push('/pictures')}/>
                </form>
                <Popup trigger={this.state.showPopup} setShowPopup={this.handleShowPopup} >
                    <h3>status message:</h3>
                    <p className="ep-messagePopup">{this.state.message}</p>
                </Popup>
            </div>
        )
    }
}


export default DeleteEventPicture;