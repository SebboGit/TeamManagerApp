import React from 'react';
import { useHistory } from "react-router";

import FeeService from './service/FeeService';
import FeeTable from './FeeTable/FeeTable';
import Flash from './misc/Flash';
import Popup from './misc/Popup';

import './css/calculateFee.css';

/**
 * Component that is responsible for every action regarding the automatic 
 * calculation of new fees. There is a simulation and a Calculation Button.
 * 
 * @author Jonas Buse
 */
const CalculateFees = () => {
    const today = new Date();

    const [fees, setFees] = React.useState([]);
    const [year, setYear] = React.useState(today.getFullYear());
    const [dateRange, setDateRange] = React.useState(`2021-01-01 - ${today.toISOString().split('T')[0]}`)
    const [wrongInput, setWrongInput] = React.useState(false);
    const [showApprovePopup, setShowApprovePopup] = React.useState(false);
    const [feesCreated, setShowFeesCreated] = React.useState(false);
    const [flashState, setFlashState] = React.useState({ show: false, type: "", text: "" });

    const history = useHistory();

    /**
     * Handle the changed input and save it to state. Change the visual state according to input.
     */
    const handleYearChange = (e) => {
        setYear(e.target.value);
        if (!/^\d{4}$/.test(e.target.value)) {
            setWrongInput(true);
            return;
        }
        setWrongInput(false);
        setDateRange(`${e.target.value}-01-01 - ${(parseInt(e.target.value) === today.getFullYear()) ? today.toISOString().split('T')[0] : e.target.value + "-12-01"}`);
    };

    /**
     * Function that is run to simulate the results and return them.
     */
    const simulateCalculation = async () => {
        if (wrongInput)
            return;
        setShowFeesCreated(false);
        const resp = await FeeService.calculateFees(year, false);
        if (resp.status === 200) {
            setFees(await resp.json());
        }
        else {
            let responseText = await resp.text();
            console.log("Error: ", resp.status, responseText);
            setFlashState({ show: true, type: "error", text: responseText });
        }
    };

    /**
     * Function that is run to calculate the results, save and return them.
     */
    const calculateFees = async () => {
        const resp = await FeeService.calculateFees(year, true);
        if (resp.status === 201) {
            setFees(await resp.json());
            setShowApprovePopup(false);
            setFlashState({ show: true, type: "success", text: `Calculation successful.` });
            setShowFeesCreated(true);
        }
        else {
            let responseText = await resp.text();
            console.log("Error: ", resp.status, responseText);
            setFlashState({ show: true, type: "error", text: responseText });
        }
    };

    return (
        <>
            <div className="calc-fees">
                <h1>Calculate new Fees</h1>

                <button onClick={() => history.push("/fees")} className="calc-fee-btn-return" >
                    <span className="icon">arrow_back_ios_new</span>Return
                </button>


                <input value={year} onChange={handleYearChange} className={wrongInput ? "calc-fees-input fee-error" : "calc-fees-input"} />

                <div className="calc-fees-daterange">
                    <span className="icon">schedule</span>
                    {dateRange}
                </div>

                <div className="calc-fees-btns">
                    <button onClick={simulateCalculation} >Simulate</button>
                    <button onClick={() => { if (!wrongInput) setShowApprovePopup(true); }} >Calculate</button>
                </div>
                {feesCreated && <h3>The following fees were created:</h3>}
                {fees.length !== 0 && <FeeTable fees={fees} setFlashState={setFlashState} />}
            </div>


            {showApprovePopup && <Popup
                title="Are you sure?"
                text={`This will create new Fees for all members regarding the year ${year}.`}
                subText="You could simulate first to see the results and confirm them."
                cancelAction={() => setShowApprovePopup(false)}
                btn1Label="Cancel"
                btn1Action={() => setShowApprovePopup(false)}
                btn2Label="Confirm"
                btn2Action={calculateFees}
                btn2Class="confirm"
            />}

            {flashState.show && <Flash type={flashState.type} text={flashState.text} setFlashState={setFlashState} />}
        </>
    );
};

export default CalculateFees;