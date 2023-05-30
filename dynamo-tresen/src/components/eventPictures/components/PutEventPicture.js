import React, {Component} from "react";
import EventPictureService from "../services/EventPictureService";
import Popup from "../Popup";
import "../css/GeneralEventPicture.css";
import "../css/PutEventPicture.css";
import "../css/Popup.css";

/**
 * This class represents the functionality to change an Event Picture.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
class PutEventPicture extends Component {

    state = {
        id: 0,
        title: '',
        description: '',
        rating: '',
        event: 0,
        members: [],
        imageName: '',
        imageType: '',
        imageData: null,
        imageNew: null,
        imageChange: false,
        showPopup: false,
        message: "Ooops, something went wrong!\nPlease check following:\n\t- Do you have a stable connection to the server?"
    }

    /**
     * This method makes the message show up when you click the Submit Button to change an Event Picture
     */
    onButtonClick = () => {
        document.getElementById('messageInput').className = "ep-show";
    }

    /**
     * This method handles the state showPopup, if it should pop up or should be closed.
     *
     * @param showPopup
     */
    handleShowPopup = (showPopup) => {
        this.setState({
            showPopup: showPopup
        })
    }

    /**
     * This method handles the switch button in the UI, if it should be true or false.
     */
    handleSwitch = () => {
        if (this.state.imageChange) {
            this.setState({
                imageChange: false
            })
        } else {
            this.setState({
                imageChange: true
            })
        }
    }

    /**
     * This method changes the state imageChange to false, when the Reset Button is clicked
     */
    handleReset = () => {
        this.setState({
            imageChange: false
        })
    }

    /**
     * This method is for getting the data of the desired Event Picture after searching via the ID field.
     * If the response is a 'string', then a message will be returned.
     * Else the data is stored in the state.
     *
     * @param event
     * @returns {Promise<void>}
     */
    getData = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.getEventPicture(this.state.id);
        if (typeof response === `string`) {
            this.setState({
                imageData: null,
                message: response
            })
        } else {
            this.setState({
                id: response.id,
                title: response.title,
                description: response.description,
                rating: response.rating,
                event: response.event,
                members: response.members,
                imageName: response.picName,
                imageType: response.picType,
                imageData: response.picData
            })
            this.setState({
                message: `Here is your Event Picture with ID '${this.state.id}':\n\nTitle:\t\t${this.state.title}\nDescription:\t${this.state.description}\nRating:\t\t${this.state.rating}\nEvent:\t\t${this.state.event}\nMembers:\t${this.state.members}\nImage:\t\t${this.state.imageName}`
            })
        }
    }

    /**
     * This method handles the Submit Button to update an Event Picture by calling the putEventPicture() method.
     * Before the update, the existence of the Event ID is verified via the getEventIdExistence().
     * If the Event ID exists, then the Event Picture will be updated. Otherwise a message for the Client is set.
     * If the upload was successful, then the state will be set back to the default values.
     * Else a message will be returned.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmit = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const responseEventId = await service.getEventIdExistence(this.state.event);
        if (responseEventId.status === 200) {
            const response = await service.putEventPicture(this.state);
            if (response.status === 200) {
                this.setState({
                    message: `Event Picture updated successfully!`,
                    titel: '',
                    description: '',
                    rating: '',
                    event: 0,
                    members: [],
                    imageName: '',
                    imageType: '',
                    imageData: null,
                    imageNew: null,
                    showPopup: false
                })
            } else {
                this.setState({
                    message: "Ooops, something went wrong!\nPlease check following:\n\t- Have you entered an ID?\n\t- The ID should be at least 1.\n\t- Maybe the ID is not present in the Database."
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
     * If the switch to change a picture is true, the state will be updated with the new picture value.
     *
     * @param evt
     */
    handleChangeImage = async (evt) => {
        if (this.state.imageChange === true) {
            this.setState({
                imageNew: evt.target.files[0]
            });
        }
    }

    render() {
        return (
            <div className="ep-layout">
                <h1>Here you can change an Event Picture!</h1>
                <form className="ep-form">
                    <p>
                        <label htmlFor="id" className="ep-lbl">ID:</label>
                        <input type="number" id="id" onChange={this.handleChange}/>
                    </p>
                    <input type="submit" value="Submit" className="ep-btn" onClick={this.getData}
                           onClickCapture={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                </form>
                <Popup trigger={this.state.showPopup} setShowPopup={this.handleShowPopup}>
                    <h3>status message:</h3>
                    <p className="ep-messagePopup">{this.state.message}</p>
                    <img src={`data:multipart/form-data;base64,${this.state.imageData}`} className="ep-img-resize"
                         alt=""/>
                </Popup>
                <form onSubmit={this.handleSubmit} className="ep-form">
                    <img src={`data:multipart/form-data;base64,${this.state.imageData}`} className="ep-img-resize"
                         alt=""/>
                    <p>Do you want to change the image? Please click the switch below</p>
                    <label className="switch">
                        <input type="checkbox" onClick={this.handleSwitch}/>
                        <span className="slider"> </span>
                    </label>
                    <p>
                        <label htmlFor="image" className="ep-lbl">Image:</label>
                        <input type="file" id="imageNew" name="file" accept="image/*" className="ep-input"
                               onChange={this.handleChangeImage}/>
                    </p>
                    <p>
                        <label htmlFor="title" className="ep-lbl">Title:</label>
                        <input type="text" id="title" value={this.state.title} onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label htmlFor="description" className="ep-lbl">Description:</label>
                        <input type="text" id="description" value={this.state.description}
                               onChange={this.handleChange}/>
                    </p>
                    <p>
                        <label htmlFor="event" className="ep-lbl">Event:</label>
                        <input type="number" id="event" value={this.state.event} onChange={this.handleChange} required/>
                    </p>
                    <p>
                        <label htmlFor="rating" className="ep-lbl">Rating:</label>
                        <label>actual: {this.state.rating}</label><br/>
                        <input type="radio" id="rating" name="rating" value="1"
                               onChange={this.handleChange}/>
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
                    <div id="messageInput" className="ep-hide">
                        <b>{this.state.message}</b>
                    </div>
                    <p>
                        <input type="submit" value="Submit" className="ep-btn" onClick={this.onButtonClick}/>
                        <input type="reset" value="Reset" className="ep-btn" onClick={this.handleReset}/>
                        <input type="button" className="ep-btn" onClick={() => this.props.history.push('/pictures')}
                               value="Overview"/>
                    </p>
                </form>
            </div>
        )
    }
}

export default PutEventPicture;