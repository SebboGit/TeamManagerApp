import React, {Component} from "react";
import EventPictureService from "../services/EventPictureService";
import Popup from "../Popup";
import "../css/GetEventPicture.css";
import "../css/GeneralEventPicture.css";
import "../css/Popup.css";

/**
 * This class represents the functionality to get an Event Picture.
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
class GetEventPicture extends Component {

    state = {
        id: 0,
        eventId: 0,
        titleId: '',
        descriptionId: '',
        ratingId: 0,
        showPopup: false,
        message: "Ooops, something went wrong!\nPlease check following:\n\t- Do you have a stable connection to the server?\n\t- Have you entered a value in the Description or Title?",
        eventPictures: []
    }

    /**
     * Prefill the eventId input with the value from a calendar event id if present.
     * set the state accordingly
     */
    componentDidMount() {
        if (typeof this.props.location.eventIdForm !== "undefined") {
            document.getElementById("eventId").value = this.props.location.eventIdForm.num;
            this.setState({
                eventId: this.props.location.eventIdForm.num
            })
        }
    }

    /**
     * This method handles sets the state showPopup, if it should pop up or should be closed.
     * When the state showPopup is false, the state eventPictures is set to default.
     *
     * @param showPopup
     */
    handleShowPopup = (showPopup) => {
        this.setState({
            showPopup: showPopup
        })
        if (this.state.showPopup === false) {
            this.setState({
                message: "Ooops, something went wrong!\nPlease check following:\n\t- Do you have a stable connection to the server?\n\t- Have you entered a value in the Description or Title?",
                eventPictures: []
            })
        }
    }

    /**
     * This method handles the 'Get via ID' Button.
     * It calls the getEventPicture() method from the EventPictureService.js and passes the actual ID.
     * If the response is type of 'string', then the message will be set an gives the Client hints.
     * Otherwise if the response from the server is OK (200), then the response will be set to the state.
     *
     * @param event
     * @returns {Promise<string>}
     */
    handleSubmitGetById = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.getEventPicture(this.state.id);
        if (typeof response === `string`) {
            this.setState({
                message: response
            })
        } else {
            this.setState({
                eventPictures: [response]
            })
        }
    }

    /**
     * This method handles the 'Get via Event ID' Button.
     * Before the Event Picture will be get, the existence of the Event ID is verified via the getEventIdExistence().
     * If the Event ID exists, then the Event Picture will be received. Otherwise a message for the Client is set.
     * Afterwards it calls the getEventPictureByEvent() method from the EventPictureService.js and passes the actual eventId.
     * If the response is type of 'string', then the message will be set an gives the Client hints.
     * Otherwise if the response from the server is OK (200), then the response will be set to the state.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmitGetByEventId = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const responseEventId = await service.getEventIdExistence(this.state.eventId);
        if (responseEventId.status === 200) {
            const response = await service.getEventPictureByEventId(this.state.eventId);
            if (typeof response === `string`) {
                this.setState({
                    message: response
                })
            } else {
                this.setState({
                    eventPictures: response
                })
            }
        } else {
            this.setState({
                message: `Sorry, the Event with ID '${this.state.eventId}' does not exist!`
            })
        }
    }

    /**
     * This method handles the 'Get via Title' Button.
     * It calls the getEventPictureByTitle() method from the EventPictureService.js and passes the actual titleId.
     * If the response is type of 'string', then the message will be set an gives the Client hints.
     * Otherwise if the response from the server is OK (200), then the response will be set to the state.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmitGetByTitle = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.getEventPictureByTitle(this.state.titleId);
        if (typeof response === `string`) {
            this.setState({
                message: response
            })
        } else {
            this.setState({
                eventPictures: response
            })
        }
    }

    /**
     * This method handles the 'Get via Description' Button.
     * It calls the getEventPictureByDescription() method from the EventPictureService.js and passes the actual descriptionId.
     * If the response is type of 'string', then the message will be set an gives the Client hints.
     * Otherwise if the response from the server is OK (200), then the response will be set to the state.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmitGetByDescription = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.getEventPictureByDescription(this.state.descriptionId);
        if (typeof response === `string`) {
            this.setState({
                message: response
            })
        } else {
            this.setState({
                eventPictures: response
            })
        }
    }

    /**
     * This method handles the 'Get via Rating' Button.
     * It calls the getEventPictureByRating() method from the EventPictureService.js and passes the actual ratingId.
     * If the response is type of 'string', then the message will be set an gives the Client hints.
     * Otherwise if the response from the server is OK (200), then the response will be set to the state.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmitGetByRating = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.getEventPictureByRating(this.state.ratingId);
        if (typeof response === `string`) {
            this.setState({
                message: response
            })
        } else {
            this.setState({
                eventPictures: response
            })
        }
    }

    /**
     * This method handles the 'Get all' Button.
     * It calls the getAllEventPictures() method from the EventPictureService.js.
     * If the response is type of 'string', then the message will be set an gives the Client hints.
     * Otherwise if the response from the server is OK (200), then the response will be set to the state.
     *
     * @param event
     * @returns {Promise<void>}
     */
    handleSubmitGetAll = async (event) => {
        event.preventDefault();
        const service = new EventPictureService();
        const response = await service.getAllEventPictures();
        if (typeof response === `string`) {
            this.setState({
                imageData: null,
                message: response
            })
        } else {
            this.setState({
                id: response.id,
                eventPictures: response
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

    render() {
        return (
            <div className="ep-layout">
                <h1>Here you can get an Event Picture!</h1>
                <form onSubmit={this.handleSubmitGetById} className="ep-form" key="id">
                    <label htmlFor="id" className="ep-lbl">ID:</label>
                    <input type="number" id="id" onChange={this.handleChange}/>
                    <input type="submit" value="Get via ID" className="ep-btn-submit"
                           onClick={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                </form>
                <form onSubmit={this.handleSubmitGetByEventId} className="ep-form" key="eventId">
                    <label htmlFor="event" className="ep-lbl">Event ID:</label>
                    <input type="number" id="eventId" onChange={this.handleChange}/>
                    <input type="submit" value="Get via Event ID" className="ep-btn-submit"
                           onClick={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                </form>
                <form onSubmit={this.handleSubmitGetByTitle} className="ep-form" key="title">
                    <label htmlFor="title" className="ep-lbl">Title:</label>
                    <input type="text" id="titleId" onChange={this.handleChange}/>
                    <input type="submit" value="Get via Title" className="ep-btn-submit"
                           onClick={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                </form>
                <form onSubmit={this.handleSubmitGetByDescription} className="ep-form" key="description">
                    <label htmlFor="description" className="ep-lbl">Description:</label>
                    <input type="text" id="descriptionId" onChange={this.handleChange}/>
                    <input type="submit" value="Get via Description" className="ep-btn-submit"
                           onClick={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                </form>
                <form onSubmit={this.handleSubmitGetByRating} className="ep-form" key="rating">
                    <label htmlFor="rating" className="ep-lbl">Rating:</label>
                    <input type="number" id="ratingId" min={1} max={5} onChange={this.handleChange}/>
                    <input type="submit" value="Get via Rating" className="ep-btn-submit-rating"
                           onClick={() => this.handleShowPopup(true)}/>
                    <input type="reset" value="Reset" className="ep-btn"/>
                </form>
                <form onSubmit={this.handleSubmitGetAll} className="ep-form" key="all">
                    <input type="submit" value="Get all" className="ep-btn-submit-rating"
                           onClick={() => this.handleShowPopup(true)}/>
                </form>
                <input type="button" value="Overview" className="ep-btn-overview"
                       onClick={() => this.props.history.push('/pictures')} key="overview"/>
                <Popup trigger={this.state.showPopup} setShowPopup={this.handleShowPopup}>
                    <h3>status message:</h3>
                    {this.state.eventPictures.length > 0 ? this.state.eventPictures.map((event) => (
                        <div className="ep-popupEP">
                            <p className="ep-messagePopup">
                                {`Here is your Event Picture with ID '${event.id}':\n\nTitle:\t\t${event.title}\nDescription:\t${event.description}\nRating:\t\t${event.rating}\nEvent:\t\t${event.event}\nMembers:\t${event.members}\nImage:\t\t${event.picName}`}
                            </p>
                            <img src={`data:multipart/form-data;base64,${event.picData}`} className="ep-img-resize"
                                 alt=""/>
                        </div>
                    )) : <p className="ep-messagePopup">{this.state.message}</p>}
                </Popup>
            </div>
        )
    }
}

export default GetEventPicture;