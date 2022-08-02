import { MenuCategoryItem } from './MenuCategoryListItem';
import style from './MenuCategoryList.module.css';

const createCategory = (category, route) => ({ category, route });

const menuCategories = [
    createCategory('Salad', 'salads'),
    createCategory('Burger', 'burgers'),
    createCategory('Pizza', 'pizza'),
    createCategory('Pasta', 'pasta'),
    createCategory('Soup', 'soup'),
    createCategory('Dessert', 'desserts')
];

export function MenuCategoryList() {
    return (
        <ul className={style.container}>
            {menuCategories.map((categ, index) => <MenuCategoryItem key={index} category={categ} />)}
        </ul>
    );
}
