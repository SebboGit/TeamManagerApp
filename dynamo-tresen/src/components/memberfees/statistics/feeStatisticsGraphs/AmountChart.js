import React from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, Pie, PieChart, Cell } from 'recharts';
import Gradient from "javascript-color-gradient";

import { renderCustomizedLabel } from './StatusChart';

/**
 * Barchart or Piechar representing the occurances of the fee amounts.
 * 
 * @author Jonas Buse
 */
const AmountChart = ({ fees, type }) => {
    const [data, setData] = React.useState([]);
    const [colors, setColors] = React.useState([]);

    React.useEffect(() => {
        const dict = {};
        fees.forEach(fee => {
            (dict.hasOwnProperty(fee.amount)) ? dict[fee.amount] += 1 : dict[fee.amount] = 1;
        });
        setData(Object.keys(dict).map(key => { return { name: key + "€", Occurances: dict[key] } }));
    }, [fees]);

    React.useEffect(() => {
        const COLORS = ['#ff0000', '#26ff00'];
        const colorGradient = new Gradient();
        colorGradient.setGradient(COLORS[0], COLORS[1]);
        colorGradient.setMidpoint(data.length);
        setColors(colorGradient.getArray());
    }, [data]);




    return (fees.length === 0) ? <div style={{ margin: '40px', color: 'darkorange' }}>No fees present...</div> :
        (type === "bar") ? (
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
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Legend wrapperStyle={{ bottom: 20, left: 15 }} />
                <Bar dataKey="Occurances" fill="#005da8" />
            </BarChart>)
            :
            (
                <PieChart width={300} height={300} >
                    <Legend wrapperStyle={{ top: -30 }} />
                    <Pie
                        data={data}
                        dataKey="Occurances"
                        nameKey="name"
                        fill="#8884d8"
                        legendType="circle"
                        label={(data.length < 5) ? renderCustomizedLabel : false}
                        labelLine={false}
                        animationDuration={500}
                    >
                        {data.map((_, index) => (
                            <Cell key={`cell-${index}`} fill={colors[index % colors.length]} />
                        ))};
                    </Pie>
                    <Tooltip />
                </PieChart>
            )
};

export default AmountChart;