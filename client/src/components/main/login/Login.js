import { useContext } from 'react';
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../../../contexts/AuthContext';
import { userService } from '../../../services/userService';
import style from '../common/Login-Register.module.css';

export function Login() {
    const navigate = useNavigate();

    const user = useContext(AuthContext);

    const [loginData, setLoginData] = useState({
        username: '',
        password: '',
    });

    const [loginError, setLoginError] = useState(false);
    const [serverDown, setServerDown] = useState(false);
    const [invalidInput, setInvalidInput] = useState(false);

    const submitHandler = (e) => {
        e.preventDefault();

        const { username, password } = Object.fromEntries(
            new FormData(e.target)
        );

        if (!username || !password) {
            setInvalidInput(true);
            return;
        }

        if (username.length > 30 || password.length > 30) {
            setInvalidInput(true);
            return
        }

        const url = 'http://localhost:8080/api/users/login';
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            body: new URLSearchParams({ username, password })
        })
            .then(res => {
                setInvalidInput(false);
                setLoginError(false);
                if (res.status === 401) {
                    throw new Error('Wrong Credentials');
                }

                return res.json();
            })
            .then(data => {
                const accessToken = data.accessToken;
                const username = data.username;
                userService.setToken(accessToken);
                userService.setUsername(username);
                user.setIsAuth();
                user.setUsername(data.username);
                navigate('/');
            })
            .catch(err => {
                if (err.name === 'Error') {
                    setLoginError(true);
                    return;
                }

                setServerDown(true);
            });
    };


    const updateDataHandler = (e) => {
        const currentTarget = e.currentTarget;
        setLoginData((oldData) => ({
            ...oldData,
            [currentTarget.name]: currentTarget.value,
        }));
    };

    return (
        <div className={style['form-wrapper']}>
            <h2 className={style.title}>Login</h2>
            <p className={style.info}>Fieds cannot be empty</p>
            <p className={style.info}>Field length: max 30</p>
            {loginError
                ? <p className={style.err}>Wrong Credentials</p>
                : undefined}
            {invalidInput
                ? <p className={style.err}>Invalid Credentials</p>
                : undefined}
            {serverDown
                ? <p className={style.err}>Server not responding</p>
                : undefined}
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
            <p className={style['register-login']}>
                <Link className={style['register-login-link']} to="/register">
                    Register
                </Link>
            </p>
        </div>
    );
}
