import { Link } from "react-router-dom";
import style from './FoodItem.module.css';
import { Rating } from "./Rating";

const addClassesToLinks = (currentLink) => {
    let linkStyle = `${style.card__link}`;

    switch (currentLink) {
        case 'details':
            linkStyle += ` ${style['card__link--details']}`;
            break;
        case 'edit':
            linkStyle += ` ${style['card__link--edit']}`;
            break;
        case 'delete':
            linkStyle += ` ${style['card__link--delete']}`;
            break;
        default:
            break;
    }

    return linkStyle;
}

const createImgUrl = (salad) => {
    return `https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Meal/${salad.toLowerCase()}-salata.jpg`;
};

export function FoodItem(props) {//TODO: add props from fetch parent
    return (
        <li>
            <div className={style.card}>
                <img className={style.card__img} src={createImgUrl(props.salad)} alt={`${props.salad} salata`} />
                <h3 className={style.card__title}>Title</h3>
                <div className={style.card__rating__container}>
                    <Rating isFilled />
                    <Rating isFilled />
                    <Rating isFilled />
                    <Rating />
                    <Rating />
                </div>
                <div className={style.card__link__container}>
                    <Link className={addClassesToLinks('details')} to='/'>Details</Link>
                    <Link className={addClassesToLinks('edit')} to='/'>Edit</Link>
                    <Link className={addClassesToLinks('delete')} to='/'>Delete</Link>
                </div>
            </div>
        </li >
    );
}