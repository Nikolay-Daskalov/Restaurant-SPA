
const createCategory = (category, route) => ({ category, route });

export const menuCategories = [
    createCategory('Salad', 'salads'),
    createCategory('Burger', 'burgers'),
    createCategory('Pizza', 'pizza'),
    createCategory('Pasta', 'pasta'),
    createCategory('Dessert', 'desserts')
];