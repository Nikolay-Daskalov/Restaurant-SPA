import { Fragment, useContext } from 'react';
import { Link } from 'react-router-dom';
import style from './Home.module.css';
import { AuthContext } from '../../../contexts/AuthContext';

export function Home() {

    const user = useContext(AuthContext);

    return (
        <Fragment>
            <h1 className={style.greetings}>Welcome{user.username ? `, ${user.username}` : ''}!</h1>
            <Link to={'/menu'} className={style.btn}>
                Hungry?
            </Link>
        </Fragment>
    );
}
