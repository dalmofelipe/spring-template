CREATE TYPE user_role AS ENUM ('JUNIOR', 'PLENO', 'SENIOR', 'JEDI');

ALTER TABLE tb_users
ALTER COLUMN role TYPE user_role
USING role::text::user_role,
    ALTER COLUMN role SET DEFAULT NULL;
