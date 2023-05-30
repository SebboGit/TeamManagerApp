/**
 * element responsible for the creation of a new infomaterial
 */
import React from "react";
import '../css/teamManager.css';
import InfomaterialService from "../service/InfomaterialService";

const CreateInfomaterial = () => {
    const [state, setState] = React.useState({name: "", description: "", url: "", pdf: null});

    const createAndPostInfomaterial = async (evt) => {
        evt.preventDefault();
        await InfomaterialService.createInfomaterial(state)
    }

    const saveInput = (evt) => {
        setState(prevState => ({
            ...prevState,
            [evt.target.id]: evt.target.value
        }));
    };

    const handlePdfChange = (evt) => {
        state.pdf = evt.target.files[0]
    }

    return (
        <div className="centerTeam">
            <form>
                <label>Infomaterial name:
                    <input title="Name of the infomaterial" className="centerTeam" id="name" onChange={saveInput}
                           placeholder="Name of the infomaterial"></input>
                </label>
                <label>description:
                    <input title="Describe the infomaterial here" className="centerTeam" id="description"
                           onChange={saveInput}
                           placeholder="description text"></input>
                </label>
                <label>url:
                    <input title="Enter url to a Website like 'https://gitlab.lrz.de/' here" className="centerTeam" id="url"
                           onChange={saveInput}
                           placeholder="https:/www.google.com"></input>
                </label>
                <label>pdf:
                    <input type="file" className="centerTeam" id="pdf" accept="application/pdf"
                           onChange={handlePdfChange}></input>
                </label>
                <button className="teamButton infoteam" onClick={createAndPostInfomaterial}><span
                    className="infotext">Create a new infomaterial with the entered data</span>Create Infomaterial
                </button>
            </form>
        </div>
    );
}

export default CreateInfomaterial;