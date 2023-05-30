import React from 'react';

import FeeService from './service/FeeService';
import PropTypes from 'prop-types';

import './css/createFee.css';

/**
 * Component that handles the creation of new fees with a form.
 * 
 * @author Jonas Buse
 */
const CreateFee = ({ showCreateFee, setFlashState }) => {
    const [state, setState] = React.useState({ memberId: 0, date: "", amount: 0, status: "" });
    const [errorState, setErrorState] = React.useState({ memberId: false, date: false, amount: false, status: false });

    /**
     * Called upon once the form is submitted. 
     * This function calls on the service to save the new fee. 
     * If the response status is 201 (created) the component will close again.
     */
    const createAndPost = async (evt) => {
        evt.preventDefault();
        if (dataHasErrors())
            return;

        const resp = await FeeService.createFee(state);
        if (resp.status === 201) {
            setFlashState({ show: true, type: "success", text: "Fee created successfully!" });
            showCreateFee(false);
        }
        else {
            let responseText = await resp.text();
            console.log("Error: ", resp.status, responseText);
            setFlashState({ show: true, type: "error", text: responseText });
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
        <div className="create-fee-outer">
            <div className="create-fee-container">
                <div onClick={() => showCreateFee(false)} className="icon close-create">close</div>
                <div className="create-fee-heading">
                    <h2>Create a new Fee</h2>
                </div>
                <small>ID will be generated automatically.</small>
                <form onSubmit={createAndPost} className="create-fee-form">
                    <label>Member ID:
                        <input id="memberId" onChange={inputChange} placeholder="e.g. 2" className={errorState.memberId ? "create-error" : ""}></input>
                    </label>
                    <label>Date:
                        <input type="date" id="date" onChange={inputChange} className={errorState.date ? "create-error" : ""}></input>
                    </label>
                    <label>Amount:
                        <input id="amount" onChange={inputChange} placeholder="e.g. 50" className={errorState.amount ? "create-error" : ""}></input>
                    </label>
                    <label>Status:
                        <input id="status" list="statuses" onChange={inputChange} placeholder="e.g. pending" className={errorState.status ? "create-error" : ""}></input>
                        <datalist id="statuses">
                            <option value="confirmed" />
                            <option value="pending" />
                        </datalist>
                    </label>
                    <button className="create-fee-btn">Create</button>
                </form>
            </div>
        </div >
    );
}

CreateFee.propTypes = {
    setFlashState: PropTypes.func.isRequired,
    showCreateFee: PropTypes.func.isRequired
}

export default CreateFee;