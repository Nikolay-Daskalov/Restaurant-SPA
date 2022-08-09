import { useState } from 'react';
import { userService } from '../services/userService';

export function useAuth() {
    const [isAuth, setIsAuth] = useState(userService.isUserAuthenticated());
    const [username, setUsername] = useState(userService.getUsername());

    return [
        isAuth,
        () => {
            setIsAuth(userService.isUserAuthenticated());
        },
        username,
        () => {
            setUsername(userService.getUsername());
        }
    ];
}