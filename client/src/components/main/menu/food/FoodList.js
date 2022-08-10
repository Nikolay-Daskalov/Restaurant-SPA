import { useParams } from 'react-router-dom';
import { useFetch } from "../../../../hooks/useFetch";
import { FoodItem } from "./FoodItem";
import style from './FoodList.module.css';

export function FoodList() {

    const { category } = useParams();

    const url = `http://localhost:8080/api/food/${category}`;
    const httpMethod = 'GET';

    const food = useFetch(url, httpMethod);

    if (food === null) {
        return (
            <h1 className={style.loading}>...Loading</h1>
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