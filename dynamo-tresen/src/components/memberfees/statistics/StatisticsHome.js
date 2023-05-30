import arrow from '../css/arrow.svg';

/**
 * Homepage for the statistics with hints on what to do next.
 * 
 * @author Jonas Buse
 */
const StatisticsHome = () => {

    return (
        <>
            <div id="stat-home-date-tip">Select a range of dates!
                <img src={arrow} id="stat-home-date-arrow" alt="Arrow SVG" />
            </div>

            <div id="stat-home-topic-tip">Select a topic!
                <img src={arrow} id="stat-home-topic-arrow" alt="Arrow SVG" />
            </div>

        </>
    )
};

export default StatisticsHome;