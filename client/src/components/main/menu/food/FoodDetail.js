import { useParams } from "react-router-dom";
import { useFetch } from "../../../../hooks/useFetch";
import style from './FoodDetail.module.css';
import { Rating } from "./Rating";

export function FoodDetail() {

    const { category, id } = useParams();

    // const url = `http://localhost:8080/api/food/${category}/${id}`;
    // const httpMethod = 'GET';
    // const food = useFetch(url, httpMethod);

    // if (food === null) {
    //     return (
    //         <h1 className={style.loading}>...Loading</h1>
    //     );
    // }

    return (
        <div className={style.container}>
            <img className={style.img} src="https://res.cloudinary.com/dee2hxl5o/image/upload/v1659715745/Restaurant/Menu/Meal/Salad/green-salad.jpg" alt="" />
            <h1 className={style.title}>Shopska salad</h1>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Ingredients:</h2>
                <ul className={style.ingredientsHolder}>
                    <li>tomatoes - 2 pieces</li>
                    <li>vinegar</li>
                    <li>salt</li>
                    <li>iceburg lettuce</li>
                </ul>
            </div>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Recipe:</h2>
                <p className={style.recipe}>Lorem ipsum dolor sit amet consectetur adipisicing elit. Necessitatibus corrupti doloremque qui, itaque odio sit facilis dicta error magnam ab minus quasi sapiente iure, perferendis reprehenderit omnis molestias ea excepturi?</p>
            </div>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Rating:</h2>
                <ul className={style.ratingHolder}>
                    <Rating isFilled />
                    <Rating />
                    <Rating />
                    <Rating />
                    <Rating />
                </ul>
            </div>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Add Review?</h2>
                <form>
                    <div className={style.inputContainer}>
                        <input type="radio" id="one" defaultValue="1" name="stars" />
                        <label htmlFor="one">
                            <ul className={style.reviewList}>
                                <Rating isFilled />
                            </ul>
                        </label>
                    </div>
                    <div className={style.inputContainer}>
                        <input type="radio" id="two" defaultValue="2" name="stars" />
                        <label htmlFor="two">
                            <ul className={style.reviewList}>
                                <Rating isFilled />
                                <Rating isFilled />
                            </ul>
                        </label>
                    </div>
                    <div className={style.inputContainer}>
                        <input type="radio" id="three" defaultValue="3" name="stars" />
                        <label htmlFor="three">
                            <ul className={style.reviewList}>
                                <Rating isFilled />
                                <Rating isFilled />
                                <Rating isFilled />
                            </ul>
                        </label>
                    </div>
                    <div className={style.inputContainer}>
                        <input type="radio" id="four" defaultValue="4" name="stars" />
                        <label htmlFor="four">
                            <ul className={style.reviewList}>
                                <Rating isFilled />
                                <Rating isFilled />
                                <Rating isFilled />
                                <Rating isFilled />
                            </ul>
                        </label>
                    </div>
                    <div className={style.inputContainer}>
                        <input type="radio" id="five" defaultValue="5" name="stars" />
                        <label htmlFor="five">
                            <ul className={style.reviewList}>
                                <Rating isFilled />
                                <Rating isFilled />
                                <Rating isFilled />
                                <Rating isFilled />
                                <Rating isFilled />
                            </ul>
                        </label>
                    </div>
                    <button className={style.submitBtn} type="submit">Review</button>
                </form>
            </div>
        </div >
    );
}