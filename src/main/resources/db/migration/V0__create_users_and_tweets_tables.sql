CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(255) NOT NULL
);

CREATE TABLE tweets (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), 
    user_id UUID NOT NULL,
    text TEXT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);