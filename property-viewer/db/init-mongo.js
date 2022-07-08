db.createUser({
    user: "property-viewer",
    pwd: "pass",
    roles: [{
        role: "read",
        db: "property"
    }]
});