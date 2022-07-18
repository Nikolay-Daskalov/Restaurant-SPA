import style from './NavList.module.css';
import { NavListItem } from './NavListItem';

export function NavList() {
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
                <NavListItem href={'/login'} text={'Login'} />
            </ul>
        </nav>
    );
}
