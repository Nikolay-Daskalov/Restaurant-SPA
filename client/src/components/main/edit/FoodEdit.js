import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useFetch } from "../../../hooks/useFetch";
import { userService } from "../../../services/userService";
import style from '../common/Login-Register.module.css';

export function FoodEdit() {

    const { id } = useParams();
    const naviagate = useNavigate();

    const valuesChangeHandler = (e) => {
        const currentTarget = e.currentTarget;
        setValues(old => ({
            ...old,
            [currentTarget.name]: currentTarget.value
        }));
    };

    const [isValidInputs, setIsValidInputs] = useState(true);

    const submitHandler = (e) => {
        const currentTarget = e.currentTarget;
        e.preventDefault();

        const formData = Object.fromEntries(new FormData(currentTarget));

        for (const key in formData) {
            if (Object.hasOwnProperty.call(formData, key)) {
                const element = formData[key];
                if (!element) {
                    setIsValidInputs(false);
                    return;
                }

                if (key === 'name') {
                    if (element.length > 30) {
                        setIsValidInputs(false);
                        return;
                    }
                }
            }
        }

        fetch(`http://localhost:8080/api/account/meals/${id}`, {
            method: 'PUT',
            mode: 'cors',
            headers: {
                'Content-type': 'application/json',
                'Authorization': `Bearer ${userService.getToken()}`
            },
            body: JSON.stringify(formData)
        })
            .then(res => {
                if (res.ok) {
                    naviagate('/account');
                }
            })
            .catch(err => console.log(err));
    };


    const food = useFetch(`http://localhost:8080/api/account/meals/${id}`, 'GET', null, true);

    const [values, setValues] = useState({
        name: '',
        imgUrl: '',
        ingredients: '',
        foodType: '',
        recipe: '',
    });

    if (food && !values.name) {
        setValues(food);
    }

    return (
        <div className={style['form-wrapper']}>
            <h2 className={style.title}>Edit Meal</h2>
            <p className={style.info}>Fieds cannot be empty</p>
            <p className={style.info}>Field length: max 30</p>
            {!isValidInputs
                ? <p className={style.err}>Invalid Credentials</p>
                : undefined}
            <form className={style.form} onSubmit={submitHandler}>
                <div className={style.container}>
                    <label className={style.label} htmlFor="name">
                        Name
                    </label>
                    <input
                        className={style.input}
                        type="text"
                        id="name"
                        name="name"
                        value={values.name}
                        onChange={valuesChangeHandler}
                    />
                </div>
                <div className={style.container}>
                    <label className={style.label} htmlFor="imgUrl">
                        Image
                    </label>
                    <input
                        className={style.input}
                        type="password"
                        id="imgUrl"
                        name="imgUrl"
                        value={values.imgUrl}
                        onChange={valuesChangeHandler}
                    />
                </div>
                <div className={style.container}>
                    <label className={style.label} htmlFor="ingredients">
                        Ingredients
                    </label>
                    <input
                        className={style.input}
                        type="text"
                        id="ingredients"
                        name="ingredients"
                        value={values.ingredients}
                        onChange={valuesChangeHandler}
                    />
                </div>
                <div className={style.container}>
                    <label className={style.label} htmlFor="type">
                        Type
                    </label>
                    <input
                        className={style.input}
                        type="text"
                        id="type"
                        name="foodType"
                        value={values.foodType}
                        onChange={valuesChangeHandler}
                    />
                </div>
                <div className={style.container}>
                    <label className={style.label} htmlFor="recipe">
                        Recipe
                    </label>
                    <input
                        className={style.input}
                        type="text"
                        id="recipe"
                        name="recipe"
                        value={values.recipe}
                        onChange={valuesChangeHandler}
                    />
                </div>
                <button className={style.submitBtn} type="submit">
                    Update
                </button>
            </form>
        </div>
    );
}