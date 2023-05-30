import React from "react";
import "./MemberAdministration.css";
import { useHistory } from "react-router-dom";

const ActiveMembers = () => {

    const history = useHistory();

        return (
            <div>
                <h1 className="center">Overview all active Members!</h1>
                <div className="center">
                    <table id="teamtable">
                        <thead>
                        <tr>
                            <th>Member-ID</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Birth</th>
                            <th>Description</th>
                            <th>Entry Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Jonas</td>
                            <td>Buse</td>
                            <td>04.08.1999</td>
                            <td>Best programmer</td>
                            <td>01.04.2021</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Felix</td>
                            <td>Pollok</td>
                            <td>15.04.1999</td>
                            <td>Best programmer</td>
                            <td>01.04.2021</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Jonas</td>
                            <td>Buse</td>
                            <td>04.08.1999</td>
                            <td>Best programmer</td>
                            <td>01.04.2021</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Jonas</td>
                            <td>Buse</td>
                            <td>04.08.1999</td>
                            <td>Best programmer</td>
                            <td>01.04.2021</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Jonas</td>
                            <td>Buse</td>
                            <td>04.08.1999</td>
                            <td>Best programmer</td>
                            <td>01.04.2021</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div>
                    <button className={"buttons"} onClick={() => history.push("/members")}>Go Back to Members Overview</button>
                </div>
            </div>

                );
}
export default ActiveMembers;