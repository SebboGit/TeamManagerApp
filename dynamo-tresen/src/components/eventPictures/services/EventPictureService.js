/**
 * this class connects the frontend with the backend
 * it passes the values entered in the CreateEventPicture.js via the URL to the backend
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
export default class EventPictureService {

    /**
     * base URL to reach the backend
     */
    constructor() {
        this.base_url = "http://localhost:8093/api/v1/EventPictures/";
    }

    /**
     * This method passes the value of the state to the backend.
     * First it creates a new Blob() object with the image value and content type 'multipart/form-data'.
     * Then a new FormData() object is declared to pass in the image data via append().
     * A new filename is set to have an uniform standard filename in the database.
     *
     * After that the values are posted to the backend via the URL params and the image in the body of the POST request.
     *
     * @param state
     * @returns {Promise<Response>}
     */
    uploadEventPicture = async (state) => {
        const imageInput = new Blob([state.image], {
            type: 'multipart/form-data'
        });
        var file = new FormData();
        const date = new Date()
        const fileName = state.title + "_" + date.getDate() + '_' + (date.getMonth() + 1) + '_' + date.getFullYear();
        file.append('file', imageInput, fileName);
        return await fetch(this.base_url + `upload?title=${state.title}&description=${state.description}&rating=${state.rating}&event=${state.event}&members=${state.members}`, {
            method: 'POST',
            body: file
        });
    }

    /**
     * This method passes the id with a DELETE-Method to the backend.
     *
     * @param id
     * @returns {Promise<Response>}
     */
    deleteEventPicture = async (id) => {
        return await fetch(this.base_url + `delete/` + id, {
            method: 'DELETE'
        })
    }

    /**
     * This method passes the id with a GET-Method to the backend to get the Event Picture with the given ID.
     * If the response is OK (200), then the Data is returned.
     * Otherwise a String with hints is returned.
     *
     * @param id
     * @returns {Promise<string|any>}
     */
    getEventPicture = async (id) => {
        const response = await fetch(this.base_url + `get/` + id, {
            method: 'GET'
        });
        if (response.status === 200) {
            return response.json()
        } else {
            return `Ooops, something went wrong!\nPlease check following:\n\t- Have you entered an ID?\n\t- The ID should be at least 1.\n\t- Maybe the ID '${id}' is not present in the Database.`
        }
    }

    /**
     * This method passes the eventId with a GET-Method to the backend to get all Event Pictures with the given ID.
     * If the response is OK (200), then the Data is returned.
     * Otherwise a String with hints is returned.
     *
     * @param eventId
     * @returns {Promise<string|any>}
     */
    getEventPictureByEventId = async (eventId) => {
        const response = await fetch(this.base_url + `getByEvent/` + eventId, {
            method: 'GET'
        });
        if (response.status === 200) {
            return response.json()
        } else {
            return "Ooops, something went wrong!\nPlease check following:\n\t- Have you entered an event ID?\n\t- The event ID should be at least 1.\n\t- Maybe the event ID is not present in the Event Picture Database."
        }
    }

    /**
     * This method passes the titleId with a GET-Method to the backend to get all Event Pictures with the given title.
     * If the response is OK (200), then the Data is returned.
     * Otherwise a String with hints is returned.
     *
     * @param titleId
     * @returns {Promise<string|any>}
     */
    getEventPictureByTitle = async (titleId) => {
        const response = await fetch(this.base_url + `getByTitle/` + titleId, {
            method: 'GET'
        });
        if (response.status === 200) {
            return response.json()
        } else {
            return "Ooops, something went wrong!\nPlease check following:\n\t- Have you entered a title?\n\t- Maybe the title is not present in the Database."
        }
    }

    /**
     * This method passes the descriptionId with a GET-Method to the backend to get all Event Pictures with the given description.
     * If the response is OK (200), then the Data is returned.
     * Otherwise a String with hints is returned.
     *
     * @param descriptionId
     * @returns {Promise<string|any>}
     */
    getEventPictureByDescription = async (descriptionId) => {
        const response = await fetch(this.base_url + `getByDescription/` + descriptionId, {
            method: 'GET'
        });
        if (response.status === 200) {
            return response.json()
        } else {
            return "Ooops, something went wrong!\nPlease check following:\n\t- Have you entered a description?\n\t- Maybe the description is not present in the Database."
        }
    }

    /**
     * This method passes the ratingId with a GET-Method to the backend to get all Event Pictures with the given rating.
     * If the response is OK (200), then the Data is returned.
     * Otherwise a String with hints is returned.
     *
     * @param ratingId
     * @returns {Promise<string|any>}
     */
    getEventPictureByRating = async (ratingId) => {
        const response = await fetch(this.base_url + `getByRating/` + ratingId, {
            method: 'GET'
        });
        if (response.status === 200) {
            return response.json()
        } else {
            return `Ooops, something went wrong!\nPlease check following:\n\t- Have you entered rating value?\n\t- The rating should be between 1 and 5.\n\t- Maybe the Rating '${ratingId}' is not present in the Database.`
        }
    }

    /**
     * This method passes a GET-Method to the backend to get all Event Pictures.
     * If the response is OK (200), then the Data is returned.
     * Otherwise a String with hints is returned.
     *
     * @returns {Promise<string|any>}
     */
    getAllEventPictures = async () => {
        const response = await fetch(this.base_url + `getAll`, {
            method: 'GET'
        });
        if (response.status === 200) {
            return response.json()
        } else {
            return "Ooops, something went wrong!\n\t- Maybe the Database is empty."
        }
    }

    /**
     * This method receives the state from the PutEventPicture.js.
     * Then the method checks, if the state.imageNew is 'null' (means if the image was changed).
     * If it is 'null', then only the information are send to the backend.
     * If it is not 'null', then the same procedure as the POST-Method is run through, but with a PUT-Method.
     *
     * @param state
     * @returns {Promise<Response>}
     */
    putEventPicture = async (state) => {
        if (state.imageNew === null) {
            return await fetch(this.base_url + 'update/' + state.id + `?title=${state.title}&description=${state.description}&rating=${state.rating}&event=${state.event}&members=${state.members}`, {
                method: 'PUT'
            })
        } else {
            const imageInput = new Blob([state.imageNew], {
                type: 'multipart/form-data'
            });
            var file = new FormData();
            const date = new Date()
            const fileName = state.title + "_" + date.getDate() + '_' + (date.getMonth() + 1) + '_' + date.getFullYear();
            file.append('file', imageInput, fileName);
            return await fetch(this.base_url + 'update/' + state.id + `?title=${state.title}&description=${state.description}&rating=${state.rating}&event=${state.event}&members=${state.members}`, {
                method: 'PUT',
                body: file
            })
        }
    }

    /**
     * This method checks in the Calendar Microservice, if the Event ID exists in the calendar database
     *
     * @param eventId
     * @returns {Promise<Response>}
     */
    getEventIdExistence = async (eventId) => {
        return await fetch("http://localhost:8095/api/calendar/id/" + eventId, {
            method: 'GET'
        });
    }
}