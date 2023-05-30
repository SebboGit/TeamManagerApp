import React from 'react';

import FeeService from './service/FeeService';
import Popup from './misc/Popup';

import './css/testdata.css';

/**
 * Button component that adds test data when pressed, after asking for confirmation with a popup.
 * Confirms the creation with a flash by using the passed in 'setFlashState'. 
 * 
 * @author Jonas Buse
 */
export const TestData = ({ setFlashState }) => {

    const [showPopup, setShowPopup] = React.useState(false);

    const addTestData = async () => {
        setShowPopup(false);

        const randomFees = [];

        [...Array(15).keys()].forEach(() =>
            randomFees.push(createRandomFee())
        );

        const resp = await FeeService.createFees(randomFees);
        if (resp.status === 201) {
            setFlashState({ show: true, type: "info", text: "Test-Data added." });
        } else {
            setFlashState({ show: true, type: "error", text: "Whoops something went wrong." });
        }
    };

    return (
        <>
            <button onClick={() => setShowPopup(true)} className="add-test-fees" >
                <span className="icon">science</span>Test Data
            </button>
            {showPopup &&
                <Popup
                    title="Add test fees."
                    text="This will add some random fees to fill out the table."
                    subText="You could also generate new fees with the 'Calulate' button."
                    cancelAction={() => setShowPopup(false)}
                    btn1Label="Cancel"
                    btn1Action={() => setShowPopup(false)}
                    btn2Label="Add"
                    btn2Action={addTestData}
                    btn2Class="confirm"
                />}
        </>
    );

};

/**
 * Function that creates a fee with random values from the 01. January 
 * in this year undtil the current month with a random day until the 28.
 * 
 * @author Jonas Buse
 */
const createRandomFee = () => {
    const today = new Date();
    const randomPaddedMonth = ("00" + Math.floor(Math.random() * today.getMonth() + 1)).slice(-2);
    const randomPaddedDay = ("00" + Math.floor(Math.random() * 28 + 1)).slice(-2);

    return {
        date: `2021-${randomPaddedMonth}-${randomPaddedDay}`,
        amount: Math.floor(Math.random() * 10 + 1) * 10,
        status: (Math.floor(Math.random() * 2) === 1) ? "pending" : "confirmed",
        memberId: Math.floor(Math.random() * 20 + 1)
    }
};
