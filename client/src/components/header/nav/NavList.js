import style from './NavList.module.css';
import { NavListItem } from './NavListItem';
import { useContext } from 'react';
import { AuthContext } from '../../../contexts/AuthContext';

export function NavList() {

    const user = useContext(AuthContext);

    return (
        <nav className={style.nav}>
            <ul className={style.ul}>
                <NavListItem href={'/'} text={'Home'} />
                <NavListItem href={'/menu'} text={'Menu'} />
                {/* <NavListItem
                    href={'/special-meals'}
                    text={'Meals'}
                    isSpecialMeal
                /> */}
                {user.isAuth ? (
                    <>
                        <NavListItem
                            href={'/account'}
                            text={'Profile'}
                        />
                    </>
                ) : (
                    <NavListItem href={'/login'} text={'Login'} />
                )}
            </ul>
        </nav>
    );
}
