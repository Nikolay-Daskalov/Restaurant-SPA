import logo from './restaurant-logo.jpg';
import style from './Header.module.css';
import { NavList } from './nav/NavList';

export function Header() {
    return (
        <header className={style.header}>
            <img
                className={style['header-img']}
                src={logo}
                alt="Restaurant Logo"
            />
            <NavList />
        </header>
    );
}
