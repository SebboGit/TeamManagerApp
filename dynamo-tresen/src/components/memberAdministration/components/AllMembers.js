import React, {Component} from 'react';
import "../css/MemberAdministration.css";
import MemberService from "../services/MemberService";
import Popup from "../Popup";

class allMembers extends React.Component {

    state = {
        showPopup: false
    }

    handleShowPopup = (showPopup) => {
        this.setState({
            showPopup: showPopup
        })
    }

    handleSubmit = async (list) => {
        list.preventDefault();
        const service = new MemberService();
        const response = await service.getAllMembers()

    }

    render() {
        return (
            <div className="FeeOverview">
                <h1>All Members Overview</h1>
                <table id="table">
                    <thead>
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
                    </thead>
                </table>
            </div>
        )
    }
}
export default allMembers;