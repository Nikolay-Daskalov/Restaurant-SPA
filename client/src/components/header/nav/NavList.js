import style from './NavList.module.css';
import { NavListItem } from './NavListItem';
import { useAuth } from '../../../hooks/useAuth';

export function NavList() {
    const [isAuth, setIsAuth] = useAuth();

    return (
        <nav className={style.nav}>
            <ul className={style.ul}>
                <NavListItem href={'/'} text={'Home'} />
                <NavListItem href={'/menu'} text={'Menu'} />
                <NavListItem
                    href={'/special-meals'}
                    text={'Meals'}
                    isSpecialMeal
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
