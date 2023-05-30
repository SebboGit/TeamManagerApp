/**
 * @author Michael Fortenbacher
 */
export default class InfomaterialService {
    static baseUrl() {
        return "http://localhost:8091/api/v1.0/infomaterial"
    }

    /**
     * returns all infomaterials that exist in the database
     * @returns JSON
     */
    static async getAllInfomaterials() {
        const response = await fetch(this.baseUrl());
        if (response.status === 404) {
            return [];
        } else {
            return await response.json();
        }
    }

    /**
     * returns the infomaterial with the given id
     * @param id
     */
    static async getInfomaterialById(id) {
        const response = await fetch(this.baseUrl() + '/' + id);
        if (response.status === 404) {
            return [];
        } else {
            return await response.json();
        }
    }

    /** Posts a new infomaterial
     *
     * @param state
     */
    static  createInfomaterial = async (state) => {
        const pdfInput = new Blob([state.pdf], {
            type: 'multipart/form-data'
        });
        let pdf = new FormData();
        const filename = state.name;
        pdf.append('file', pdfInput, filename)
        return await fetch(this.baseUrl() + `?name=${state.name}&description=${state.description}&url=${state.url}`, {
            method: 'POST',
            body: pdf
        });
    }


    /**
     * changes different attributes of a infomaterial
     */
    static editInfomaterial = async (state) => {
        const pdfInput = new Blob([state.pdf], {
            type: 'multipart/form-data'
        });
        let pdf = new FormData();
        const filename = state.name;
        pdf.append('file', pdfInput, filename)
        return await fetch(this.baseUrl() + `/editInfomaterial?id=${state.id}&name=${state.name}&description=${state.description}&url=${state.url}`, {
            method: 'PUT',
            body: pdf
        });
    }
}