import React from 'react';

import '../css/teamTable.css';

/**
 * Component that lists a single Team in a table for better looks
 *
 * @author Michael Fortenbacher
 */
const TeamDisplay = ({team}) => {
    return (team.length === 0 && <p className="centerTeam">No Team with the matching Id in "Team-Id" was found!</p>) || (
        <>
            <table id="teamtable">
                <thead>
                <tr>
                    <th>Team-Id</th>
                    <th>Teamname</th>
                    <th>Amount of Members</th>
                    <th>MemberIds</th>
                    <th>Amount of Infomaterials</th>
                    <th>Infomaterial-Ids</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>{team.id}</td>
                    <td>{team.name}</td>
                    <td>{team.memberCount}</td>
                    <td>{team.memberIds}</td>
                    <td>{team.infomaterialCount}</td>
                    <td>{team.infomaterialIds}</td>
                </tr>
                </tbody>
            </table>
        </>
    );
}

export default TeamDisplay;