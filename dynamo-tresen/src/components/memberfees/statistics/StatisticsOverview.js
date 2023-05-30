import React from 'react';

import StatisticsGraph from './StatisticsGraphs';
import StatisticsHome from './StatisticsHome';

import '../css/statistics.css';

/**
 * Statistics page containing the sidebar from which a topic can be selected
 * and a date picker to select a range of dates.
 * 
 * @author Jonas Buse
 */
export const StatisticsOverview = () => {

    const today = new Date();

    const [openSite, setOpenSite] = React.useState("home");
    const [startDate, setStartDate] = React.useState(`${today.getFullYear()}-01-01`);
    const [endDate, setEndDate] = React.useState(today.toISOString().split('T')[0]);
    const [dateDifference, setDateDifference] = React.useState((new Date(endDate).getTime() - new Date(startDate).getTime()) / (1000 * 60 * 60 * 24));


    React.useEffect(() => {
        setDateDifference((new Date(endDate).getTime() - new Date(startDate).getTime()) / (1000 * 60 * 60 * 24));
    }, [startDate, endDate]);

    return (
        <div className="statistics-site">
            <div className="statistics-sidebar">
                <h1 className="statistics-header">Statistics</h1>
                <ul>
                    <li className="statistics-listItemA">
                        <div onClick={() => setOpenSite("home")} style={(openSite === "home") ? { fontWeight: "700" } : {}}>
                            <span className="icon sidebar-icon">home</span>Home</div>
                    </li>
                    <li className="statistics-listItemT" >Topics</li>
                    <li className="statistics-listItemA">
                        <div onClick={() => setOpenSite("teams")} style={(openSite === "teams") ? { fontWeight: "700" } : {}}>
                            <span className="icon sidebar-icon">groups</span>Teams</div>
                    </li>
                    <li className="statistics-listItemA">
                        <div onClick={() => setOpenSite("members")} style={(openSite === "members") ? { fontWeight: "700" } : {}}>
                            <span className="icon sidebar-icon">transfer_within_a_station</span>Members</div>
                    </li>
                    <li className="statistics-listItemA">
                        <div onClick={() => setOpenSite("fees")} style={(openSite === "fees") ? { fontWeight: "700" } : {}}>
                            <span className="icon sidebar-icon">price_change</span>Fees</div>
                    </li>
                    <li className="statistics-listItemA">
                        <div onClick={() => setOpenSite("events")} style={(openSite === "events") ? { fontWeight: "700" } : {}}>
                            <span className="icon sidebar-icon">event</span>Events</div>
                    </li>
                </ul>
            </div>

            <div className="statistics-body-wrapper">
                <div className="statistics-period-picker" >
                    <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
                    <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
                    <div className={"statistics-date-diff " + ((dateDifference > 0) ? "" : "stat-date-error")} >
                        <span className="icon statistics-time">schedule</span>
                        {dateDifference + " Tag" + (dateDifference === 1 ? "" : "e")}
                    </div>
                </div>

                {
                    (openSite === "home" && <StatisticsHome />)
                    || <StatisticsGraph startDate={startDate} endDate={endDate} topic={openSite} />


                }
            </div>
        </div>
    );
};