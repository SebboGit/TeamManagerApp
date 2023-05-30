import React, {Component} from 'react';
import './css/overview.css';


class Menu extends Component {

    render() {
        return (
            <div>
                <h1 className="h1">Overview</h1>
                <h3>Here you find all functions of the management system!</h3>
                <div className="buttonfield">
                    <button className="buttonMS_1">
                        <a href={"/memberAdministration"}>Member Administration</a>
                    </button>
                    <button className="buttonMS_2">
                        <a href={"/teamManager"}>Teams</a>
                    </button>
                    <button className="buttonMS_3">
                        <a href={"/memberfees"}>Member Fees</a>
                    </button>
                    <button className="buttonMS_4">
                        <a href={"/calendar"}>Event Calendar</a>
                    </button>
                    <button className="buttonMS_5">
                        <a href={"/eventPictures"}>Event Pictures</a>
                    </button>
                </div>
            </div>
        )
    }
}

export default Menu;
