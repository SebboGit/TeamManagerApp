import React from "react";
import "./css/MemberAdministration.css";

class MemberAdministration extends React.Component {

    render() {
        return (
            <div>
                <h1 className="Header">
                    Member Administration
                </h1>
                <h2 className="description">
                Here you can manage all Members and create new ones
                </h2>

                <div className="buttons">
                    <button className="buttons">
                        <a href={"/AllMembers"}>All Members</a>
                    </button>
                    <button className="buttons">
                        <a href={"/newMember"}>New Member</a>
                    </button>
                    <button className="buttons">
                        <a href={"/searchMember"}>Search Member</a>
                    </button>
                    <button className="buttons">
                        <a href={"/memberAdministration"}>Overview Member Administration</a>
                    </button>
                </div>
            </div>
        )
    }

}

    export default MemberAdministration;
