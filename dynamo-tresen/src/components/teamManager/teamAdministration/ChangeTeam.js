/**
 * element responsible for changing attributes of a new team
 */
import TeamService from "../service/TeamService";
import React from "react";
import '../css/teamManager.css';

const ChangeTeam = () => {
    const [state, setState] = React.useState();

    const createAndPostTeam = async (evt) => {
        evt.preventDefault();
        await TeamService.changeTeam(state);
    }

    const saveInput = (evt) => {
        setState(prevState => ({
            ...prevState,
            [evt.target.id]: evt.target.value
        }));
    };

    return (
        <div className="centerTeam">
            <form>
                <label>Team-Id:
                    <input title="Id of the team that should be edited" type="number" min="1" className="centerTeam" id="id"
                           onChange={saveInput} placeholder="e.g. '15'"></input>
                </label>
                <label>New team name:
                    <input title="New name for the team" className="centerTeam" id="name" onChange={saveInput}
                           placeholder="e.g. 'FC Köln'"></input>
                </label>
                <label>Set new memberIds:
                    <input title="New member-ids seperated by ',' like '11,13,7'" className="centerTeam" id="memberIds"
                           onChange={saveInput} placeholder="e.g. '2,99,33'"></input>
                </label>
                <label>Set new infomaterialIds:
                    <input title="New infomaterial-ids seperated by ',' like '69,420,666'" className="centerTeam"
                           id="infomaterialIds" onChange={saveInput} placeholder="e.g. '44,55,3'"></input>
                </label>
                <button className="teamButton infoteam" onClick={createAndPostTeam}><span
                    className="infotext">Edit the shown team with the entered data (All fields must be filled!)</span>Edit
                    team
                </button>
            </form>
        </div>
    );
}

export default ChangeTeam;