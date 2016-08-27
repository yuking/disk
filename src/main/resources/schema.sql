CREATE TABLE IF NOT EXISTS users (
  id  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(255)    NOT NULL,
  password VARCHAR(255)    NOT NULL,
  enabled  BOOLEAN         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS authorities (
  id   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  username  VARCHAR(255)    NOT NULL,
  authority VARCHAR(255)    NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS persistent_logins (
  username  VARCHAR(255) NOT NULL,
  series    VARCHAR(255) NOT NULL,
  token     VARCHAR(255) NOT NULL,
  last_used TIMESTAMP    NOT NULL,
  PRIMARY KEY (series)
);

CREATE TABLE IF NOT EXISTS file_node (
  id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  parent_id   BIGINT UNSIGNED NOT NULL,
  filename    VARCHAR(255)    NOT NULL,
  username    VARCHAR(255)    NOT NULL,
  dir         BOOLEAN         NOT NULL,
  size        BIGINT UNSIGNED NOT NULL,
  create_time TIMESTAMP       NOT NULL DEFAULT now(),
  PRIMARY KEY (id)
);


