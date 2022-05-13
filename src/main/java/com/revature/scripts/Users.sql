CREATE TABLE if not exists users (
    id SERIAL,
    first_name VARCHAR(50) not null,
    last_name VARCHAR(50) not null,
    username VARCHAR(50) not null,
    password VARCHAR(50) not null,
    email VARCHAR(50) not null,
    role VARCHAR(16) not null,
    unique (username, email),
    primary key (id)
);



CREATE TABLE if not exists reimbursement (
    id SERIAL,
    status VARCHAR(20) not null,
    author_id INT not null,
    resolver_id INT,
    amount DECIMAL(12,2) not null,
    CONSTRAINT fk_author_id
        FOREIGN KEY (author_id)
            REFERENCES users(id),
    CONSTRAINT fk_resolver_id
        FOREIGN KEY (resolver_id)
            REFERENCES users(id)
);