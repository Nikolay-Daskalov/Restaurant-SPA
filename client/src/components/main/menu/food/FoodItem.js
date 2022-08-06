import { Link, useLocation } from "react-router-dom";
import style from './FoodItem.module.css';
import { Rating } from "./Rating";

export function FoodItem(props) {

    const location = useLocation();

    const buildRating = () => {
        if (props.rating === 0) {
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
            if (props.rating >= i) {
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
                <div className={style.card__link__container}>
                    <Link className={style.card__link} to={`${location.pathname}/${props.id}`}>Details</Link>
                </div>
            </div>
        </li >
    );
}