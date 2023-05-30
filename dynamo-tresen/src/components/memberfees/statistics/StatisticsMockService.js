/**
 * Mock service class that creates semi-pseudo-random mocks for other microservices.
 * @author Jonas Buse
 */

/**
 * Creates a list of semi-pseudo-random mocks representing members.
 * A member can have a date of leaving which is before the date of entering,
 * since that doesn't matter for the purpose of showing the statistics.
 * 
 * This will be replaced by a real connection to the member API once that is available.
 * 
 * @return {Array} of members
 */
export const mockMembers = () => {
    const memberList = [];

    [...Array(30).keys()].forEach(() => {
        memberList.push({
            id: Math.floor(Math.random() * 30 + 1),
            firstname: "Example",
            lastname: "Member",
            entryDate: randomDate(),
            leavingDate: (Math.floor(Math.random() * 3) > 1) ? randomDate() : null,
            memberships: Math.floor(Math.random() * 7)
        })
    });

    return memberList;
};

/**
 * Mocks a single member with a given id.
 * If the id is 0, then a random id is chosen.
 * 
 * @param {number} id
 * @return {Object} a mocked member
 */
export const mockSingleMember = (id) => {
    return {
        id: id || Math.floor(Math.random() * 10 + 1),
        firstname: "Example",
        lastname: "Member",
        entryDate: randomDate(),
        leavingDate: (Math.floor(Math.random() * 3) > 1) ? randomDate() : null,
        memberships: Math.floor(Math.random() * 6 + 1)
    };
};

/**
 * Creates a list of semi-pseudo-random mocks representing teams.
 * 
 * This will be replaced by a real connection to the member API once that is available.
 *
 * @return {Array} of teams
 */
export const mockTeams = () => {
    const teamsList = [];

    [...Array(5).keys()].forEach(() => {
        const memCount = Math.floor(Math.random() * 15 + 10);
        const id = Math.floor(Math.random() * 100 + 1);
        const randomMemberIds = [];

        do {
            const randomNumber = Math.floor(Math.random() * 30 + 1);
            if (!randomMemberIds.includes(randomNumber))
                randomMemberIds.push(randomNumber);
        } while (randomMemberIds.length < memCount);

        teamsList.push({
            id: id,
            name: "Team #" + id,
            membercount: memCount,
            members: randomMemberIds
        });
    });

    return teamsList;
};

/**
 * Creates a random date from 2020-2021.
 * The days are limited from 01 to 28 to avoid trouble with those pesky Februaries.
 *
 * @return {String} in ISO 8601 format.
 */
const randomDate = () => {
    const randomYear = ("0000" + Math.floor(Math.random() * 2 + 2020)).slice(-4);
    const randomMonth = ("00" + Math.floor(Math.random() * 12 + 1)).slice(-2);
    const randomDay = ("00" + Math.floor(Math.random() * 28 + 1)).slice(-2);

    return `${randomYear}-${randomMonth}-${randomDay}`;
};
