import React from 'react';
import PropTypes from 'prop-types';

import SearchBar from './SearchBar';
import { FeeLong } from './FeeLong';
import FeeShort from './FeeShort';

import '../css/feeTable.css'

/**
 * React component that displays a Table of fees with sorting functionality.
 * 
 * @author Jonas Buse
 */
const FeeTable = ({ fees, setFlashState, showFeeLong }) => {
    const [sortState, setSortState] = React.useState("id");
    const [searchState, setSearchState] = React.useState("");

    /**
     * Custom sorting function for Strings and Integers.
     * 
     * @param {String} property the sorting attribute. prepend a "-" to reverse
     * @returns a comparison function to be used in a .sort(x)
     */
    const dynamicSort = (property) => {
        var sortOrder = 1;
        if (property[0] === "-") {
            sortOrder = -1;
            property = property.substr(1);
        }
        return function (a, b) {
            var result = (a[property] < b[property]) ? -1 : (a[property] > b[property]) ? 1 : 0;
            return result * sortOrder;
        }
    }

    /**
     * Switches the sort state based on the column clicked.
     * 
     * @param {String} column the name of the column
     */
    const changeSort = (column) => {
        if (sortState.includes(column)) {
            setSortState((sortState.charAt(0) === "-") ? column : "-" + column);
            return;
        }
        setSortState(column);
    };

    /**
     * Returns an icon that indicates the sorting, if applicable.
     * 
     * @param {String} column name of the column
     * @return a String cointaining the name of the icon
     */
    const getIcon = (column) => {
        if (sortState.includes(column)) {
            return (sortState.charAt(0) !== "-") ? "arrow_drop_up" : "arrow_drop_down";
        }
        return;
    };

    /**
     * This is designed to be used in an Array.filter() method.
     * It takes the searchState, wich is just a string and converts it to 
     * an Object with key value pairs representing the different attributes.
     * 
     * The function then returns the sorting method if any of the attributes were asked
     * for.
     * 
     */
    const applySearchFilter = (fee) => {
        const dict = { id: null, memberId: null, date: null, amount: null, status: null };

        // Makes the string lowercase, removes all whitespace, and splits it on commas.
        // Then it loops over each string to check against regex.
        searchState.toLowerCase().replace(/\s+/g, '').split(',').forEach(c => {
            if (/^id:\d+/g.test(c))
                dict.id = c.split(':')[1];
            else if (/member(?:.*id)?:\d+/g.test(c))
                dict.memberId = c.split(':')[1];
            else if (/date:\d{4}-\d{2}-\d{2}/g.test(c))
                dict.date = c.split(':')[1];
            else if (/amount:\d+/g.test(c))
                dict.amount = c.split(':')[1];
            else if (/status:(?:pending|confirmed)/g.test(c))
                dict.status = c.split(':')[1];
        });

        // If the dict entry is null, compare against effectively nothing.
        return (
            ((dict.id) ? fee.id === parseInt(dict.id) : fee.id !== undefined) &&
            ((dict.memberId) ? fee.memberId === parseInt(dict.memberId) : fee.memberId !== null) &&
            ((dict.date) ? fee.date === dict.date : fee.date !== null) &&
            ((dict.amount) ? fee.amount === parseInt(dict.amount) : fee.amount !== null) &&
            ((dict.status) ? fee.status === dict.status : fee.status !== null)
        );
    };

    return (fees.length === 0 && <p className="not-found">No fees available. Create a new one to start!</p>) || (
        <>
            <SearchBar setSearchState={setSearchState} searchState={searchState} />
            <div className="FeeTable">
                <div className="legend">
                    <div onClick={() => changeSort("id")} >
                        ID <span className="icon">{getIcon("id")}</span>
                    </div>
                    <div onClick={() => changeSort("memberId")} >
                        Member ID <span className="icon">{getIcon("memberId")}</span>
                    </div>
                    <div onClick={() => changeSort("date")} >
                        Date <span className="icon">{getIcon("date")}</span>
                    </div>
                    <div onClick={() => changeSort("amount")} >
                        Amount <span className="icon">{getIcon("amount")}</span>
                    </div>
                    <div onClick={() => changeSort("status")} >
                        Status <span className="icon">{getIcon("status")}</span>
                    </div>
                </div>
                <div className="feeList">
                    {fees.filter(applySearchFilter).sort(dynamicSort(sortState)).map((fee) =>
                        (showFeeLong &&
                            <FeeLong id={fee.id}
                                memberId={fee.memberId}
                                date={fee.date}
                                amount={fee.amount}
                                status={fee.status}
                                key={fee.id}
                                setFlashState={setFlashState} />)
                        || <FeeShort
                            id={fee.id}
                            memberId={fee.memberId}
                            date={fee.date}
                            amount={fee.amount}
                            status={fee.status}
                            key={fee.memberId} />
                    )}
                </div>
            </div>
        </>
    );
}

FeeTable.propTypes = {
    fees: PropTypes.array.isRequired
}

FeeTable.defaultProps = {
    fees: []
}

export default FeeTable;