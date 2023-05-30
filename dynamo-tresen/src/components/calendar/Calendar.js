import React from "react";
import "./css/Calendar.css";
import Container from "../calendar/Container";
import {
  format,
  startOfWeek,
  addDays,
  addMonths,
  subMonths,
  startOfMonth,
  endOfMonth,
  endOfWeek,
  isSameMonth,
  isSameDay,
  toDate,
} from "date-fns";
import EventService from "./EventService";
import DownloadLink from "./DownloadLink";
import { Link } from "react-router-dom";

class Calendar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentMonth: new Date(),
      selectedDate: new Date(),
      eventsOnSelectedDay: 0,
      events: [],
      btnText: "Create Event",
      id: -1,
      date: "",
      time: "",
      name: "",
      description: "",
      numOfPhotos: 0,
      program: null,
    };

    this.refresh = this.refresh.bind(this);
  }

  async componentDidMount() {
    this.setState({ events: await EventService.getEvents() });
  }

  renderHeader() {
    const dateFormat = "MMMM yyyy";

    return (
      <div className="header row flex-middle">
        <div className="column col-start">
          <div className="icon" onClick={this.prevMonth}>
            chevron_left
          </div>
        </div>
        <div className="column col-center">
          <span>{format(this.state.currentMonth, dateFormat)}</span>
        </div>
        <div className="column col-end" onClick={this.nextMonth}>
          <div className="icon">chevron_right</div>
        </div>
      </div>
    );
  }

  renderDays() {
    const dateFormat = "iiii";
    const days = [];

    let startDate = startOfWeek(this.state.currentMonth);

    for (let i = 0; i < 7; i++) {
      days.push(
        <div className="column col-center" key={i}>
          {format(addDays(startDate, i), dateFormat)}
        </div>
      );
    }

    return <div className="days row">{days}</div>;
  }

  /**
   * Constructs the html for the calendar cells, disables the days from previous or upcoming moths,
   * selects the corresponding day and checks and shows how many events occur on given days.
   *
   */
  renderCells() {
    const { currentMonth, selectedDate } = this.state;
    const monthStart = startOfMonth(currentMonth);
    const monthEnd = endOfMonth(monthStart);
    const startDate = startOfWeek(monthStart);
    const endDate = endOfWeek(monthEnd);

    const dateFormat = "d";
    const rows = [];

    let days = [];
    let day = startDate;
    let formattedDate = "";

    while (day <= endDate) {
      for (let i = 0; i < 7; i++) {
        formattedDate = format(day, dateFormat);
        const cloneDay = day;
        const isoDay = new Date(day.getTime() - day.getTimezoneOffset() * 60000)
          .toISOString()
          .substring(0, 10);
        let eventsOnDay = 0;
        if (this.state.events.length !== 0) {
          let filtered = this.state.events.filter((e) => e.date === isoDay);
          eventsOnDay = filtered.length;
        }

        days.push(
          <div
            className={`column cell ${
              !isSameMonth(day, monthStart)
                ? "disabled"
                : isSameDay(day, selectedDate)
                ? "selected"
                : ""
            }`}
            key={day}
            onClick={() => this.onDateClicked(toDate(cloneDay))}
          >
            <span className="number">{formattedDate}</span>
            <span className={eventsOnDay < 1 ? "no-events" : "event"}>
              {eventsOnDay}
            </span>
          </div>
        );
        day = addDays(day, 1);
      }

      rows.push(
        <div className="row" key={day}>
          {days}
        </div>
      );
      days = [];
    }

    return <div className="body">{rows}</div>;
  }

  /**
   * Constructs the html for the details list of events on a specific day.
   *
   */
  renderDetails() {
    let events = [];
    let filtered = [];
    if (this.state.events !== 0) {
      const day = new Date(
        this.state.selectedDate.getTime() -
          this.state.selectedDate.getTimezoneOffset() * 60000
      )
        .toISOString()
        .substring(0, 10);

      filtered = this.state.events.filter((e) => e.date === day);
    }

    for (let i = 0; i < filtered.length; i++) {
      events.push(
        <tr className="event-row" key={i}>
          <td>{this.state.selectedDate.toDateString()}</td>
          <td>{filtered[i].time}</td>
          <td>{filtered[i].name}</td>
          <td>{filtered[i].description}</td>
          <td>
            {filtered[i].numOfPhotos === 0 ? (
              0
            ) : (
              <Link
                to={{
                  pathname: "/pictures/get",
                  eventIdForm: { num: filtered[i].id },
                }}
                className="btn-event event-small"
              >
                {filtered[i].numOfPhotos}
              </Link>
            )}
          </td>
          <td>
            {filtered[i].program !== null ? (
              <DownloadLink
                src={`data:application/pdf;base64,${filtered[i].program}`}
              >
                Download
              </DownloadLink>
            ) : (
              "No program available"
            )}
          </td>
          <td>
            <button
              onClick={() => this.handleEdit(filtered[i])}
              className="edit-button action-button"
            >
              &#9998;
            </button>
            <button
              onClick={() => this.handleDelete(filtered[i].id)}
              className="delete-button action-button"
            >
              &#10006;
            </button>
          </td>
        </tr>
      );
    }
    return events;
  }

  onDateClicked = (day) => {
    this.setState({ selectedDate: day });
    this.renderDetails();
    this.setState({ btnText: "Create Event" });
    this.closeForm();
  };

  nextMonth = () => {
    this.setState({
      currentMonth: addMonths(this.state.currentMonth, 1),
    });
  };

  prevMonth = () => {
    this.setState({
      currentMonth: subMonths(this.state.currentMonth, 1),
    });
  };

  /**
   * On deleting an event, remove the event and refresh the page afterwards.
   *
   * @param {*} id database id of the event to delete
   */
  async handleDelete(id) {
    await EventService.deleteEvent(id);
    this.setState(
      {
        date: "",
        time: "",
        name: "",
        description: "",
        numOfPhotos: 0,
        program: null,
      },
      this.refresh
    );
  }

  /**
   * On editing an event, first close the form it it is currently open.
   * Change the Button Text, set state that is passed to the container and open the edit form.
   *
   * @param {*} e event
   */
  async handleEdit(e) {
    this.closeForm();
    this.setState({ btnText: "Edit Event" });

    this.setState(
      {
        id: e.id,
        date: e.date,
        time: e.time,
        name: e.name,
        description: e.description,
        numOfPhotos: e.numOfPhotos,
        program: e.program,
      },
      this.openForm
    );
  }

  openForm() {
    if (document.getElementById("triggerButton") != null) {
      document.getElementById("triggerButton").click();
    }
  }

  closeForm() {
    if (document.getElementById("close-modal") != null) {
      document.getElementById("close-modal").click();
    }
  }

  async refresh() {
    this.setState({ events: await EventService.getEvents() });
    this.renderDetails();
    this.setState({ btnText: "Create Event" });
    this.closeForm();
  }

  render() {
    return (
      <>
        <div className="calendar">
          {this.renderHeader()}
          {this.renderDays()}
          {this.renderCells()}
        </div>

        <div>
          <p className="calendar-hint">
            Please refresh the page manually after uploading a program.
          </p>
          <table className="event-table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Name</th>
                <th>Description</th>
                <th>Number of photos</th>
                <th>Program</th>
                <th></th>
              </tr>
            </thead>
            <tbody>{this.renderDetails()}</tbody>
          </table>
          <Container
            triggerText={this.state.btnText}
            id={this.state.id}
            date={this.state.date}
            time={this.state.time}
            name={this.state.name}
            description={this.state.description}
            numOfPhotos={this.state.numOfPhotos}
            program={this.state.program}
            onEdit={this.state.btnText === "Edit Event"}
            refresh={this.refresh}
          />
        </div>
      </>
    );
  }
}

export default Calendar;
