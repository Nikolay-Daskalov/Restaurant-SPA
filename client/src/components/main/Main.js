import { Routes, Route } from 'react-router-dom';
import { Home } from './home/Home';
import { Login } from './login/Login';

import style from './Main.module.css';
import { MenuCategoryList } from './menu/category/MenuCategoryList';
import { FoodList } from './menu/food/FoodList';
import { Register } from './register/Register';

export function Main() {
    return (
        <main className={style.main}>
            <section>
                <Routes>
                    <Route path="" element={<Home />} />
                    <Route path="menu">
                        <Route path="" element={<MenuCategoryList />} />
                        <Route path=":category" element={<FoodList />} />
                    </Route>
                    <Route path="login" element={<Login />} />
                    <Route path="register" element={<Register />} />
                </Routes>
            </section>
        </main>
    );
}
