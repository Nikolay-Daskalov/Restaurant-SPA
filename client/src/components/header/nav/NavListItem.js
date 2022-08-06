import { NavLink } from 'react-router-dom';
import style from './NavListItem.module.css';
import { userService } from '../../../services/userService';

export function NavListItem(props) {
    const isSpecialMealImgPresent = () => {
        if (props.isSpecialMeal) {
            return (
                <img
                    className={style['special-img']}
                    src='https://res.cloudinary.com/dee2hxl5o/image/upload/v1659653851/Restaurant/Static/special-meals-logo.png'
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

    if (props.logoutStateHandler) {
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
            {isSpecialMealImgPresent()}
        </li>
    );
}
