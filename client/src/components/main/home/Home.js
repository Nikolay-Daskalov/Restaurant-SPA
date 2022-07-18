import { Link } from 'react-router-dom';
import style from './Home.module.css';

export function Home() {
    return (
        <section>
            <h1 className={style.greetings}>Welcome!</h1>
            <Link to={'/menu'} className={style.btn}>
                Hungry?
            </Link>
        </section>
    );
}
