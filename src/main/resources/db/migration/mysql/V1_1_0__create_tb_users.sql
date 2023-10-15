DROP TABLE IF EXISTS TB_USERS;

CREATE TABLE TB_USERS (
    id VARCHAR(36),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20),
    created_at DATETIME
);

INSERT INTO TB_USERS (id, name, email, password, role)
VALUES
('df6e663a-f302-444a-a7fa-5ebecfa160de', 'TESTE1', 'teste1@email.com', '123123', 'JEDI'),
('07300028-a924-47cf-ad54-1c147a95a9a4', 'TESTE2', 'teste2@email.com', '321321', 'JUNIOR'),
('94f182df-6cad-405d-85bb-6851bfa1effc' ,'TESTE3', 'teste3@email.com', '123456', 'PLENO'),
('5f08a94e-3a9a-4daa-b4af-07af03b4523a', 'TESTE4', 'teste4@email.com', '656565', 'SENIOR'),
('11433ddc-efa8-4e62-b7fe-8bdc3ba46bb6', 'TESTE5', 'teste5@email.com', '006007', 'JUNIOR');
