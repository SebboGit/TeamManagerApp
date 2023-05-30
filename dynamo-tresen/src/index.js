import React from 'react';
import { Route, BrowserRouter } from 'react-router-dom';
import ReactDOM from 'react-dom';

import './index.css';
import App from './App';
import Nav from './components/NavBar';

import newMember from "./components/memberAdministration/components/newMember";
import searchMember from "./components/memberAdministration/components/searchMember";
import AllMembers from "./components/memberAdministration/components/AllMembers";
import Calendar from './components/calendar/Calendar';
import MemberAdministration from './components/memberAdministration/MemberAdministration'
import FeeOverview from './components/memberfees/Memberfees';
import CalculateFees from './components/memberfees/CalculateFees';
import TeamManager from "./components/teamManager/overviewPage/TeamManager";
import TeamAdministrationCreate from "./components/teamManager/teamAdministration/TeamAdministrationCreate";
import TeamAdministrationChange from "./components/teamManager/teamAdministration/TeamAdministrationChange";
import TeamInfo from "./components/teamManager/teamInfomation/TeamInfo";
import EditTeamInfo from "./components/teamManager/teamInfomation/EditTeamInfo";
import { StatisticsOverview } from './components/memberfees/statistics/StatisticsOverview';
import OverviewEventPicture from "./components/eventPictures/OverviewEventPicture";
import CreateEventPicture from "./components/eventPictures/components/CreateEventPicture";
import DeleteEventPicture from "./components/eventPictures/components/DeleteEventPicture";
import PutEventPicture from "./components/eventPictures/components/PutEventPicture";
import GetEventPicture from "./components/eventPictures/components/GetEventPicture";



const routes = (
    <BrowserRouter>
        <div>
            <Nav />
            <Route exact path="/calendar" component={Calendar}></Route>
            <Route exact path="/pictures" component={OverviewEventPicture}></Route>
            <Route exact path="/pictures/create" component={CreateEventPicture}></Route>
            <Route exact path="/pictures/delete" component={DeleteEventPicture}></Route>
            <Route exact path="/pictures/change" component={PutEventPicture}></Route>
            <Route exact path="/pictures/get" component={GetEventPicture}></Route>
            <Route exact path="/fees" component={FeeOverview}></Route>
            <Route exact path="/fees/calculate" component={CalculateFees}></Route>
            <Route exact path="/statistics" component={StatisticsOverview}></Route>
            <Route exact path="/teams" component={TeamManager}></Route>
            <Route exact path="/teams/administration/create" component={TeamAdministrationCreate}></Route>
            <Route exact path="/teams/administration/change" component={TeamAdministrationChange}></Route>
            <Route exact path="/teams/information/viewandcreate" component={TeamInfo}></Route>
            <Route exact path="/teams/information/edit" component={EditTeamInfo}></Route>
            <Route exact path="/memberAdministration" component={MemberAdministration}></Route>
            <Route exact path="/newMember" component={newMember}></Route>
            <Route exact path="/searchMember" component={searchMember}></Route>
            <Route exact path="/allMembers" component={AllMembers}></Route>
            <Route exact path="/" component={App}></Route>
        </div>
    </BrowserRouter>
)

ReactDOM.render(
    routes,
    document.getElementById('root')
);
