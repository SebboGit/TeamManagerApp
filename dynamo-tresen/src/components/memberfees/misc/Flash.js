import React from 'react';
import PropTypes from 'prop-types';
import ReactDom from 'react-dom';

import '../css/flash.css';

/**
 * Generic Flash message with customizable text and 4 different types (info, success, warning, error).
 * Flash will float in the middle of the screen (non obstructing) and dissapear once moused over or 
 * after a few seconds. 
 * Props: type, text, and setFlashState
 * 
 * @author Jonas Buse
 */
const Flash = (props) => {
    const [classState, setClassState] = React.useState("");
    const setShowFlash = props.setFlashState;

    /**
     * This method starts the fade-out and then removes the flash from the screen.
     */
    const removeFlash = () => {
        setClassState("flash-fadeout");
        setTimeout(() => setShowFlash({ show: false }), 500);
    };

    // using portal to avoid problems with parent styling and formatting
    return ReactDom.createPortal(
        <div className={props.type + ' flash ' + classState} onClick={removeFlash}>
            <span className="icon">{(props.type === 'success') ? 'check_circle' : props.type}</span>
            {props.text}
            <div className="flash-note">Click me to remove!</div>
        </div>,
        document.getElementById('portal')
    );
}

Flash.propTypes = {
    type: PropTypes.oneOf(['info', 'success', 'warning', 'error']).isRequired,
    text: PropTypes.string,
    setFlashState: PropTypes.func.isRequired
}

Flash.defaultProps = {
    type: 'info'
}

export default Flash;