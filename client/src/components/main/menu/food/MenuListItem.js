import { Link } from "react-router-dom";

export function MenuListItem() {
    return (
        <div className="card">
            <img src="" alt="" />
            <h3>Title</h3>
            <p>Some text</p>
            <Link to='/'>Details</Link>
            <Link to='/'>Edit</Link>
            <Link to='/'>Delete</Link>
        </div>
    );
}