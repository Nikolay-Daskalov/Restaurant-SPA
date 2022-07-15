import style from './Main.module.css';

export function Main() {
    return (
        <main className={style.main}>
            <section>
                <h1 className={style.greetings}>Welcome!</h1>
                <button className={style.btn}>Hungry?</button>
            </section>
        </main>
    );
}