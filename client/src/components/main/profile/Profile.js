import { useContext } from "react";
import { AuthContext } from "../../../contexts/AuthContext";
import { Link } from 'react-router-dom';
import style from './Profile.module.css';
import { userService } from "../../../services/userService";

export function Profile() {
    const user = useContext(AuthContext);

    return (
        <div className={style.container}>
            <h1 className={style.heading}>Hi, {user.username}</h1>
            <ul className={style.linkContainer}>
                <li>
                    <button className={`${style.link} ${style.linkBlue}`} type="button">Get all Meals</button>
                </li>
                <li>
                    <button className={`${style.link} ${style.linkBlue}`} type="button">Create Meal</button>
                </li>
                <li>
                    <Link to='/' className={`${style.link} ${style.linkRed}`}
                        onClick={() => {
                            userService.deleteToken();
                            userService.deleteUsername();
                            user.setIsAuth();
                            user.setUsername();
                        }}
                    >
                        Logout
                    </Link>
                </li>
            </ul>
        </div>
    );
}