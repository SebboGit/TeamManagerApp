import React from 'react';
import { useHistory } from 'react-router-dom';

import FeeService from './service/FeeService';
import FeeTable from './FeeTable/FeeTable';
import CreateFee from './CreateFee';
import { TestData } from './TestData';
import Flash from './misc/Flash';

import './css/overview.css';

/**
 * Overview site of all fees.
 * 
 * @author Jonas Buse
 */
const FeeOverview = () => {
    const [dataState, setDataState] = React.useState([]);
    const [showCreateFee, setShowCreateFee] = React.useState(false);
    const [flashState, setFlashState] = React.useState({ show: false, type: "", text: "" });
    const [refreshList, setRefreshList] = React.useState(0);

    // useEffect hook that runs once mounted and every time the 
    // flashState changes, ie a new fee was created, deleted or updated
    // or when refreshList state is updated
    React.useEffect(() => {
        const fetchData = async () => {
            const data = await FeeService.getAllFees();
            setDataState(data);
        }
        fetchData();
    }, [setDataState, flashState, refreshList]);

    const history = useHistory();

    return (
        <div className="FeeOverview">
            {flashState.show && <Flash type={flashState.type} text={flashState.text} setFlashState={setFlashState} />}
            <h1>Fee Overview</h1>
            <button onClick={() => setShowCreateFee(true)} className="new-fee-btn" >
                <span className="icon">add_circle</span>New
            </button>
            <button onClick={() => setRefreshList(c => (c += 1))} className="refresh-fees-btn" >
                <span className="icon">refresh</span>Refresh
            </button>
            <button onClick={() => history.push("/fees/calculate")} className="calc-fee-btn" >
                <span className="icon">calculate</span>Calculate
            </button>
            {showCreateFee && <CreateFee showCreateFee={setShowCreateFee} setFlashState={setFlashState} />}
            <FeeTable fees={dataState} setFlashState={setFlashState} showFeeLong={true} />

            <TestData setFlashState={setFlashState} />
        </div>
    );
}

export default FeeOverview;