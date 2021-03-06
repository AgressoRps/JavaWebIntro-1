CREATE TABLE ldap_user_attributes      --// сущность для хранения информации о атрибутах пользователя на ldap-сервере
(
  id BIGINT AUTO_INCREMENT NOT NULL,    --// уникальный идентификатор
  name CHARACTER VARYING NOT NULL,     --// атрибут имени
  surname CHARACTER VARYING NOT NULL,  --// атрибут фамилии
  second_name CHARACTER VARYING,       --// атрибут отчества
  mail CHARACTER VARYING,              --// атрибут почты
  login CHARACTER VARYING NOT NULL,    --// атрибут логина
  PRIMARY KEY (id)
);

CREATE TABLE ldap_auth                            --// сущность для хранения ldap-серверов
(
  id BIGINT AUTO_INCREMENT NOT NULL,                          --// суникальный идентификатор
  date_open TIMESTAMP NOT NULL,                   --// дата создания
  address CHARACTER VARYING NOT NULL,             --// url ldap-сервера
  port CHARACTER VARYING NOT NULL,                --// порт ldap-сервера
  name CHARACTER VARYING NOT NULL,                --// наименование ldap-сервера
  groups_directory CHARACTER VARYING NOT NULL,    --// директория с группами пользователей
  users_directory CHARACTER VARYING NOT NULL,     --// директория с пользователями
  active BOOLEAN DEFAULT TRUE NOT NULL,           --// флаг активности ldap-сервера
  role_references CHARACTER VARYING NOT NULL,     --// связь групп пользователей с ролями системы
  user_attributes BIGINT NOT NULL,                --// ссылка на сущность с информацией об атрибутах на ldap-сервере
  domain CHARACTER VARYING NOT NULL,                --//
  ldap_login CHARACTER VARYING NOT NULL,                --//
  ldap_password CHARACTER VARYING NOT NULL,                --//
  credentials_auth BOOLEAN DEFAULT FALSE NOT NULL,
  role_from_group BOOLEAN DEFAULT FALSE NOT NULL,
  readonly BOOLEAN DEFAULT TRUE NOT NULL,
  user_class CHARACTER VARYING NOT NULL DEFAULT 'inetOrgPerson',                --//
  group_class CHARACTER VARYING NOT NULL DEFAULT 'posixGroup',                --//
  PRIMARY KEY (id),
  FOREIGN KEY (user_attributes) REFERENCES ldap_user_attributes (id)
);

CREATE TABLE users
(
  id                  BIGINT AUTO_INCREMENT NOT NULL,                    -- // уникальный идентификатор
  login               CHARACTER VARYING NOT NULL UNIQUE,     -- // логин
  surname             CHARACTER VARYING NOT NULL,            -- // фамилия
  name                CHARACTER VARYING NOT NULL,            -- // имя
  second_name         CHARACTER VARYING,                     -- // отчество
  date_create         TIMESTAMP NOT NULL,                    -- // дата создания
  date_block          TIMESTAMP,                             -- // дата блокировки
  date_last_online    TIMESTAMP,                             -- // дата последней активности в системе
  password            CHARACTER VARYING NOT NULL,            -- // пароль
  mail                CHARACTER VARYING NOT NULL,            -- // почтовый ящик
  role                CHARACTER VARYING,            -- // роль в системе
  status              CHARACTER VARYING NOT NULL,            -- // статус автивности аккаунта
  id_ldap             BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_ldap) REFERENCES ldap_auth (id)
);

INSERT INTO users (login, surname, name, date_create, password, mail, status, role)
VALUES('root','Администратор', 'Администратор', CURRENT_TIMESTAMP,
       '$2a$10$LijUmixpYL0i9rRvwXrnX.heUijboQzE3PsoCrxuJANIDVX28FNjS',
       'admin@email', 'ACTIVE', 'ROLE_ADMIN');

INSERT INTO users (login, surname, name, date_create, password, mail, status, role)
VALUES('operator','Оператор', 'Оператор', CURRENT_TIMESTAMP,
       '$2a$10$LijUmixpYL0i9rRvwXrnX.heUijboQzE3PsoCrxuJANIDVX28FNjS',
       'operator@email', 'ACTIVE', 'ROLE_OPERATOR');

CREATE TABLE permissions                                 --// сущность для связи разрешений с ролями пользователей
(
  id          BIGINT AUTO_INCREMENT NOT NULL,            --// уникальный идентификатор
  action      CHARACTER VARYING NOT NULL,                --// наименование разрешения
  role        CHARACTER VARYING NOT NULL,                --// роль
  PRIMARY KEY (id)
);

CREATE TABLE company                                      --// сущность для хранения списка компаний
(
  id          BIGINT AUTO_INCREMENT NOT NULL,             --// уникальный идентификатор
  name        CHARACTER VARYING NOT NULL,                 --// наименование компании
  PRIMARY KEY(id)
);

INSERT INTO company (name)
VALUES('Уральский союз');

CREATE TABLE pilot                                          --// сущность для хранения пилотов
(
  id              BIGINT AUTO_INCREMENT NOT NULL,           --// уникальный идентификатор
  name            CHARACTER VARYING NOT NULL,               --// имя
  surname         CHARACTER VARYING NOT NULL,               --// фамилия
  second_name     CHARACTER VARYING NOT NULL,               --// отчество
  mail            CHARACTER VARYING NOT NULL,               --// почта
  status          CHARACTER VARYING NOT NULL,               --// флаг, в полете либо нет
  PRIMARY KEY(id)
);

INSERT INTO pilot (name, surname, second_name, mail, status)
VALUES('Владислав','Старокожев', 'Сергеевич', 'AgressoRj@gmail.com', 'AVAILABLE');

CREATE TABLE type_aircraft                                  --// сущность для хранения типов самолетов
(
  id              BIGINT AUTO_INCREMENT NOT NULL,           --// уникальный идентификатор
  name            CHARACTER VARYING NOT NULL,               --// наименование типа
  PRIMARY KEY(id)
);

INSERT INTO type_aircraft (name)
VALUES('Средний пассажирский самолет');

CREATE TABLE name_aircraft                                  --// сущность для хранения наименований моделей самолетов
(
  id              BIGINT AUTO_INCREMENT NOT NULL,           --// уникальный идентификатор
  name            CHARACTER VARYING NOT NULL,               --// наименование модели
  PRIMARY KEY(id)
);

INSERT INTO name_aircraft (name)
VALUES('ТУ-134');
INSERT INTO name_aircraft (name)
VALUES('ТУ-127');

CREATE TABLE aircraft                                       --// сущность для хранения самолетов
(
  id                BIGINT AUTO_INCREMENT NOT NULL,         --// уникальный идентификатор
  id_name           BIGINT NOT NULL,                        --// модель самолета
  places            INTEGER NOT NULL,                       --// количество мест в самолете
  condition_air     CHARACTER VARYING NOT NULL,             --// флаг, на ремонте либо нет
  id_type           BIGINT NOT NULL,                        --// ссылка на сущность с информацией о типах самолетов
  id_company        BIGINT NOT NULL,                        --// ссылка на сущность с информацией о компаниях
  id_pilot          BIGINT NOT NULL,                        --// ссылка на сущность с информацией о пилотах
  PRIMARY KEY(id),
  FOREIGN KEY (id_name) REFERENCES name_aircraft(id),
  FOREIGN KEY (id_type) REFERENCES type_aircraft(id),
  FOREIGN KEY (id_company) REFERENCES company (id),
  FOREIGN KEY (id_pilot) REFERENCES pilot (id)
);

INSERT INTO aircraft (id_name, places, condition_air, id_type, id_company, id_pilot)
VALUES(1, 130, 'REPAIR', 1, 1, 1);
INSERT INTO aircraft (id_name, places, condition_air, id_type, id_company, id_pilot)
VALUES(2, 142, 'AVAILABLE', 1, 1, 1);

