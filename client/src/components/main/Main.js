import { Routes, Route } from 'react-router-dom';
import { Home } from './home/Home';

import style from './Main.module.css';

export function Main() {
    return (
        <main className={style.main}>
            <Routes>
                <Route path="/" element={<Home />} />
            </Routes>
        </main>
    );
}
