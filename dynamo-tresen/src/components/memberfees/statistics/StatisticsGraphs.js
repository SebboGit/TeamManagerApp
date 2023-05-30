import React from "react";

import FeeService from "../service/FeeService";
import { mockMembers, mockTeams } from './StatisticsMockService';

import StatusChart from './feeStatisticsGraphs/StatusChart';
import AmountChart from './feeStatisticsGraphs/AmountChart';
import MemberFluctuation from './memberStatisticsGraphs/MemberFluctuationChart';
import MembershipsChart from './memberStatisticsGraphs/MembershipsChart';
import MembershipAgeChart from './memberStatisticsGraphs/MembershipAgeChart';
import TeamProfitChart from './teamStatisticsGraphs/TeamProfitChart';

import '../css/statistics.css';

/**
 * Statistics page containing diagramms and graphs. Topics can be chosen 
 * through the prop of 'topic'. This component also requests the relevant 
 * data and sometimes begins to format it. 
 * 
 * @author Jonas Buse
 */
const StatisticsGraph = ({ startDate, endDate, topic }) => {
    const [fees, setFees] = React.useState([]);
    const [members, setMembers] = React.useState([]);
    const [teams, setTeams] = React.useState([]);

    React.useEffect(() => {
        // getting fees
        (async () => {
            const newFees = await FeeService.getFeesByDate(startDate, endDate);
            setFees(newFees);
        })()

        // getting members
        setMembers(mockMembers().filter(member => {
            member.entryDate = (member.entryDate >= startDate && member.entryDate <= endDate) ? member.entryDate : null;
            if (member.leavingDate !== null) {
                member.leavingDate = (member.leavingDate >= startDate && member.leavingDate <= endDate) ? member.leavingDate : null;
            }

            return member;
        }));

        // getting teams
        setTeams(mockTeams());

    }, [startDate, endDate]);


    /**
    * The graphs which will be displayed.
    */
    const graphs = {
        fees: [{
            name: "By Status - PieChart", desc: "Percentage and amount of fees with the given status.",
            component: <StatusChart fees={fees} />
        },
        {
            name: "By Amount - BarChart", desc: "The distribution of the amount of given fees. This represents how many fees with a specific amount there are. Presented in a Bar chart.",
            component: <AmountChart fees={fees} type="bar" />
        },
        {
            name: "By Amount - PieChart", desc: "The distribution of the amount of given fees. Presented in a Pie chart.",
            component: <AmountChart fees={fees} type="pie" />
        }],
        members: [{
            name: "Member Difference - BarChart", desc: "The difference of new members and members leaving per month. Sample data! Will change on reload or date change.",
            component: <MemberFluctuation members={members} startDate={startDate} endDate={endDate} type="bar" />
        },
        {
            name: "Members Total - AreaChart", desc: "The total number of members per month. Sample data! Will change on reload or date change.",
            component: <MemberFluctuation members={members} startDate={startDate} endDate={endDate} type="area" />
        },
        {
            name: "Memberships per Member - BarChart", desc: "The amount of members with the given amount of memberships. Sample data! Will change on reload or date change.",
            component: <MembershipsChart members={members} type="bar" />
        },
        {
            name: "Memberships per Member - Pie", desc: "The amount of members with the given amount of memberships. Sample data! Will change on reload or date change.",
            component: <MembershipsChart members={members} type="pie" />
        },
        {
            name: "Memberships Length - BarChart", desc: "The lengths of memberships of the members. Sample data! Will change on reload or date change.",
            component: <MembershipAgeChart members={members} type="bar" />
        }],
        events: [{
            name: "Coming soon...", desc: "But coming real soon, I promise!",
            component: <div>Not yet implemented</div>
        }],
        teams: [{
            name: "Earnings by Team - BarChart", desc: "Earnings from each team in €, which is calculated by dividing the fees which each member payed, evenly among the teams. Sample data! Will change on reload or date change.",
            component: <TeamProfitChart fees={fees} teams={teams} type="bar" />
        },
        {
            name: "Earnings by Team - PieChart", desc: "Earnings from each team, which is calculated by dividing the fees which each member payed, evenly among the teams. Sample data! Will change on reload or date change.",
            component: <TeamProfitChart fees={fees} teams={teams} type="pie" />
        }]
    };


    return (
        <div className="statistics-graphs">
            {graphs[topic].map((graph) => (
                <div className="statistics-card" key={graph.name}>
                    <div className="stat-card-head">
                        <h2>{graph.name}</h2>
                    </div>
                    <div className="stat-card-body">
                        <div className="stat-card-graph">
                            {graph.component}
                        </div>
                        <div className="stat-card-desc">
                            {graph.desc}
                        </div>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default StatisticsGraph;
