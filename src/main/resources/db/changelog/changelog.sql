--liquibase formatted sql

--changeset randobigor:categories-table splitStatements:FALSE
CREATE TABLE categories(
  id       SERIAL  PRIMARY KEY,
  name     TEXT    NOT NULL,
  position INTEGER NOT NULL
);

--changeset randobigor:pictures-table splitStatements:FALSE
CREATE TABLE pictures(
  id      SERIAL PRIMARY KEY,
  content TEXT   NOT NULL
);

--changeset randobigor:roles-table splitStatements:FALSE
CREATE TABLE roles(
  id   SERIAL PRIMARY KEY,
  name TEXT   NOT NULL UNIQUE
);

--changeset randobigor:users-table splitStatements:FALSE
CREATE TABLE users(
  id         SERIAL PRIMARY KEY,
  first_name TEXT          NOT NULL,
  last_name  TEXT          NOT NULL,
  email      TEXT          NOT NULL,
  password   TEXT          NOT NULL,
  balance    NUMERIC(8, 2) NOT NULL DEFAULT 0,
  picture    TEXT
);

--changeset randobigor:users-to-roles-table splitStatements:FALSE
CREATE TABLE users_to_roles(
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT PK_users_to_roles_user_id_role_id PRIMARY KEY (user_id, role_id),
  CONSTRAINT FK_users_to_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FK_users_to_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

--changeset randobigor:project-table splitStatements:FALSE
CREATE TABLE projects(
  id          SERIAL PRIMARY KEY,
  name        TEXT           NOT NULL,
  description TEXT           NOT NULL,
  category_id BIGINT         NOT NULL,
  created_by  BIGINT         NOT NULL,
  collected   NUMERIC(10, 2) NOT NULL DEFAULT 0,
  target      NUMERIC(10, 2) NOT NULL,
  picture     TEXT           NOT NULL,
  completed   BOOLEAN        NOT NULL DEFAULT FALSE,
  created_tm  TIMESTAMP      NOT NULL DEFAULT NOW(),
  CONSTRAINT FK_projects_category_id FOREIGN KEY (category_id) REFERENCES categories (id),
  CONSTRAINT FK_projects_created_by FOREIGN KEY (created_by) REFERENCES users (id)
);

--changeset randobigor:projects-to-pictures-table splitStatements:FALSE
CREATE TABLE projects_to_pictures(
  project_id BIGINT NOT NULL,
  picture_id BIGINT NOT NULL,
  CONSTRAINT FK_projects_to_pictures_project_id FOREIGN KEY (project_id) REFERENCES projects (id),
  CONSTRAINT FK_projects_to_pictures_picture_id FOREIGN KEY (picture_id) REFERENCES pictures (id)
);

--changeset randobigor:payments-table splitStatements:FALSE
CREATE TABLE payments(
  id           SERIAL PRIMARY KEY,
  processed_tm TIMESTAMP     NOT NULL DEFAULT NOW(),
  donated_by   BIGINT        NOT NULL,
  project_id   BIGINT        NOT NULL,
  value        numeric(7, 2) NOT NULL,
  CONSTRAINT FK_payments_donated_by FOREIGN KEY (donated_by) REFERENCES users (id),
  CONSTRAINT FK_payments_project_id FOREIGN KEY (project_id) REFERENCES projects (id)
);

--changeset randobigor:populating-categories-table splitStatements:FALSE
INSERT INTO categories (name, position)
VALUES
('Благотворительность', 1),
('Спорт', 2),
('Наука и технологии', 3),
('Культура и искусство', 4),
('Ремесло', 5),
('Другое', 6);

--changeset randobigor:populating-roles-table splitStatements:FALSE
INSERT INTO roles(name) VALUES
('ROLE_ADMIN'),
('ROLE_MODERATOR'),
('ROLE_USER');
