import { MenuCategoryItem } from './MenuCategoryListItem';
import { menuCategories } from '../../../../util/menuCategory';
import style from './MenuCategoryList.module.css';

export function MenuCategoryList() {
    return (
        <ul className={style.container}>
            {menuCategories.map((categ, index) => <MenuCategoryItem key={index} category={categ} />)}
        </ul>
    );
}
