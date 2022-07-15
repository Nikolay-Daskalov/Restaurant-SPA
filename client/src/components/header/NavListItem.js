export function NavListItem(props) {
    return (
        <li className={props.classNameLi}>
            <a className={props.classNameA} href="/users/login">Login</a>
        </li>
    );
}