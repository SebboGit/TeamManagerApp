import React from 'react';

import FeeService from './service/FeeService';

import './css/changefee.css';

/**
 * Popup Component used for changing existing fees. The existing values
 * are prefilled into the fields and change visibly when edited.
 * 
 * Some minor data validation is also performed before putting, with
 * visual feedback if anything went wrong.
 * 
 * @author Jonas Buse
 */
const ChangeFee = (props) => {
    const [state, setState] = React.useState({ memberId: props.memberId, date: props.date, amount: props.amount, status: props.status });
    const [errorState, setErrorState] = React.useState({ memberId: false, date: false, amount: false, status: false });


    /**
    * Called upon once the form is submitted. 
    * This function calls on the service to save the changed fee. 
    * If the response status is 200 (OK) the component will close again.
    */
    const createAndPost = async (evt) => {
        evt.preventDefault();
        if (dataHasErrors())
            return;

        const resp = await FeeService.changeFee(state, props.id);
        if (resp.status === 200) {
            props.setFlashState({ show: true, type: "success", text: `Fee #${props.id} changed successfully!` });
            props.setShowChangeFee(false);
        }
        else {
            let responseText = await resp.text();
            console.log("Error: ", resp.status, responseText);
            props.setFlashState({ show: true, type: "error", text: responseText });
        }

    };

    /**
     * Called upon once a change to the input fields is made.
     * Instantly saves the change to the state.
     */
    const inputChange = (evt) => {
        setState(prevState => ({
            ...prevState,
            [evt.target.id]: evt.target.value
        }));
    };

    /**
     * Perform a first validation of the data before posting it to the api.
     * Faulty inputs are saved to state and displayed through the class and css.
     */
    const dataHasErrors = () => {
        const errors = {
            memberId: (state.memberId > 0) ? !/^\d+$/.test(state.memberId) : true,
            date: (state.date !== "") ? !/^\d{4}-\d{2}-\d{2}$/.test(state.date) : true,
            amount: (state.amount > 0) ? !/^\d+$/.test(state.amount) : true,
            status: (state.status !== "") ? !/^(pending|confirmed)$/.test(state.status) : true
        };

        setErrorState(errors);

        return Object.values(errors).filter(c => (c === true)).length > 0;
    };

    return (
        <div className="change-fee-outer">
            <div className="change-fee-container">
                <div onClick={() => props.setShowChangeFee(false)} className="icon close-change">close</div>
                <div className="change-fee-heading">
                    <h2>Change Fee #{props.id}</h2>
                </div>
                <form className="change-fee-form" onSubmit={createAndPost}>
                    <label>Member ID:
                        <input id="memberId" onChange={inputChange} value={state.memberId}
                            className={(errorState.memberId ? "change-error " : "") + ((state.memberId === props.memberId) ? "" : "changed-value")}>
                        </input>
                    </label>
                    <label>Date:
                        <input type="date" id="date" onChange={inputChange} value={state.date}
                            className={(errorState.date ? "change-error " : "") + ((state.date === props.date) ? "" : "changed-value")}>
                        </input>
                    </label>
                    <label>Amount:
                        <input id="amount" onChange={inputChange} value={state.amount}
                            className={(errorState.amount ? "change-error " : "") + ((state.amount === props.amount) ? "" : "changed-value")}>
                        </input>
                    </label>
                    <label>Status:
                        <input id="status" list="statuses" onChange={inputChange} value={state.status}
                            className={(errorState.status ? "change-error " : "") + ((state.status === props.status) ? "" : "changed-value")}>
                        </input>
                        <datalist id="statuses">
                            <option value="confirmed" />
                            <option value="pending" />
                        </datalist>
                    </label>
                    <button className="change-fee-btn">Save</button>
                </form>
            </div>
        </div>
    );
};


export default ChangeFee;