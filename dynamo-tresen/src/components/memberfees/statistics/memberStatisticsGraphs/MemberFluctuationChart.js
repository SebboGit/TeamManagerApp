import React from 'react';
import { AreaChart, Area, BarChart, Bar, Brush, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ReferenceLine, Cell } from 'recharts';

/**
 * Charts used to display the fluctuation of members based on months.
 * It calculates the difference between the number of new members and 
 * the number of members leaving.
 * 
 * @author Jonas Buse
 */
const MemberFluctuation = ({ members, startDate, endDate, type }) => {
    const [dataDifference, setDataDifference] = React.useState([]);
    const [dataAbsolute, setDataAbsolute] = React.useState([]);
    const colors = ["#2ab600", "#ce0000"]; // slightly darker, green and red

    React.useEffect(() => {
        let startDateObject = new Date(startDate);
        const endDateObject = new Date(endDate)
        const dictDiff = {};

        // create an object with all months between the two dates as keys with the value 0
        while (startDateObject.setDate(10) <= endDateObject.setDate(10)) {
            dictDiff[startDateObject.toISOString().substring(0, 7)] = 0;
            startDateObject.setMonth(startDateObject.getMonth() + 1)
        }

        // for each member, increase or decrease the corresponding dicts month value
        members.forEach(member => {
            if (member.entryDate != null)
                dictDiff[member.entryDate.substring(0, 7)] += 1;
            if (member.leavingDate != null)
                dictDiff[member.leavingDate.substring(0, 7)] -= 1;

        });
        // data for the difference chart
        const tempDataDifference = Object.keys(dictDiff).map(key => { return { month: key, Difference: dictDiff[key] } });
        setDataDifference(tempDataDifference);

        // data from the absolute chart, is just aggregated from previously
        var acc = 0;
        const tempDataAbsolute = tempDataDifference.map(val => {
            acc += val.Difference;
            val.Amount = acc;
            return val;
        });
        setDataAbsolute(tempDataAbsolute);

    }, [members, startDate, endDate]);

    return (members.length === 0) ? <div style={{ margin: '40px', color: 'darkorange' }}>No members present...</div> :
        (type === "bar") ? (
            <BarChart
                width={550}
                height={300}
                data={dataDifference}
                margin={{
                    top: 5,
                    right: 30,
                    left: 0,
                    bottom: 50,
                }}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="month" />
                <YAxis />
                <Tooltip labelFormatter={(label) => `Month ${label}`} />
                <ReferenceLine y={0} stroke="#000" />
                <Brush dataKey="month" height={35} stroke="#005da8" />
                <Legend wrapperStyle={{ bottom: 20, left: 15 }} />
                <Bar dataKey="Difference" fill="#005da8" >
                    {
                        dataDifference.map((entry, index) => (
                            <Cell key={`cell-${index}`} fill={colors[(entry.Difference < 0) ? 1 : 0]} stroke="#005da8" />
                        ))}
                </Bar>
            </BarChart>

        ) : (
            <AreaChart
                width={550}
                height={300}
                data={dataAbsolute}
                margin={{
                    top: 5,
                    right: 30,
                    left: 0,
                    bottom: 50,
                }}>
                <defs>
                    <linearGradient id="stat-members-gradient" x1="0" y1="0" x2="0" y2="1">
                        <stop offset="10%" stopColor="#005da8" stopOpacity={1} />
                        <stop offset="99%" stopColor="#005da8" stopOpacity={0} />
                    </linearGradient>
                </defs>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="month" />
                <YAxis />
                <Tooltip labelFormatter={(label) => `Month ${label}`} />
                <Legend wrapperStyle={{ bottom: 20, left: 15 }} iconType="rect"
                    formatter={(val, entry) => <span style={{ color: "#005da8" }}>Amount of Members</span>}
                />
                <Area type="monotone" dataKey="Amount" stroke="#012066" fillOpacity={1} fill="url(#stat-members-gradient)" />
            </AreaChart>
        );
};

export default MemberFluctuation;