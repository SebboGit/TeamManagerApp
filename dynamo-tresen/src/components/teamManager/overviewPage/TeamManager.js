import React from "react";
import '../css/teamManager.css';
import TeamTable from "./TeamTable"
import TeamService from "../service/TeamService";
import {Route} from "react-router-dom";

/**
 * renders the different elements that should be displayed at the startpage of the microservice "Mannschaften"
 * @returns {JSX.Element}
 *
 * @author Michael Fortenbacher
 */
const TeamManager = () => {

    const [dataState, setDataState] = React.useState([]);

    React.useEffect(() => {
        const fetchData = async () => {
            const data = await TeamService.getAllTeams();
            setDataState(data);
        }
        fetchData();
    }, [setDataState]);

    return (
        <div>
            <h1 className="centerTeam">Overview Page of the Teammanager</h1>
            <TeamTable teams={dataState}/>
            <div className="centerTeam">
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('teams/administration/create')}>
                        <span className="infotext">Your Team is not shown here? Create a new Team from scratch</span>
                        Create new team
                    </button>
                )}/>
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('teams/administration/change')}>
                        <span
                            className="infotext">Leads to a menu where you can manage/edit teams already saved in the database</span>
                        Manage teams
                    </button>
                )}/>
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('teams/information/viewandcreate')}>
                        <span className="infotext">Look at Information already stored or create a new one</span>
                        Create and View all Infomaterial
                    </button>
                )}/>
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('teams/information/edit')}>
                        <span className="infotext">Edit Infomaterials already stored in the database</span>
                        Edit Infomaterial
                    </button>
                )}/>
            </div>
            <footer id="teamfooter">
                <p>Author: Michael Fortenbacher</p>
            </footer>
        </div>
    );
}

export default TeamManager;