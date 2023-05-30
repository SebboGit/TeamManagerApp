import React from "react";
import '../css/teamTable.css'
import {Infomaterial} from "../elements/Infomaterial";

/**
 *
 * Component that lists different infomaterials in a table
 *
 * @author Michael Fortenbacher
 */
const InfoTable = ({infos}) => {

    return (infos.length === 0 &&
        <p className="centerTeam">No infomaterials saved in the database. Create a new infomaterial to see it here!</p> || (
            <>
                <table id="teamtable">
                    <thead>
                    <tr>
                        <th>Infomaterial-Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Url</th>
                        <th>Pdf</th>
                    </tr>
                    </thead>
                    <tbody>
                    {infos.map((info) => (
                        <Infomaterial id={info.id} name={info.name} description={info.description} url={info.url}
                                      pdf={info.pdf}/>
                    ))}
                    </tbody>
                </table>
            </>
        )
    );
}
export default InfoTable;