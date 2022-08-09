import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import style from '../common/Login-Register.module.css';

export function Register() {

    const navigate = useNavigate();

    const [registerError, setRegisterError] = useState(false);
    const [serverDown, setServerDown] = useState(false);
    const [invalidInput, setInvalidInput] = useState(false);
    const [userExist, setUserExist] = useState(false);

    const [registerData, setRegisterData] = useState({
        username: '',
        password: '',
        repeatPassword: '',
    });

    const submitHandler = (e) => {
        e.preventDefault();

        const {
            username,
            password,
            // eslint-disable-next-line
            ['repeat-password']: repeatPassword,
        } = Object.fromEntries(new FormData(e.currentTarget));

        if (!username || !password || !repeatPassword) {
            setInvalidInput(true);
            return;
        }

        if (password !== repeatPassword) {
            setInvalidInput(true);
            return;
        }

        if (username.length > 30 ||
            password.length > 30 ||
            repeatPassword.length > 30) {
            setInvalidInput(true);
            return;
        }

        const url = 'http://localhost:8080/api/users/register';
        fetch(url, {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({ username, password, repeatPassword })
        })
            .then(res => {
                setRegisterError(false);
                setServerDown(false);
                setUserExist(false);
                if (res.status === 409) {
                    setUserExist(true);
                    return;
                }
                if (!res.ok) {
                    setRegisterError(true);
                    return;
                }

                navigate('/login');
            })
            .catch(err => setServerDown(true));
    };

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
            <p className={style.info}>Fieds cannot be empty</p>
            <p className={style.info}>Field length: max 30</p>
            {registerError
                ? <p className={style.err}>Wrong Credentials</p>
                : undefined}
            {invalidInput
                ? <p className={style.err}>Invalid Credentials</p>
                : undefined}
            {serverDown
                ? <p className={style.err}>Server not responding</p>
                : undefined}
            {userExist
                ? <p className={style.err}>Username exists</p>
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
