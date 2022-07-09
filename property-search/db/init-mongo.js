db.createUser({
    user: "property-search",
    pwd: "pass",
    roles: [{
        role: "read",
        db: "property"
    }]
});