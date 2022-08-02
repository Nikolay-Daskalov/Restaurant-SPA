import style from './Rating.module.css';

export function Rating(props) {

    const imgUrl = (isFilled) => {
        return `https://res.cloudinary.com/dee2hxl5o/image/upload/Restaurant/Menu/Rating/star-${isFilled ? 'filled' : 'empty'}.png`;
    }

    return (
        <span className={style.rating__holder}>
            <img className={style.rating__img} src={imgUrl(props.isFilled)} alt="" />
        </span>
    );
}