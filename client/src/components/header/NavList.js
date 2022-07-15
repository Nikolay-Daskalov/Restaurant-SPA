import style from './NavList.module.css';
import { NavListItem } from './NavListItem';
import specialMeals from './special-logo-cropped.png';

export function NavList() {
    return (
        <nav className={style.nav}>
            <ul className={style.ul}>
                <li className={style.li}>
                    <a className={style.a} href='/'>Home</a>
                </li>
                <li className={style.li}>
                    <a className={style.a} href='/menu'>Menu</a>
                </li>
                <li className={style.li}>
                    <a className={style.a} href='/special-meals'>Meals</a>
                    <img
                        className={style['special-img']}
                        src={specialMeals}
                        alt='Special Meals'
                    />
                </li>
                <NavListItem classNameLi={style.li} classNameA={style.a} />
            </ul>
        </nav >
    );
}