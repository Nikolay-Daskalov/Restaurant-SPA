import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useFetch } from "../../../../hooks/useFetch";
import { userService } from "../../../../services/userService";
import style from './FoodDetail.module.css';
import { Rating } from "./Rating";

export function FoodDetail() {

    const navigate = useNavigate();
    const { category, id } = useParams();
    const [isReviewed, setIsReviewed] = useState(false);
    const [alreadyReviewed, setAlreadyReviewed] = useState(false);

    const url = `http://localhost:8080/api/food/${category}/${id}`;
    const httpMethod = 'GET';
    const food = useFetch(url, httpMethod);

    if (food === null) {
        return (
            <h1 className={style.loading}>...Loading</h1>
        );
    }

    const renderRating = (rating) => {
        const stars = rating.stars;
        const ratings = [];
        for (let i = 1; i <= 5; i++) {
            if (stars >= i) {
                ratings.push(<Rating key={i} isFilled />);
                continue;
            }

            ratings.push(<Rating key={i} />);
        }

        return ratings;
    };

    const submitHandler = (e) => {
        const currentTarget = e.currentTarget;
        e.preventDefault();

        const stars = Object.fromEntries(new FormData(currentTarget));
        const url = `http://localhost:8080/api/food/${category}/${id}/ratings`;

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
                'Authorization': `Bearer ${userService.getToken()}`
            },
            body: JSON.stringify(stars)
        })
            .then(res => {
                if (res.status === 403) {
                    userService.deleteToken();
                    navigate('/login');
                }

                if (res.status === 409) {
                    setAlreadyReviewed(true);
                }

                setIsReviewed(true);
            })
            .catch(err => console.log(err));
    };

    const showReviews = () => {
        if (!isReviewed) {
            return (
                <div className={style.detailsSection}>
                    <h2 className={style.detailsHeading}>Add Review?</h2>
                    <form onSubmit={submitHandler}>
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
                            <input type="radio" id="three" defaultValue="3" name="stars" defaultChecked />
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
                        <button onClick={(e) => e.currentTarget.textContent = '...Submiting'} className={style.submitBtn} type="submit">Review</button>
                    </form>
                </div>
            );
        } else if (alreadyReviewed) {
            return (
                <p className={style.reviewSend}>Already reviewed!</p>
            );
        } else {
            return (
                <p className={style.reviewSend}>Thank you!</p>
            );
        }
    }

    return (
        <div className={style.container}>
            <img className={style.img} src={food.imgUrl} alt={food.name} />
            <h1 className={style.title}>{food.name}</h1>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Ingredients:</h2>
                <ul className={style.ingredientsHolder}>
                    {food.ingredients.map((ingredient, i) => <li key={i}>{ingredient}</li>)}
                </ul>
            </div>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Recipe:</h2>
                <p className={style.recipe}>{food.recipe}</p>
            </div>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Author:</h2>
                <p className={style.author}>{food.author}</p>
            </div>
            <div className={style.detailsSection}>
                <h2 className={style.detailsHeading}>Rating:</h2>
                <ul className={style.ratingHolder}>
                    {renderRating(food.rating)}
                </ul>
                <span className={style.peopleCount}>{'(' + food.rating.peopleCount + ')'}</span>
            </div>
            {showReviews()}
        </div >
    );
}