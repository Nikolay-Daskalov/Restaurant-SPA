import { Footer } from "./components/footer/Footer";
import { Header } from "./components/header/Header";
import { Main } from "./components/main/Main";

import style from './App.module.css';

function App() {
    return (
        <div className={style.App}>
            <Header />
            <Main />
            <Footer />
        </div>
    );
}

export default App;
