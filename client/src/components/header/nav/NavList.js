import style from './NavList.module.css';
import { NavListItem } from './NavListItem';
import { userService } from '../../../services/userService';
import { useState } from 'react';

export function NavList() {
    const [isAuth, setIsAuth] = useState(userService.isUserAuthenticated());

    return (
        <nav className={style.nav}>
            <ul className={style.ul}>
                <NavListItem href={'/'} text={'Home'} />
                <NavListItem href={'/menu'} text={'Menu'} />
                <NavListItem
                    href={'/special-meals'}
                    text={'Meals'}
                    containsImg={true}
                />
                {isAuth ? (
                    <NavListItem
                        href={'/'}
                        text={'Logout'}
                        logoutStateHandler={setIsAuth}
                    />
                ) : (
                    <NavListItem href={'/login'} text={'Login'} />
                )}
            </ul>
        </nav>
    );
}
