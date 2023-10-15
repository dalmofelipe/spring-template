CREATE TYPE USER_ROLE AS ENUM ('JUNIOR', 'PLENO', 'SENIOR', 'JEDI');

ALTER TABLE TB_USERS 
ALTER COLUMN role TYPE USER_ROLE
USING role::text::USER_ROLE,
    ALTER COLUMN role SET DEFAULT NULL;
