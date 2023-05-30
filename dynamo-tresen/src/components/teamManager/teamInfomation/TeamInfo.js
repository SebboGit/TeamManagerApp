import React from "react";
import '../css/teamManager.css';
import {Route} from "react-router-dom";
import InfoTable from "./InfoTable";
import InfomaterialService from "../service/InfomaterialService";
import CreateInfomaterial from "./CreateInfomaterial";

const TeamInfo = () => {

    const [dataState, setDataState] = React.useState([]);
    const [refresh, setRefresh] = React.useState([]);

    React.useEffect(() => {
        const fetchData = async () => {
            const data = await InfomaterialService.getAllInfomaterials();
            setDataState(data);
        }
        fetchData();
    }, [refresh]);

    return (
        <div>
            <h1 className="centerTeam">Team Information - View and Create</h1>
            <h3 className="centerTeam">Infomaterials saved in the database:</h3>
            <InfoTable infos={dataState}/>
            <CreateInfomaterial/>
            <div className="centerTeam">
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('/teams')}>
                        <span className="infotext">Return to the overview page </span>
                        Back to the Overview Page
                    </button>
                )}/>
                <button className="teamButton infoteam" onClick={setRefresh}>
                    <span className="infotext">Refresh infomaterials to see the new infomaterial above</span>Refresh shown infomaterials
                </button>
            </div>
            <footer id="teamfooter">
                <p>Author: Michael Fortenbacher</p>
            </footer>
        </div>
    );
}

export default TeamInfo;