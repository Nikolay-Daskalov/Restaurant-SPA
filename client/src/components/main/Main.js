import { Routes, Route } from 'react-router-dom';
import { Home } from './home/Home';
import { Login } from './login/Login';

import style from './Main.module.css';
import { MenuContainer } from './menu/MenuContainer';
import { Register } from './register/Register';

export function Main() {
    return (
        <main className={style.main}>
            <section>
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/menu/*' element={<MenuContainer />} />
                    <Route path='/login' element={<Login />} />
                    <Route path='/register' element={<Register />} />
                </Routes>
            </section>
        </main>
    );
}
