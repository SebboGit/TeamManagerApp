import {Component} from "react";
import './css/Popup.css';

class Popup extends Component {
    render(props) {
        return (this.props.trigger) ? (
            <div className="ep-popup">
                <div className="ep-popup-inner">
                    <button className="close-btn" onClick={() => this.props.setShowPopup(false)}>close</button>
                    {this.props.children}
                </div>
            </div>
        ) : "";
    }
}

export default Popup;