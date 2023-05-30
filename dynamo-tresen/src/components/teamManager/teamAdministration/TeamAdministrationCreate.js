import React from "react";
import '../css/teamManager.css';
import {Route} from "react-router-dom";
import CreateTeam from "./CreateTeam";
import TeamDisplay from "../elements/TeamDisplay";
import TeamService from "../service/TeamService";

/**
 * renders the different elements that should be displayed at the create - administration page for teams in the microservice "Mannschaften"
 * @returns {JSX.Element}
 *
 * @author Michael Fortenbacher
 */
const TeamAdministrationCreate = () => {

    const [dataState, setDataState] = React.useState([]);
    const [refresh, setRefresh] = React.useState([]);

    React.useEffect(() => {
        const fetchData = async () => {
            const id = await TeamService.getLatestTeam();
            const data = await TeamService.getTeamById(id);
            if (data.id === id) {
                setDataState(data);
            }
        }
        fetchData();
    }, [refresh]);

    return (
        <div>
            <h1 className="centerTeam">Team Administration - Create</h1>
            <h3 className="centerTeam">Newest Team:</h3>
            <TeamDisplay team={dataState}/>
            <CreateTeam/>
            <div className="centerTeam">
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('/teams')}>
                            <span
                                className="infotext">Return to the overview page </span>
                        Back to the Overview Page
                    </button>
                )}/>
                <button className="teamButton infoteam" onClick={setRefresh}>
                    <span className="infotext">Refresh to see the team created last</span>
                    Refresh newest team
                </button>
            </div>
            <footer id="teamfooter">
                <p>Author: Michael Fortenbacher</p>
            </footer>
        </div>
    );

}

export default TeamAdministrationCreate;