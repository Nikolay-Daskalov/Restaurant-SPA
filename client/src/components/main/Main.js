import { Routes, Route } from 'react-router-dom';
import { PrivateGuard } from '../../guards/PrivateGuard';
import { FoodEdit } from './edit/FoodEdit';
import { Home } from './home/Home';
import { Login } from './login/Login';

import style from './Main.module.css';
import { MenuCategoryList } from './menu/category/MenuCategoryList';
import { FoodDetail } from './menu/food/FoodDetail';
import { FoodList } from './menu/food/FoodList';
import { Profile } from './profile/Profile';
import { Register } from './register/Register';

export function Main() {
    return (
        <main className={style.main}>
            <section>
                <Routes>
                    <Route path="" element={<Home />} />
                    <Route path="menu">
                        <Route index element={<MenuCategoryList />} />
                        <Route path=":category" element={<FoodList />} />
                        <Route path=":category/:id" element={<FoodDetail />} />
                    </Route>
                    <Route path="login" element={<Login />} />
                    <Route path="register" element={<Register />} />
                    <Route path="account/*" element={<PrivateGuard />}>
                        <Route path='meal/:id/edit' element={<FoodEdit />} />
                        <Route path='*' element={<Profile />} />
                    </Route>
                </Routes>
            </section>
        </main>
    );
}
