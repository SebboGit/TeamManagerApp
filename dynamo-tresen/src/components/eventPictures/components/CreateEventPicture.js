import React, {Component} from "react";
import EventPictureService from "../services/EventPictureService";
import "../css/GeneralEventPicture.css";
import "../css/Popup.css";
import Popup from "../Popup";

/**
 * This class represents the functionality to create an Event Picture.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
class CreateEventPicture extends Component {

    state = {
        title: '',
        description: '',
        rating: 1,
        event: 0,
        members: [],
        image: null,
        message: "Ooops, something went wrong!\nPlease check following:\n\t- Have you selected an image?\n\t- Have you entered the members?\n\t- Do you have a stable connection to the server?",
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
     * This method sets the state message back do the default message.
     */
    handleDefaultMessage = () => {
        this.setState({
            message: "Ooops, something went wrong!\nPlease check following:\n\t- Have you selected an image?\n\t- Have you entered the members?\n\t- Do you have a stable connection to the server?"
        })
    }

    /**
     * This method handles the Submit Button.
     * Before the upload, the existence of the Event ID is verified via the getEventIdExistence().
     * If the Event ID exists, then the Event Picture will be uploaded. Otherwise a message for the Client is set.
     * Afterwards it calls the uploadEventPicture() method from the EventPictureService.js and passes the actual state.
     * If the response from the server has the status OK (200), then the corresponding message will be set.
     * Otherwise the other message will be set to tell the Client something went wrong.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmit = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const responseEventId = await service.getEventIdExistence(this.state.event);
        if (responseEventId.status === 200) {
            const response = await service.uploadEventPicture(this.state);
            if (response.status === 201) {
                this.setState({
                    message: `Event Picture created successfully!`,
                    titel: '',
                    description: '',
                    rating: 1,
                    event: 0,
                    members: [],
                    image: null,
                })
            } else {
                this.setState({
                    message: "Ooops, something went wrong!"
                })
            }
        } else {
            this.setState({
                message: `Sorry, the Event with ID '${this.state.event}' does not exist!`
            })
        }
    }

    /**
     * This method handles the change event, when a value in the input fields is changed.
     * It takes the actual status of the state and stores the new value in the state.
     *
     * @param evt
     */
    handleChange = (evt) => {
        this.setState(actualState => ({
            ...actualState,
            [evt.target.id]: evt.target.value
        }));
    }

    /**
     * This method handles the change event of the picture input.
     * If the picture is changed, the state will be updated with the new picture value.
     *
     * @param evt
     */
    handleChangeImage = (evt) => {
        this.setState({
            image: evt.target.files[0]
        });
    }

    render() {
        return (
            <div className="ep-layout">
                <h1>Here you can upload an image with information!</h1>
                <form onSubmit={this.handleSubmit} onClick={this.handleDefaultMessage} className="ep-form">
                    <p>
                        <label htmlFor="image" className="ep-lbl">Image:</label>
                        <input type="file" id="image" name="file" accept="image/*" className="ep-input" required
                               onChange={this.handleChangeImage}/>
                    </p>
                    <p>
                        <label htmlFor="title" className="ep-lbl">Title:</label>
                        <input type="text" id="title" value={this.state.title} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label htmlFor="description" className="ep-lbl">Description:</label>
                        <input type="text" id="description" value={this.state.description} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label htmlFor="event" className="ep-lbl">Event:</label>
                        <input type="number" id="event" value={this.state.event} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label htmlFor="rating" className="ep-lbl">Rating:</label><br/>
                        <input type="radio" id="rating" name="rating" value="1"
                               onChange={this.handleChange} defaultChecked={true}/>
                        <label htmlFor="rating1" className="ep-lbl">1</label><br/>
                        <input type="radio" id="rating" name="rating" value="2"
                               onChange={this.handleChange}/>
                        <label htmlFor="rating2" className="ep-lbl">2</label><br/>
                        <input type="radio" id="rating" name="rating" value="3"
                               onChange={this.handleChange}/>
                        <label htmlFor="rating3" className="ep-lbl">3</label><br/>
                        <input type="radio" id="rating" name="rating" value="4"
                               onChange={this.handleChange}/>
                        <label htmlFor="rating4" className="ep-lbl">4</label><br/>
                        <input type="radio" id="rating" name="rating" value="5"
                               onChange={this.handleChange}/>
                        <label htmlFor="rating5" className="ep-lbl">5</label><br/>
                    </p>
                    <p>
                        <label htmlFor="members" className="ep-lbl">Members:</label>
                        <input type="text" id="members" value={this.state.members} pattern="\d+(, \d+)*"
                               title="Member IDs should have a format of '1, 34, 66, 3' etc."
                               onChange={this.handleChange} required/>
                    </p>
                    <p>
                        <input type="submit" value="Submit" className="ep-btn" onClick={() => this.handleShowPopup(true)}/>
                        <input type="reset" value="Reset" className="ep-btn"/>
                        <input type="button" value="Overview" className="ep-btn" onClick={() => this.props.history.push('/pictures')} />
                    </p>
                </form>
                <Popup trigger={this.state.showPopup} setShowPopup={this.handleShowPopup} >
                    <h3>status message:</h3>
                    <p className="ep-messagePopup">{this.state.message}</p>
                </Popup>
            </div>
        )
    }
}

export default CreateEventPicture;