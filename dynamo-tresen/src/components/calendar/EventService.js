import React from "react";

class EventService extends React.Component {
  static url() {
    return "http://localhost:8095/api/calendar";
  }

  /**
   * REST-API Call to get a list of all events
   * @returns all Event instances from the databas
   */
  static async getEvents() {
    const resp = await fetch(this.url());
    if (resp.status === 404) {
      return [];
    }
    return await resp.json();
  }

  /**
   * REST-API Call to get the id of the most recent event in the database
   * @returns latest event id
   */
  static async getLatestEventID() {
    const resp = await fetch(this.url() + "/num");
    if (resp.status === 404) {
      return -1;
    }
    return await resp.json();
  }

  static async getEventsByDate(date) {
    const resp = await fetch(this.url() + "/date/" + date);
    if (resp.status === 404) {
      return [];
    }
    return await resp.json();
  }

  static async deleteEvent(id) {
    return await fetch(this.url() + "/id/" + id, { method: "DELETE" });
  }

  static async createEvent(event) {
    event.id = parseInt(event.id);
    const req = {
      method: "POST",
      body: JSON.stringify([event]),
      headers: {
        "Content-Type": "application/json",
      },
    };
    return await fetch(this.url(), req);
  }
}

export default EventService;
