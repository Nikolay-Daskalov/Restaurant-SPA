import style from './Footer.module.css';

export function Footer() {
    return (
        <footer className={style.footer}>
            <div className={style.content}>
                <span className={style.text}>Created by Nikolay Daskalov</span>
                <a href="https://www.youtube.com/watch?v=lV3guoDL1x0"
                    target="_blank"
                    rel="noopener noreferrer"
                    className={style.link}>GitHub</a>
            </div>
        </footer>
    );
}