import { useContext } from "react";
import { AuthContext } from "../../../contexts/AuthContext";
import { Link } from 'react-router-dom';
import style from './Profile.module.css';
import { userService } from "../../../services/userService";
import { useState } from "react";
import { FoodItem } from "../menu/food/FoodItem";

export function Profile() {
    const user = useContext(AuthContext);

    const [showMeals, setShowMeals] = useState(false);
    const [mealsData, setMealsData] = useState([]);

    const getAllMealsHandler = () => {
        const url = 'http://localhost:8080/api/account/meals';
        fetch(url, {
            mode: 'cors',
            headers: {
                'Content-type': 'application/json',
                'Authorization': `Bearer ${userService.getToken()}`
            }
        })
            .then(res => {
                setShowMeals(true);
                return res.json();
            })
            .then(data => {
                setMealsData(data);
            })
            .catch(err => console.log(err));
    };

    return (
        <div className={style.container}>
            <h1 className={style.heading}>Hi, {user.username}</h1>
            <ul className={style.linkContainer}>
                <li>
                    <button onClick={getAllMealsHandler} className={`${style.link} ${style.linkBlue}`} type="button">Get all Meals</button>
                </li>
                <li>
                    <Link to='/account/meal/create' className={`${style.link} ${style.linkBlue}`}>Create Meal</Link>
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
            <div className={style.mealsContainer}>
                {showMeals
                    ? mealsData.length === 0
                        ? <h1 className={style.loading}>No meals</h1>
                        : (
                            <ul className={style.listMeals}>
                                {
                                    mealsData.map((meal) => (
                                        <FoodItem
                                            key={meal.id} id={meal.id}
                                            name={meal.name}
                                            imgUrl={meal.imgUrl}
                                            rating={meal.rating}
                                            isAuthor
                                        />
                                    ))
                                }
                            </ul>
                        )
                    : undefined}
            </div>
        </div>
    );
}