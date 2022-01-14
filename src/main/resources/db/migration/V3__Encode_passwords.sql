create extension if not exists pgcrypto;

update tuser set password = crypt(password, gen_salt('bf', 10));