import React from 'react';
import { BarChart, Bar, Brush, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ReferenceLine } from 'recharts';

/**
 * Chart to display the length of the memberships of all the members and their occurances.
 * 
 * @author Jonas Buse
 */
const MembershipAgeChart = ({ members, type }) => {
    const [data, setData] = React.useState([]);

    React.useEffect(() => {
        const dict = {};

        // save the length of each members membership to an object with the length as key and the value the occurances.
        members.filter(memb => memb.entryDate != null).forEach((memb) => {
            const beginDate = new Date(memb.entryDate);
            const endDate = (memb.leavingDate != null) ? new Date(memb.leavingDate) : new Date();
            let length = Math.round((endDate - beginDate) / (1000 * 60 * 60 * 24 * 30));
            length = length >= 0 ? length : 0;

            (dict[length]) ? dict[length] += 1 : dict[length] = 1;
        });

        // get the largest key
        const maxLength = Object.keys(dict).reduce((max, key) => {
            return (max === undefined || max < key) ? key : max
        }, 0);

        // pad the rest of the object with key of missing lengths and values of 0
        [...Array(parseInt(maxLength)).keys()].forEach((val) => {
            if (!dict[val])
                dict[val] = 0;
        });

        setData(Object.keys(dict).map(key => { return { months: key, Occurances: dict[key] } }));


    }, [members]);

    return (members.length === 0) ? <div style={{ margin: '40px', color: 'darkorange' }}>No members present...</div> :
        (type === "bar") ? (
            <BarChart
                width={550}
                height={300}
                data={data}
                margin={{
                    top: 5,
                    right: 30,
                    left: 0,
                    bottom: 50,
                }}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="months" />
                <YAxis />
                <Tooltip labelFormatter={(label) => `Membership Length: ${label}`} />
                <ReferenceLine y={0} stroke="#000" />
                <Brush dataKey="months" height={35} stroke="#005da8" />
                <Legend wrapperStyle={{ bottom: 20, left: 15 }} />
                <Bar dataKey="Occurances" fill="#005da8" />
            </BarChart>

        ) : (
            <>
            </>
        );
};

export default MembershipAgeChart;