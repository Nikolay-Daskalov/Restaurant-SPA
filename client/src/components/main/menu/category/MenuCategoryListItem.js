import style from './MenuCategoryListItem.module.css';
import categImg from './salad-category.jpeg';

export function MenuCategoryListItem(props) {

    return (
        <li>
            <div className={style.category}>
                <img
                    className={style.img}
                    src={categImg}
                    alt={`${props.category} category`}
                />
            </div>
        </li>
    );
}