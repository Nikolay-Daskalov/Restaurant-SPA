import { createContext } from "react";
import { useAuth } from "../hooks/useAuth";

export const AuthContext = createContext({ msg: 'Out of Context' });

export const AuthProvider = ({ children }) => {
    const [isAuth, setIsAuth, username, setUsername] = useAuth();

    return (
        <AuthContext.Provider
            value={{ isAuth, setIsAuth, username, setUsername }}
        >
            {children}
        </AuthContext.Provider>
    );
}