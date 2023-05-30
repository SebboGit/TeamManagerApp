import React from "react";
import '../css/teamManager.css';
import {Route} from "react-router-dom";
import TeamDisplay from "../elements/TeamDisplay";
import TeamService from "../service/TeamService";
import ChangeTeam from "./ChangeTeam";

/**
 * renders the different elements that should be displayed at the change - administration page for teams in the microservice "Mannschaften"
 * @returns {JSX.Element}
 *
 * @author Michael Fortenbacher
 */
const TeamAdministrationChange = () => {

    const [dataState, setDataState] = React.useState([]);
    const [refresh, setRefresh] = React.useState([]);

    React.useEffect(() => {
        const fetchData = async () => {
            let id = parseInt(document.getElementById("id").value);
            const data = await TeamService.getTeamById(id);
            console.log(data);
            if (data.id === id) {
                setDataState(data);
            }
        }
        fetchData();
    }, [refresh]);

    return (
        <div>
            <h1 className="centerTeam">Team Administration - Change</h1>
            <h3 className="centerTeam">Team that was last edited:</h3>
            <TeamDisplay team={dataState}/>
            <ChangeTeam/>
            <div className="centerTeam">
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('/teams')}>
                            <span
                                className="infotext">Return to the overview page </span>
                        Back to the Overview Page
                    </button>
                )}/>
                <button className="teamButton infoteam" onClick={setRefresh}>
                    <span className="infotext">Refresh shown team before or after edit. No change means team was not edited because of non-existing id or no changes</span>
                    Refresh shown team
                </button>
            </div>
            <footer id="teamfooter">
                <p>Author: Michael Fortenbacher</p>
            </footer>
        </div>
    );

}

export default TeamAdministrationChange;