import React from "react";
import "./css/ModalButton.css";
import EventService from "./EventService";

class EventForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: -1,
      date: "",
      time: "",
      name: "",
      description: "",
      numOfPhotos: 0,
      program: null,
      onEdit: false,
    };

    this.handleChange = this.handleChange.bind(this);
    this.handlePDFChange = this.handlePDFChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this.setState({
      id: this.props.id,
      date: this.props.date,
      time: this.props.time,
      name: this.props.name,
      description: this.props.description,
      numOfPhotos: this.props.numOfPhotos,
      program: this.props.program,
      onEdit: this.props.onEdit,
    });
  }

  handleChange(event) {
    const name = event.target.name;
    let value = event.target.value;
    this.setState({ [name]: value });
  }

  handlePDFChange(event) {
    this.setState({ program: event.target.files[0] });
  }

  async handleSubmit(event) {
    event.preventDefault();
    if (this.state.onEdit) {
      this.patchData();
    } else {
      this.postData();
    }
  }

  async postData() {
    const options = {
      method: "POST",
      body: JSON.stringify([
        {
          id: this.state.id,
          date: this.state.date,
          time: this.state.time,
          name: this.state.name,
          description: this.state.description,
          numOfPhotos: this.state.numOfPhotos,
        },
      ]),
      headers: {
        "Content-Type": "application/json",
      },
    };

    await fetch("http://localhost:8095/api/calendar/", options);

    if (this.state.program !== null) {
      var number = await EventService.getLatestEventID();
      this.uploadPDF(number);
    }

    this.props.refresh();
  }

  async uploadPDF(id) {
    const pdf = new Blob([this.state.program], { type: "multipart/form-data" });
    var file = new FormData();
    const filename = this.state.program.name;
    file.append("file", pdf, filename);
    await fetch(`http://localhost:8095/api/calendar/upload/id/${id}`, {
      method: "POST",
      body: file,
    });
  }

  async patchData() {
    const options = {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
    };

    await fetch(
      `http://localhost:8095/api/calendar/id/${this.state.id}?name=${
        this.state.name
      }&description=${this.state.description}&date=${
        this.state.date
      }&time=${this.state.time.replace(":", "")}`,
      options
    );

    this.props.refresh();
  }

  render() {
    return (
      <form className="event-form" onSubmit={this.handleSubmit}>
        <div className="event-form-group ic1 short">
          <label htmlFor="date">Date</label>
          <input
            type="date"
            required
            className="form-control"
            id="date"
            name="date"
            value={this.state.date}
            onChange={this.handleChange}
          />
        </div>
        <div className="event-form-group ic2 short">
          <label htmlFor="time">Time</label>
          <input
            type="time"
            required
            className="form-control"
            id="time"
            placeholder="19:00"
            name="time"
            value={this.state.time}
            onChange={this.handleChange}
          />
        </div>
        <div className="event-form-group ic2 long">
          <label htmlFor="name">Name</label>
          <input
            required
            className="form-control"
            id="name"
            name="name"
            value={this.state.name}
            onChange={this.handleChange}
          />
        </div>
        <div className="event-form-group ic2 long">
          <label htmlFor="description">Description</label>
          <input
            className="form-control"
            id="description"
            name="description"
            value={this.state.description}
            onChange={this.handleChange}
          />
        </div>
        <div className="event-form-group ic2 short">
          <label htmlFor="numOfPhotos">Number of Photos</label>
          <input
            type="number"
            min="0"
            className="form-control"
            id="numOfPhotos"
            name="numOfPhotos"
            value={this.state.numOfPhotos}
            onChange={this.handleChange}
          />
        </div>
        {this.state.onEdit ? null : (
          <div className="event-form-group ic2 long">
            <label for="program" class="custom-file-upload">
              Upload Program
            </label>
            <input
              type="file"
              accept="application/pdf"
              className="form-control upload-pdf"
              id="program"
              name="program"
              onChange={this.handlePDFChange}
            />
          </div>
        )}

        <div className="event-form-group ic2">
          <button
            onClick="http://localhost:3000/calendar"
            className="btn-event"
            type="submit"
          >
            Submit
          </button>
        </div>
      </form>
    );
  }
}
export default EventForm;
