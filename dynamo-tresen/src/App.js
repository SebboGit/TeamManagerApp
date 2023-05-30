import logo from './resources/logo.png';
import './App.css';
import Footer from './components/footer.js';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>SE2_21_Gruppe_05</h1>
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    Welcome to the Footballclub management system!
                </p>
                <button className="button">
                    <a href={"/overview"} className="a">Have fun!</a>
                </button>
                <Footer group="IB4C" semester="SoSe2021" seGroup="Gruppe 5" />
            </header>
        </div>
    );
}

export default App;
