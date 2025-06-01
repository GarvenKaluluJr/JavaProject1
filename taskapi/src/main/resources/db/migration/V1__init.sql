CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS task (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    description VARCHAR(255),
    completed BOOLEAN,
    deleted BOOLEAN
);

CREATE TABLE IF NOT EXISTS notification (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    message VARCHAR(255),
    read BOOLEAN
);
