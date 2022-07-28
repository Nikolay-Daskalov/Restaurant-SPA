import { useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../common/Login-Register.module.css';

export function Register() {
    const submitHandler = (e) => {
        e.preventDefault();

        const {
            username,
            password,
            // eslint-disable-next-line
            ['repeat-password']: repeatPassword,
        } = Object.fromEntries(new FormData(e.currentTarget));

        console.log(username, password, repeatPassword);
    };

    const [registerData, setRegisterData] = useState({
        username: '',
        password: '',
        repeatPassword: '',
    });

    const updateDataHandler = (e) => {
        const currentTarget = e.currentTarget;
        setRegisterData((oldData) => {
            let name = currentTarget.name;
            if (currentTarget.name.includes('-')) {
                name = 'repeatPassword';
            }

            return {
                ...oldData,
                [name]: currentTarget.value,
            };
        });
    };
    return (
        <div className={style['form-wrapper']}>
            <h2 className={style.title}>Register</h2>
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
                        value={registerData.username}
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
                        value={registerData.password}
                        onChange={updateDataHandler}
                    />
                </div>
                <div className={style.container}>
                    <label
                        className={style.label}
                        htmlFor="repeat-password"
                    >
                        Repeat password
                    </label>
                    <input
                        className={style.input}
                        type="password"
                        id="repeat-password"
                        name="repeat-password"
                        value={registerData.repeatPassword}
                        onChange={updateDataHandler}
                    />
                </div>
                <button className={style.submitBtn} type="submit">
                    Register
                </button>
            </form>
            <p className={style['register-login']}>
                <Link className={style['register-login-link']} to="/login">
                    Login
                </Link>
            </p>
        </div>
    );
}
