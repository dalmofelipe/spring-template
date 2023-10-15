-- Alteração necessária para corrigir o bind de UUID para string ao recuperar dados do usuário no Postgres

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE TB_USERS ALTER COLUMN id SET DATA TYPE UUID USING (uuid_generate_v4());

INSERT INTO TB_USERS(id, name, email, password, role)
VALUES 
('df6e663a-f302-444a-a7fa-5ebecfa160de', 'Dalmo', 'dalmo@email.com', '123123', 'JEDI'),
('07300028-a924-47cf-ad54-1c147a95a9a4', 'Felipe', 'felipe@email.com', '321321', 'JUNIOR'),
('94f182df-6cad-405d-85bb-6851bfa1effc' ,'Torres', 'torres@email.com', '123456', 'PLENO'),
('5f08a94e-3a9a-4daa-b4af-07af03b4523a', 'Paula', 'Paula@email.com', '656565', 'PLENO'),
('11433ddc-efa8-4e62-b7fe-8bdc3ba46bb6', 'De', 'de@email.com', '006007', 'JUNIOR');
