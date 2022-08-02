import { useEffect } from "react";
import { useParams } from 'react-router-dom';
import { FoodItem } from "./FoodItem";
import style from './FoodList.module.css';

export function FoodList() {

    const { category } = useParams();

    useEffect(() => {
        //TODO fetch recepies
    }, []);

    return (
        <ul className={style.container}>
            <FoodItem salad='Shopska' />
            <FoodItem salad='Frenska' />
            <FoodItem salad='Cesar' />
            <FoodItem salad='Ovcharska' />
        </ul>
    );
}