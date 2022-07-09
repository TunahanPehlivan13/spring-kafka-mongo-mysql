db.createUser({
    user: "property-store",
    pwd: "pass",
    roles: [{
        role: "readWrite",
        db: "property"
    }]
});