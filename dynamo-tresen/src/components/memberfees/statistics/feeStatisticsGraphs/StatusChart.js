import React from 'react';
import { PieChart, Pie, Legend, Tooltip, Cell } from 'recharts';

/**
 * A PieChart that represents the distribution of fees according to their status.
 * 
 * @author Jonas Buse
 */
const StatusChart = ({ fees }) => {
    const [data, setData] = React.useState([{ name: "Confirmed", value: 0 }, { name: "Pending", value: 0 }]);
    const COLORS = ['#ff9f49', '#00a01b'];

    React.useEffect(() => {
        setData([
            { name: "Pending", value: fees.reduce((acc, f) => { return (f.status === 'pending' ? acc += 1 : acc) }, 0) },
            { name: "Confirmed", value: fees.reduce((acc, f) => { return (f.status === 'confirmed' ? acc += 1 : acc) }, 0) }]);
    }, [fees]);


    return (fees.length === 0) ? <div style={{ margin: '40px', color: 'darkorange' }}>No fees present...</div> : (
        <>
            <PieChart width={270} height={270} >
                <Legend wrapperStyle={{ top: -20 }} />
                <Pie
                    data={data}
                    dataKey="value"
                    nameKey="name"
                    fill="#8884d8"
                    legendType="circle"
                    label={renderCustomizedLabel}
                    labelLine={false}
                    animationDuration={500}
                >
                    {data.map((_, index) => (
                        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))};
                </Pie>
                <Tooltip />
            </PieChart>

        </>
    );
};

/**
 * Renders the label to be inside the diagram for a pie chart
 */
export const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, percent }) => {
    const RADIAN = Math.PI / 180;
    const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
    const x = cx + radius * Math.cos(-midAngle * RADIAN);
    const y = cy + radius * Math.sin(-midAngle * RADIAN);

    return (
        <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
            {`${(percent * 100).toFixed(0)}%`}
        </text>
    );
};

export default StatusChart;