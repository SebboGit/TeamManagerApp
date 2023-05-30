import React from 'react';
import PropTypes from 'prop-types';

import '../css/teamTable.css';
import {Team} from "../elements/Team";

/**
 * Component that lists different sports teams in a table
 *
 * @author Michael Fortenbacher
 */
const TeamTable = ({teams}) => {

    return (teams.length === 0 &&
        <p className="centerTeam">No team saved in the database. Create a new team to see it here!</p>) || (
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
                {teams.map((team) =>
                    <Team id={team.id} name={team.name} memberCount={team.memberCount} memberIds={team.memberIds}
                          infomaterialCount={team.infomaterialCount} infomaterialIds={team.infomaterialIds}/>
                )}
                </tbody>
            </table>
        </>
    );
}

TeamTable.propTypes =
    {
        teams: PropTypes.array.isRequired
    }

TeamTable.defaultProps =
    {
        teams: []
    }
export default TeamTable;