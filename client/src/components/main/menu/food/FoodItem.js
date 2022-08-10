import { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { userService } from "../../../../services/userService";
import style from './FoodItem.module.css';
import { Rating } from "./Rating";

export function FoodItem(props) {

    const location = useLocation();
    const [isDeleted, setIsDeleted] = useState(false);

    const buildRating = () => {
        if (props.rating.stars === 0) {
            return (
                <>
                    <Rating />
                    <Rating />
                    <Rating />
                    <Rating />
                    <Rating />
                </>
            );
        }

        let rating = [];
        for (let i = 1; i <= 5; i++) {
            if (props.rating.stars >= i) {
                rating.push(
                    <Rating key={i} isFilled />
                );
            } else {
                rating.push(
                    <Rating key={i} />
                );
            }
        }

        return rating;
    }

    return (
        <li>
            <div className={style.card}>
                <img className={style.card__img} src={props.imgUrl} alt={`${props.name}`} />
                <h3 className={style.card__title}>{props.name.split(' ')[0]}</h3>
                <ul className={style.card__rating__container}>
                    {buildRating()}
                </ul>
                <span className={style.peopleCount}>{'(' + props.rating.peopleCount + ')'}</span>
                <div className={style.card__link__container}>
                    {!props.isAuthor
                        ? <Link
                            className={style.card__link}
                            to={`${location.pathname}/${props.id}`}
                        >
                            Details
                        </Link>
                        : undefined
                    }
                    {props.isAuthor
                        ? isDeleted
                            ? (
                                <button
                                    type="button"
                                    className={`${style.card__link} ${style['card__link--delete']}`}
                                >
                                    Deleted
                                </button>
                            )
                            : (
                                <>
                                    <Link
                                        className={`${style.card__link} ${style['card__link--edit']}`}
                                        to={`${location.pathname}/meal/${props.id}/edit`}
                                    >
                                        Edit
                                    </Link>
                                    <button
                                        type="button"
                                        className={`${style.card__link} ${style['card__link--delete']}`}
                                        onClick={() => {
                                            fetch(`http://localhost:8080/api/account/meals/${props.id}`, {
                                                method: 'DELETE',
                                                mode: 'cors',
                                                headers: {
                                                    'Content-type': 'application/json',
                                                    'Authorization': `Bearer ${userService.getToken()}`
                                                }
                                            })
                                                .then(res => {
                                                    if (res.ok) {
                                                        setIsDeleted(true);
                                                    }
                                                })
                                                .catch(err => console.log(err));
                                        }}
                                    >
                                        Delete
                                    </button>
                                </>
                            )
                        : undefined
                    }
                    {/* {props.isAuthor
                        ? <Link
                            className={`${style.card__link} ${style['card__link--edit']}`}
                            to={`${location.pathname}/meal/${props.id}/edit`}
                        >
                            Edit
                        </Link>
                        : undefined
                    }
                    {props.isAuthor
                        ? <button
                            type="button"
                            className={`${style.card__link} ${style['card__link--delete']}`}
                            onClick={(e) => {
                                const currentTarget = e.currentTarget;
                                fetch(`http://localhost:8080/api/account/meals/${props.id}`, {
                                    method: 'DELETE',
                                    mode: 'cors',
                                    headers: {
                                        'Content-type': 'application/json',
                                        'Authorization': `Bearer ${userService.getToken()}`
                                    }
                                })
                                    .then(res => {
                                        if (res.ok) {
                                            currentTarget.parentElement.innerHTML = <p>Deleted</p>;
                                        }
                                    })
                                    .catch(err => console.log(err));
                            }}
                        >
                            Delete
                        </button>
                        : undefined
                    } */}
                </div>
            </div>
        </li >
    );
}