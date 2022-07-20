import { Routes, Route } from 'react-router-dom';
import { Home } from './home/Home';
import { Login } from './login/Login';

import style from './Main.module.css';
import { Register } from './register/Register';

export function Main() {
    return (
        <main className={style.main}>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Routes>
        </main>
    );
}
