import { NavLink } from 'react-router-dom';
import style from './NavListItem.module.css';
import specialMeals from './special-logo.png';
import { userService } from '../../../services/userService';

export function NavListItem(props) {
    const isImgItemPresent = () => {
        if (props.containsImg) {
            return (
                <img
                    className={style['special-img']}
                    src={specialMeals}
                    alt="Special Meals"
                />
            );
        }
    };

    const isActiveHandler = ({ isActive }) => {
        let classes = `${style.a}`;
        if (isActive) {
            classes += ` ${style['a-active']}`;
        }

        return classes;
    };

    const logoutHandler = () => {
        userService.deleteToken();
        props.logoutStateHandler(userService.isUserAuthenticated());
    };

    if (props?.logoutStateHandler) {
        return (
            <li className={style.li}>
                <NavLink
                    className={style.a}
                    to={props.href}
                    onClick={logoutHandler}
                >
                    {props.text}
                </NavLink>
            </li>
        );
    }

    return (
        <li className={style.li}>
            <NavLink className={isActiveHandler} to={props.href}>
                {props.text}
            </NavLink>
            {isImgItemPresent()}
        </li>
    );
}
