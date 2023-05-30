import {Component} from "react";
import "./css/Popup.css";

class Popup extends Component {

    render(props) {
        return (this.props.trigger) ? (
            <div className="popup">
                <div className="popup_inner">
                    <button className="buttons" onClick={() => this.props.setShowPopup(false)}>close</button>
                    {this.props.children}
                </div>
            </div>
        ) : "";
    }
}

export default Popup;