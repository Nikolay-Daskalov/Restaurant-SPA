import { Route, Routes } from "react-router-dom";
import { MenuCategoryList } from "./category/MenuCaterogyList";
import { MenuList } from "./food/MenuList";

export function MenuContainer() {
    return (
        <Routes>
            <Route path='/' element={<MenuCategoryList />} />
            <Route path="/:category" element={<MenuList />} />
        </Routes >
    );
}