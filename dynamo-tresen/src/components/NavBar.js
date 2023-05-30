import React from 'react'
import { useHistory } from 'react-router-dom';
import './css/nav.css'

/**
 * Navigation Bar which is placed at the top of the screen for every site.
 * Redirects work by using the push method of the usHistory hook.
 * New components should be added in here for easy navigation. 
 * 
 * @author Jonas Buse
 */
const Nav = () => {

    const history = useHistory();

    const redirect = (dest) => {
        history.push(dest);
    }

    return (
        <nav>
            <ul>
                <li><button onClick={() => redirect("/")}>Home</button></li>
                <li style={{ float: 'right' }}><button onClick={() => redirect("/calendar")}>Calendar</button></li>
                <li style={{ float: 'right' }}><button onClick={() => redirect("/statistics")}>Statistics</button></li>
                <li style={{ float: 'right' }}><button onClick={() => redirect("/fees")}>Fees</button></li>
                <li style={{ float: 'right' }}><button onClick={() => redirect("/memberAdministration")}>Members</button></li>
                <li style={{ float: 'right' }}><button onClick={() => redirect("/pictures")}>Pictures</button></li>
                <li style={{ float: 'right' }}><button onClick={() => redirect("/teams")}>Teams</button></li>
            </ul>
        </nav>
    )
};


export default Nav;