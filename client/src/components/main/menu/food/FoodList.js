import { useParams, Navigate } from 'react-router-dom';
import { useFetch } from "../../../../hooks/useFetch";
import { FoodItem } from "./FoodItem";
import { menuCategories } from '../../../../util/menuCategory';
import style from './FoodList.module.css';

export function FoodList() {

    const { category } = useParams();

    let isCategoryValid = false;
    for (const categ of menuCategories) {
        if (categ.route === category) {
            isCategoryValid = true;
            break;
        }
    }

    const url = `http://localhost:8080/api/food/${category}`;
    const httpMethod = 'GET';

    const food = useFetch(url, httpMethod);

    if (!isCategoryValid) {
        return <Navigate to='/error' />
    }

    if (food === null) {
        return (
            <h1 className={style.loading}>...Loading</h1>
        );
    }

    if (food.length === 0) {
        return (
            <h1 className={style.loading}>No Meals</h1>
        );
    }

    return (
        <ul className={style.container}>
            {food.map((item, i) =>
                <FoodItem
                    key={i} id={item.id}
                    name={item.name}
                    imgUrl={item.imgUrl}
                    rating={item.rating}
                />)
            }
        </ul>
    );
}