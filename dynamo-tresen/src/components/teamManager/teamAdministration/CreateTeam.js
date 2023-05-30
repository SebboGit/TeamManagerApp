/**
 * element responsible for the creation of a new team
 */
import TeamService from "../service/TeamService";
import React from "react";
import '../css/teamManager.css';

const CreateTeam = () => {
    const [state, setState] = React.useState();

    const createAndPostTeam = async (evt) => {
        evt.preventDefault();
        await TeamService.createTeam(state);
    }

    const saveInput = (evt) => {
        setState(prevState => ({
            ...prevState,
            [evt.target.id]: evt.target.value
        }));
    };

    return (
        <div className="centerTeam">
            <form >
                <label>Team name:
                    <input title="Enter the name for the new sports team " className="centerTeam" id="name" onChange={saveInput} placeholder="e.g. FC Bayern"></input>
                </label>
                <label>MemberIds:
                    <input title="Enter member-ids seperated by ',' like '33,2,102'" className="centerTeam" id="memberIds" onChange={saveInput} placeholder="5,3,4,1"></input>
                </label>
                <label>InfomaterialsIds:
                    <input title="Enter infomaterial-ids seperated by ',' like '4,11,77' " className="centerTeam" id="infomaterialIds" onChange={saveInput} placeholder="1,2,3,4"></input>
                </label>
                <button className="teamButton infoteam" onClick={createAndPostTeam}><span
                    className="infotext">Create a new team with the entered data </span>Create team
                </button>
            </form>
        </div>
    );
}

export default CreateTeam;