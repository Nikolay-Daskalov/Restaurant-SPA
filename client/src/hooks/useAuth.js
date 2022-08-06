import { useState } from 'react';
import { userService } from '../services/userService';

export function useAuth() {
    const [isAuth, setIsAuth] = useState(userService.isUserAuthenticated());

    return [
        isAuth,
        () => {
            setIsAuth(userService.isUserAuthenticated());
        }
    ];
}