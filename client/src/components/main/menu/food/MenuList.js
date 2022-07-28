import { useEffect } from "react";
import { MenuListItem } from "./MenuListItem";

export function MenuList() {

    useEffect(() => {
        //TODO fetch recepies
    }, []);

    return (
        <ul>
            <MenuListItem />
        </ul>
    );
}