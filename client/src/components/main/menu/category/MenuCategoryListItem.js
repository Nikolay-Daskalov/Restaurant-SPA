import { Link } from 'react-router-dom';
import style from './MenuCategoryListItem.module.css';

export function MenuCategoryListItem(props) {
    const imgUrl = `https://res.cloudinary.com/dee2hxl5o/image/upload/v1659226346/Restaurant/Menu/Category/${props.category.singular}-Category.jpg`;

    return (
        <li className={style.category}>
            <Link to={`/menu/${props.category.plural}`}>
                <img
                    className={style.img}
                    src={imgUrl}
                    alt={`${props.category.singular} category`}
                />
            </Link>
        </li>
    );
}
