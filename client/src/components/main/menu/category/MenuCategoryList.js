import { MenuCategoryListItem } from './MenuCategoryListItem';
import style from './MenuCategoryList.module.css';

const createCategory = (singular, plural) => ({ singular, plural });

const menuCategories = {
    BBQ: createCategory('BBQ', 'barbeque'),
    burger: createCategory('Burger', 'burgers'),
    chicken: createCategory('Chicken', 'chicken'),
    dessert: createCategory('Dessert', 'desserts'),
    pasta: createCategory('Pasta', 'pasta'),
    salad: createCategory('Salad', 'salads'),
    soup: createCategory('Soup', 'soup'),
};

export function MenuCategoryList() {
    return (
        <ul className={style.container}>
            <MenuCategoryListItem category={menuCategories.salad} />
            <MenuCategoryListItem category={menuCategories.burger} />
            <MenuCategoryListItem category={menuCategories.chicken} />
            <MenuCategoryListItem category={menuCategories.BBQ} />
            <MenuCategoryListItem category={menuCategories.pasta} />
            <MenuCategoryListItem category={menuCategories.soup} />
            <MenuCategoryListItem category={menuCategories.dessert} />
        </ul>
    );
}
