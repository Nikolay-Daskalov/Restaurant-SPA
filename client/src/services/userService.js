function isUserAuthenticated() {
    const user = localStorage.getItem('access_token');
    return user ? true : false;
}

function setToken(accessToken) {
    localStorage.setItem('access_token', accessToken);
}

function deleteToken() {
    localStorage.removeItem('access_token');
}

export const userService = {
    isUserAuthenticated,
    setToken,
    deleteToken,
};
