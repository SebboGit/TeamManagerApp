
import '../css/fee.css';

/**
 * Component that represents a single fee in a table of fees. Short version.
 * Used in the calculation screen.
 *
 * @props props - the different attributes of the fee
 *
 * @author Jonas Buse
 */
const FeeShort = (props) => {


    return (
        <>
            <div className="fee">
                <div className="fee-header">
                    <h3>Fee</h3>
                    <div className="fee-id">#{props.id || "Preview"}</div>
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


                </div>
            </div>

        </>
    );
}


export default FeeShort;