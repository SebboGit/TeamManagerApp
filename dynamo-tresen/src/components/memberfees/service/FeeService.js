
/**
 * Class to serve all needed HTTP requests to the backend.
 * Made with static methods to avoid exhaustive-deps problems 
 * and infinte loops with the useEffect hooks.
 * 
 * @author Jonas Buse
 */
export default class FeeService {

    static base_url() {
        return "http://localhost:8094/api/v1/fees/";
    }

    /**
     * Returns all existing fees.
     * 
     * @return JSON list of all fees
     */
    static async getAllFees() {
        const resp = await fetch(this.base_url());
        if (resp.status === 404)
            return [];
        return await resp.json();
    }

    /**
     * Returns all fees in a given time frame.
     * 
     * @return JSON list of fees
     */
    static async getFeesByDate(start, end) {
        const resp = await fetch(this.base_url() + `date?from=${start}&until=${end}`);
        if (resp.status === 404)
            return [];
        return await resp.json();
    }

    /**
     * Posts a singular new fee. 
     * 
     * @param newFee Json representation of a new fee
     * @return Response Object
     */
    static async createFee(newFee) {
        newFee.memberId = parseInt(newFee.memberId);
        newFee.amount = parseInt(newFee.amount);
        const options = {
            method: 'POST',
            body: JSON.stringify([newFee]),
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return await fetch(this.base_url(), options);
    }

    /**
     * Posts a multiple new fees. 
     * 
     * @param newFee Json representation of a new fee
     * @return Response Object
     */
    static async createFees(newFees) {
        const options = {
            method: 'POST',
            body: JSON.stringify(newFees),
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return await fetch(this.base_url(), options);
    }

    /**
     * Delete a fee by its ID.
     * 
     * @param feeId Integer unique fee id
     * @return Response Object
     */
    static async deleteFee(feeId) {
        return await fetch(this.base_url() + feeId, { method: 'DELETE' });
    }

    /**
     * Change a give fees status to confirmed with its it.
     * 
     * @param feeId Integer unique fee id
     * @return Response Object
     */
    static async confirmFee(feeId) {
        return await fetch(this.base_url() + feeId + "?status=confirmed", { method: 'PATCH' });
    }

    /**
     * Change a whole fee with a new one.
     * 
     * @param fee fee object without the id
     * @param id the unique id
     * @return Response Object
     */
    static async changeFee(fee, id) {
        const options = {
            method: 'PUT',
            body: JSON.stringify(fee),
            headers: {
                'Content-Type': 'application/json'
            }
        };
        return await fetch(this.base_url() + id, options);
    };

    /**
     * 
     * @param {year} int for which the fees should be generated
     * @param {persist} boolean if the new fees should also be saved to the db 
     * @returns 
     */
    static async calculateFees(year, persist) {
        return await fetch(this.base_url() + `calculate/${year}`, { method: persist ? 'POST' : 'GET' })
    }
}
