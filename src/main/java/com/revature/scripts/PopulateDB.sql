insert into users (first_name, last_name, username, "password", "role", email)
values ('brandon', 'le', 'admin', 'password', 'Employee', 'email@email.com');

INSERT INTO reimbursement (status, author_id, amount)
VALUES  ('Pending', 1, 5),
        ('Pending', 1, 10);

INSERT INTO reimbursement (status, author_id, resolver_id, amount)
VALUES  ('Approved', 1, 4, 20);

INSERT INTO reimbursement (status, author_id, amount)
VALUES  ('Pending', 1, 7.777);