import React from 'react';

import '../css/searchbar.css';

/**
 * Implementation of a search bar that automatically updates a given
 * prop with the value of the input.
 * 
 * Two icons, one to clear, one to show help and the help
 * bubble itself are included.
 * 
 * @author Jonas Buse
 */
const SearchBar = (props) => {
    const [showHint, setShowHint] = React.useState(false);

    return (
        <div className="search-bar-group" >
            <form className="fee-search-bar" onSubmit={(e) => e.preventDefault()}>
                <input placeholder="Search..."
                    onChange={(e) => props.setSearchState(e.target.value)}
                    value={props.searchState}
                    className={props.searchState !== "" ? "search-not-empty" : ""}>
                </input>
                <span className={"icon clear-search" + (props.searchState !== "" ? " search-not-empty" : "")} onClick={() => props.setSearchState("")}>backspace</span>
                <span className="icon show-hint" onClick={() => setShowHint(c => !c)}>help</span>
            </form>
            <small className={"fee-search-hint " + (showHint && "show-hint")}>
                Search for specific fees using the following syntax: <br />
                [attribute]: [value], [attribute]: [value], ... <br /> <br />
                Example: Status: Confirmed, Date: 1998-11-01, Member ID: 1 <br />
                <i>Hint: Capitalization and whitespace don't matter!<br />
                    But commas and colons do!</i>

            </small>
        </div>
    );
};

export default SearchBar;