import React from "react";
import "../css/MemberAdministration.css";
import MemberService from "../services/MemberService";
import Popup from "../Popup";
import "../css/Popup.css";

class searchMember extends React.Component {

    state = {
        id: 1,
        firstname: '',
        lastname: '',
        description: '',
        birth: '',
        entryDate: '',
        leavingDate: '',
        address_id: '',
        address: {
            street: '',
            houseNumber: '',
            city: ''
        },
        showPopup: false
    }

    handleShowPopup = (showPopup) => {
        this.setState({
            showPopup: showPopup
        })
    }

    handleSubmit = async (id) => {
        id.preventDefault();
        const service = new MemberService();
        const response = await service.getOneMember(this.state.id);
        console.log(response);
        if (typeof response === 'string') {
            this.setState({
                message: response
            })
        } else {
            this.setState(response)

            this.setState({
                message: "Member with" + this.state.id
            })
        }
    }

        handleChange = (evt) => {
            this.setState({
                id: evt.target.value
            });
        }

    render() {
        return (
            <>
                <h1 className="Header"> Search Member</h1>
                    <form onSubmit={this.handleSubmit} className="form">
                        <p>
                            <label htmlFor="id" className="search">ID:</label>
                            <input type="number" id="id" onChange={this.handleChange}/>
                        </p>
                        <input type="submit" value="Search" className="buttons"
                               onClick={() => this.handleShowPopup(true)}/>
                        <button className="buttons"><a href={"/memberAdministration"}>Overview Member Administration</a>
                        </button>
                    </form>
                <Popup trigger={this.state.showPopup} setShowPopup={this.handleShowPopup}>
                    <h3>Member Information</h3>
                    <p className="popupMessage">{this.state.message}</p>
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
                        <tr>
                            <td>{this.state.id}</td>
                            <td>{this.state.firstname}</td>
                            <td>{this.state.lastname}</td>
                            <td>{this.state.birth}</td>
                            <td>{this.state.description}</td>
                            <td>{this.state.entryDate}</td>
                            <td>{this.state.leavingDate}</td>
                            <td>{this.state.address.street}</td>
                            <td>{this.state.address.houseNumber}</td>
                            <td>{this.state.address.city}</td>
                        </tr>
                        </thead>
                    </table>
                </Popup>
            </>
        )
    }

}

export default searchMember;