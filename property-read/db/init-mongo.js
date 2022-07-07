db.createUser({
    user: "property-read",
    pwd: "pass",
    roles: [{
        role: "read",
        db: "property"
    }]
});