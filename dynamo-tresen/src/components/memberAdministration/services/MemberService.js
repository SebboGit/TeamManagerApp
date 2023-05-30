import newMember from "../components/newMember";

export default class MemberService {

    constructor() {
        this.base_url = "http://localhost:8092/api/v1/memberAdministration/";
    }

    static baseUrl() {
        return "http://localhost:8092/api/v1/memberAdministration/";
    }

    /**
     * Return all AllMembers from Db
     *
     * @returns {Promise<*[]|any>}
     */
    getAllMembers = async () => {
        const response = await fetch(this.base_url + 'list');
        if (response.status === 400) {
            return [];
        } else
            return await response.json();
    }

    getOneMember = async (id) => {
        const response = await fetch(this.base_url + 'member/' + id, {method: 'GET'});
        if (response.status === 200) {
            return response.json()
        } else {
            return "No Member with this ID in Database";
        }
    }

    static async newMember(member) {
        const params = {
            method: 'POST',
            body: JSON.stringify(member),
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return await fetch(this.baseUrl() + '/create', params);
    }

}