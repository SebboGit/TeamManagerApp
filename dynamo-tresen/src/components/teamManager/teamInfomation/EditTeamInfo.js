import React from "react";
import '../css/teamManager.css';
import {Route} from "react-router-dom";
import InfomaterialService from "../service/InfomaterialService";
import EditInfomaterial from "./EditInfomaterial";
import InfomaterialDisplay from "../elements/InfomaterialDisplay";

const EditTeamInfo = () => {

    const [dataState, setDataState] = React.useState([]);
    const [refresh, setRefresh] = React.useState([]);

    React.useEffect(() => {
        const fetchData = async () => {
            let id = parseInt(document.getElementById("id").value);
            const data = await InfomaterialService.getInfomaterialById(id);
            if (data.id === id) {
                setDataState(data);
            }
        }
        fetchData();
    }, [refresh]);

    return (
        <div>
            <h1 className="centerTeam">Team Information - Edit</h1>
            <h3 className="centerTeam">Infomaterial that was last edited:</h3>
            <InfomaterialDisplay info={dataState}/>
            <EditInfomaterial/>
            <div className="centerTeam">
                <Route render={({history}) => (
                    <button className="teamButton infoteam" onClick={() => history.push('/teams')}>
                            <span
                                className="infotext">Return to the overview page </span>
                        Back to the Overview Page
                    </button>
                )}/>
                <button className="teamButton infoteam" onClick={setRefresh}>
                    <span className="infotext">Refresh shown infomaterials before or after edit. No change means infomaterial was not edited because of non-existing id or no changes</span>Refresh
                    shown infomaterials
                </button>
            </div>
            <footer id="teamfooter">
                <p>Author: Michael Fortenbacher</p>
            </footer>
        </div>
    );
}

export default EditTeamInfo;