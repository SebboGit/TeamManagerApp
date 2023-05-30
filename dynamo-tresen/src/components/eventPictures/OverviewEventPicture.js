import {Component} from "react";
import "./css/GeneralEventPicture.css"
/**
 * this class represents the overview of all functions for an Event Picture
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
class OverviewEventPicture extends Component {

    render() {
        return (
            <div className="ep-layout">
                <h1>This is the Overview for the Event Picture Service!</h1>
                <button className="ep-btn" onClick={() => this.props.history.push('/pictures/create')}>Create an Event Picture</button>
                <button className="ep-btn" onClick={() => this.props.history.push('/pictures/delete')}>Delete an Event Picture</button>
                <button className="ep-btn" onClick={() => this.props.history.push('/pictures/change')}>Change an Event Picture</button>
                <button className="ep-btn" onClick={() => this.props.history.push('/pictures/get')}>Get an Event Picture</button>
            </div>
        )
    }
}

export default OverviewEventPicture;