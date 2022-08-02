import { Link } from 'react-router-dom';
import style from './MenuCategoryListItem.module.css';

export function MenuCategoryItem(props) {
    const imgUrl = `https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Category/${props.category.category}-Category.jpg`;

    return (
        <li className={style.category}>
            <Link to={`/menu/${props.category.route}`}>
                <img
                    className={style.img}
                    src={imgUrl}
                    alt={`${props.category.category} category`}
                />
            </Link>
        </li>
    );
}
