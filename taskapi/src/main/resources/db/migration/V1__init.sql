CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE tasks (
    id UUID PRIMARY KEY,
    description VARCHAR(255),
    completed BOOLEAN,
    deleted BOOLEAN,
    creation_date TIMESTAMP,
    target_date TIMESTAMP,
    user_id UUID NOT NULL REFERENCES users(id)
);

CREATE TABLE notifications (
    id UUID PRIMARY KEY,
    message VARCHAR(255),
    read BOOLEAN,
    user_id UUID NOT NULL REFERENCES users(id)
);
