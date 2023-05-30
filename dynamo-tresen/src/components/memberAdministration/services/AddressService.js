export default class AddressService {

    static baseUrl() {
        return "http://localhost:8092/api/v1/memberAdministration/";
    }

    static async newAddress(address) {
        const params = {
            method: 'POST',
            body: JSON.stringify(address),
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return await fetch(this.baseUrl() + '/create', params);
    }
}