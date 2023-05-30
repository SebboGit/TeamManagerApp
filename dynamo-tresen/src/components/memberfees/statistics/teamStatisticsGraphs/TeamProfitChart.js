import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, Pie, PieChart, Cell } from 'recharts';

import { mockSingleMember } from '../StatisticsMockService';

import { renderCustomizedLabel } from '../feeStatisticsGraphs/StatusChart';

/**
 * A PieChart that represents profit of each team.
 * 
 * @author Jonas Buse
 */
const TeamProfitChart = ({ teams, fees, type }) => {
    const [data, setData] = React.useState([]);
    const COLORS = ['#3C9D4E', '#7031AC', '#C94D6D', '#E4BF58', '#4174C9'];

    React.useEffect(() => {
        const teamArr = [];

        // for each team
        teams.forEach(team => {
            // calculate the profit for each member of said team
            const earnings = team.members.reduce((acc, member) => {
                const mockedMember = mockSingleMember(member);
                // using the fees in the timeframe from the relevant member
                const feeArr = fees.filter(fee => fee.memberId === mockedMember.id)

                // if he has any fees, get those amounts (divided by the number of memberships)
                if (feeArr.length > 0) {
                    acc += (feeArr.reduce((acc2, fee) => {
                        acc2 += fee.amount / mockedMember.memberships;
                        return acc2;
                    }, 0));
                }

                return acc;
            }, 0);
            teamArr.push({
                team: team.name, Earnings: (Math.round(earnings * 100) / 100) // round to two digits after the decimal point
            });
        });

        setData(teamArr);
    }, [fees, teams]);


    return (fees.length === 0) ? <div style={{ margin: '40px', color: 'darkorange' }}>No data present...</div> : (
        (type === "pie") ? (
            <PieChart width={350} height={300} >
                <Legend wrapperStyle={{ top: -35 }} />
                <Pie
                    data={data}
                    dataKey="Earnings"
                    nameKey="team"
                    fill="#8884d8"
                    legendType="circle"
                    label={renderCustomizedLabel}
                    labelLine={false}
                    animationDuration={500}
                >
                    {
                        data.map((_, index) => (
                            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                        ))
                    };
                </Pie>
                <Tooltip />
            </PieChart>
        ) : (
            <BarChart
                width={500}
                height={300}
                data={data}
                margin={{
                    top: 5,
                    right: 30,
                    left: 0,
                    bottom: 50,
                }}
            >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="team" />
                <YAxis />
                <Tooltip />
                <Legend wrapperStyle={{ bottom: 20, left: 15 }} />
                <Bar dataKey="Earnings" fill="#005da8" />
            </BarChart>
        )
    );
};

export default TeamProfitChart;