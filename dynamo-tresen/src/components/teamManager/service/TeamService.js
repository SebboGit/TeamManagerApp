/**
 * @author Michael Fortenbacher
 */
export default class TeamService {
    static baseUrl() {
        return "http://localhost:8091/api/v1.0/team"
    }

    /**
     * returns all sport teams in the database
     */
    static async getAllTeams() {
        const response = await fetch(this.baseUrl());
        if (response.status === 404) {
            return [];
        } else {
            return await response.json();
        }
    }

    /**
     * returns the sport team with the given id
     * @param id id of the team
     */
    static async getTeamById(id) {
        const response = await fetch(this.baseUrl() + '/' + id);
        if (response.status === 404) {
            return [];
        } else {
            return await response.json();
        }
    }

    static async getLatestTeam() {
        const response = await fetch(this.baseUrl() + '/latestTeamId');
        if (response.status === 404) {
            return [];
        } else {
            return await response.json();
        }
    }

    /** Posts a new team
     *
     * @param newTeam
     */
    static async createTeam(newTeam) {
        const params = {
            method: 'POST',
            body: JSON.stringify(newTeam),
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return await fetch(this.baseUrl(), params);
    }

    /**
     * changes different attributes of a team
     */
    static async changeTeam(changedTeam) {
        const params = {
            method: 'PUT',
            body: JSON.stringify(changedTeam),
            headers: {
                'Content-Type': 'application/json'
            }
        }
        console.log(params);
        return await fetch(this.baseUrl() + '/changeTeam', params);
    }
}