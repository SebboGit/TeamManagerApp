import React from 'react';
import PropTypes from 'prop-types';

const MembersTable = ({members}) => {

    return (members.length === 0 &&
    <p className="center">Hallo und hier Text</p> ||
        <>
            <table id="membersTable">
        <thread>
            <tr>
                <th>ID</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Birth</th>
                <th>Description</th>
                <th>EntryDate</th>
                <th>LeavingDate</th>
                <th>Street</th>
                <th>HouseNumber</th>
                <th>City</th>
            </tr>
        </thread>
        <tbody>
        {members.map((member) =>
        <Member id={member.id} firstname={member.firstname} lastname={member.lastname} birth={member.birth}/>
        )}
        </tbody>
            </table>
        </>
    );
}

MembersTable.protoType = {
    members: PropTypes.array.isRequired
}

MembersTable.defaultProps = {
    team: []
}

export default MembersTable;