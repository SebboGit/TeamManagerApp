import '../css/popup.css';
import PropTypes from 'prop-types';

/**
 * Generic Popup with customizable Text, Button Labels and actions.
 * Up to three Buttons can be used. The relevant functions and Strings can
 * be passed as props.
 * 
 * @param props cancelAction, text, btn1Label, btn1Action etc. 
 * @returns Popup component
 * 
 * @author Jonas Buse
 */
const Popup = (props) => (

    <div className='popup_outer'>
        <div className='popup_box'>
            <span className="close_span" onClick={props.cancelAction}>&times;</span>
            <div className="popup_header">{props.title}</div>
            <div className="popup_content">
                <p className="popup_text">{props.text}</p>
                <p className="popup_text_sub">{props.subText}</p>
                {props.btn1Label && <button onClick={props.btn1Action} className={"popup_button " + props.btn1Class}>{props.btn1Label}</button>}
                {props.btn2Label && <button onClick={props.btn2Action} className={"popup_button " + props.btn2Class}>{props.btn2Label}</button>}
                {props.btn3Label && <button onClick={props.btn3Action} className={"popup_button " + props.btn3Class}>{props.btn3Label}</button>}
            </div>
        </div>
    </div >
);

Popup.propTypes = {
    title: PropTypes.string,
    cancelAction: PropTypes.func,
    btn1Action: PropTypes.func,
    btn2Action: PropTypes.func,
    btn3Action: PropTypes.func
}

export default Popup;