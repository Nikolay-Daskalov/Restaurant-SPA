import { Footer } from "./components/footer/Footer";
import { Header } from "./components/header/Header";
import { Main } from "./components/main/Main";

import style from './App.module.css';
import { AuthProvider } from "./contexts/AuthContext";

function App() {
    return (
        <div className={style.App}>
            <AuthProvider>
                <Header />
                <Main />
            </AuthProvider>
            <Footer />
        </div>
    );
}

export default App;
