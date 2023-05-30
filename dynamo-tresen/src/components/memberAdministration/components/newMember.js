import React from "react";
import MemberService from "../services/MemberService";
import AddressService from "../services/AddressService";
import "../css/MemberAdministration.css";
import "../css/Popup.css";
import Popup from "../Popup";

const NewMember = () => {
    const [state, setState,] = React.useState();

    const createAndPostMember = async (evt) => {
        evt.preventDefault();
        console.log('response = string')
        await MemberService.newMember(state);
        await AddressService.newAddress(state);
        console.log("hallo");
    }

    const saveMember = (evt) => {
        setState(prevState => ({
            ...prevState,
                [evt.target.id]: evt.target.value
        }));
        console.log('save')
    };

        return (
            <div className="center">
                <form className="form">
                    <p>
                    <label>Firstname:
                        <input className="center" id="firstname" onChange={saveMember}/>
                    </label>
                    </p>
                    <p>
                    <label>Lastname:
                        <input className="center" id="lastname" onChange={saveMember}></input>
                    </label>
                    </p>
                    <p>
                    <label>Description:
                        <input className="center" id="description" onChange={saveMember}></input>
                    </label>
                    </p>
                    <p>
                        <label>Birth:
                            <input type="date" className="center" id="birth" onChange={saveMember}></input>
                        </label>
                    </p>
                    <p>
                        <label>Entry Date:
                            <input type="date" className="center" id="entryDate" onChange={saveMember}></input>
                        </label>
                    </p>
                    <p>
                        <label>Leaving Date:
                            <input type="date" className="center" id="leavingDate" onChange={saveMember}></input>
                        </label>
                    </p>
                    <p>
                        <label>Street / Housenumber:
                            <input type="string" className="center" id="street" onChange={saveMember}></input>
                            <label>
                                <input type="integer" className="center" id="houseNumber" onChange={saveMember}></input>
                            </label>
                        </label>
                    </p>
                    <p>
                        <label>City:
                            <input type="String" className="center" id="city" onChange={saveMember}></input>
                        </label>
                    </p>
                    <button className="buttons" onClick={createAndPostMember}>
                        <span className="buttons">Save Member</span>
                    </button>
                    <button className="buttons"><a href={"/memberAdministration"}>Overview Member Administration</a>
                    </button>
                </form>
            </div>
        );
}

export default NewMember;