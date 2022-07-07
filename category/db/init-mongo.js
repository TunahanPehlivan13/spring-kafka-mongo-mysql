db.createUser({
    user: "root",
    pwd: "pass",
    roles: [{
        role: "readWrite",
        db: "category"
    }]
});

db.category.insertMany([
    {
        categoryId: 1,
        name: 'Konut'
    },
    {
        categoryId: 2,
        name: 'Arsa'
    },
    {
        categoryId: 3,
        name: 'Ticari'
    }
]);