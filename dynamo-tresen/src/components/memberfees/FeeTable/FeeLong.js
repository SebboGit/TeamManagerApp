import React from 'react';

import FeeService from '../service/FeeService';
import ChangeFee from '../ChangeFee';
import Popup from '../misc/Popup';

import '../css/fee.css';

/**
 * Component that represents a single fee in a table of fees. Long version.
 * 
 * @props props - the different attributes of the fee
 * 
 * @author Jonas Buse
 */
export const FeeLong = (props) => {
    const [showDeletePopup, setShowDeletePopup] = React.useState(false);
    const [showApprovePopup, setShowApprovePopup] = React.useState(false);
    const [showChangePopup, setShowChangePopup] = React.useState(false);

    /**
     * Deletes a fee and handles the responses.
     */
    const deleteFee = async () => {
        setShowDeletePopup(false);
        const resp = await FeeService.deleteFee(props.id);

        if (resp.status === 200) {
            props.setFlashState({ show: true, type: "success", text: `Fee #${props.id} deleted successfully!` });
        }
        else {
            let responseText = await resp.text();
            console.log("Error: ", resp.status, responseText);
            props.setFlashState({ show: true, type: "error", text: responseText });
        }
    };

    /**
     * Approve Fee to status "confirmed".
     */
    const confirmFee = async () => {
        setShowApprovePopup(false);
        const resp = await FeeService.confirmFee(props.id);

        if (resp.status === 200) {
            props.setFlashState({ show: true, type: "success", text: `Fee #${props.id} confirmed!` });
        }
        else {
            let responseText = await resp.text();
            console.log("Error: ", resp.status, responseText);
            props.setFlashState({ show: true, type: "error", text: responseText });
        }
    };

    return (
        <>
            <div className="fee">
                <div className="fee-header">
                    <h3>Fee</h3>
                    <div className="fee-id">#{props.id}</div>
                </div>
                <div className="fee-info">
                    <div>
                        <h6>{props.date}</h6>
                        <h6>Member: {props.memberId}</h6>
                        <h6 className={"fee-status " + props.status}>
                            <span className="icon" >{(props.status === "pending") ? "hourglass_top" : "verified"}</span>
                            {props.status}
                        </h6>
                    </div>
                    <h2>{props.amount} €</h2>

                    {props.status === "pending" &&
                        <div className="btn-fee appr-btn" onClick={() => setShowApprovePopup(true)}>
                            <div className="icon">done</div>
                        </div>
                    }
                    <div className="btn-fee edit-btn" onClick={() => setShowChangePopup(true)}>
                        <div className="icon">edit</div>
                    </div>
                    <div className="btn-fee delete-btn" onClick={() => setShowDeletePopup(true)}>
                        <div className="icon hover">delete_forever</div>
                        <div className="icon standard">delete_outline</div>
                    </div>
                </div>
            </div>
            {showDeletePopup && <Popup
                title="Are you sure?"
                text="This will delete the Fee permanently. It won't be recoverable after that."
                subText={`Fee ${props.id}: Member ID=${props.memberId}, Date=${props.date}, Amount=${props.amount}, Status=${props.status}`}
                cancelAction={() => setShowDeletePopup(false)}
                btn1Label="Cancel"
                btn1Action={() => setShowDeletePopup(false)}
                btn2Label="Delete"
                btn2Action={deleteFee}
                btn2Class="delete"
            />}
            {showApprovePopup && <Popup
                title="Are you sure?"
                text="This will confirm the Fee to be payed. You can always change the status later."
                subText={`Fee ${props.id}: Member ID=${props.memberId}, Date=${props.date}, Amount=${props.amount}, Status=${props.status}`}
                cancelAction={() => setShowApprovePopup(false)}
                btn1Label="Cancel"
                btn1Action={() => setShowApprovePopup(false)}
                btn2Label="Confirm"
                btn2Action={confirmFee}
                btn2Class="confirm"
            />}
            {showChangePopup &&
                <ChangeFee
                    id={props.id}
                    memberId={props.memberId}
                    date={props.date}
                    amount={props.amount}
                    status={props.status}
                    setFlashState={props.setFlashState}
                    setShowChangeFee={setShowChangePopup}
                />}
        </>
    );
}
