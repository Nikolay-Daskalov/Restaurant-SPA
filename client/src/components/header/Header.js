import style from './Header.module.css';
import { NavList } from './nav/NavList';

export function Header() {
    return (
        <header className={style.header}>
            <img
                className={style['header-img']}
                src='https://res.cloudinary.com/dee2hxl5o/image/upload/v1659653877/Restaurant/Static/restaurant-logo.jpg'
                alt="Restaurant Logo"
            />
            <NavList />
        </header>
    );
}
