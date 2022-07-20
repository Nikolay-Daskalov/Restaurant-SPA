import { useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../common/Login-Register.module.css';

export function Login() {
    const submitHandler = (e) => {
        e.preventDefault();

        const { username, password } = Object.fromEntries(
            new FormData(e.target)
        );

        console.log(username, password);
    };

    const [loginData, setLoginData] = useState({
        username: '',
        password: '',
    });

    const updateDataHandler = (e) => {
        const currentTarget = e.currentTarget;
        setLoginData((oldData) => ({
            ...oldData,
            [currentTarget.name]: currentTarget.value,
        }));
    };

    return (
        <section>
            <div className={style['form-wrapper']}>
                <h2 className={style.title}>Login</h2>
                <form className={style.form} onSubmit={submitHandler}>
                    <div className={style.container}>
                        <label className={style.label} htmlFor="username">
                            Username
                        </label>
                        <input
                            className={style.input}
                            type="text"
                            id="username"
                            name="username"
                            value={loginData.username}
                            onChange={updateDataHandler}
                        />
                    </div>
                    <div className={style.container}>
                        <label className={style.label} htmlFor="password">
                            Password
                        </label>
                        <input
                            className={style.input}
                            type="password"
                            id="password"
                            name="password"
                            value={loginData.password}
                            onChange={updateDataHandler}
                        />
                    </div>
                    <button className={style.submitBtn} type="submit">
                        Login
                    </button>
                </form>
                <p className={style.register}>
                    <Link className={style['register-link']} to="/register">
                        Register
                    </Link>
                </p>
            </div>
        </section>
    );
}
