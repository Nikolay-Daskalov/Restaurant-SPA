import { useEffect } from 'react';
import { useMemo } from 'react';
import { useState } from 'react';
import { userService } from '../services/userService';

export const useFetch = (url, method, payload, authenticated) => {

    const fetchOptions = useMemo(() => ({ method }), [method]);

    if (payload) {
        fetchOptions.body = JSON.stringify(payload);
    }

    if (authenticated) {
        fetchOptions.headers = {
            "Authorization": `Bearer ${userService.getToken()}`
        }
    }

    const [data, setData] = useState(null);

    useEffect(() => {
        fetch(url, fetchOptions)
            .then(res => res.json())
            .then(data => {
                setData(data);
            })
            .catch(err => console.error(err));
    }, [url, fetchOptions]);

    return data;
}