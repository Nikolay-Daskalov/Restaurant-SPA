import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

export function PrivateGuard({ children }) {
    const user = useContext(AuthContext);

    if (!user.isAuth) {
        return <Navigate to={'/login'} replace />;
    }

    return (
        <>
            {children}
        </>
    );
}