CREATE TABLE chat
(
    id                  BIGINT NOT NULL,
    status              VARCHAR(255) NULL,
    customer_name       VARCHAR(255) NULL,
    customer_session_id VARCHAR(255) NULL,
    chat_token          VARCHAR(255) NULL,
    user_id             BIGINT NULL,
    CONSTRAINT pk_chat PRIMARY KEY (id)
);

CREATE TABLE human_message
(
    id        BIGINT NOT NULL,
    send_date date NULL,
    send_time time NULL,
    sender    INT NULL,
    content   VARCHAR(255) NULL,
    CONSTRAINT pk_human_message PRIMARY KEY (id)
);

CREATE TABLE site
(
    id        INT NOT NULL,
    name      VARCHAR(30) NULL,
    uri       VARCHAR(30) NULL,
    chat_name VARCHAR(40) NULL,
    user_id   BIGINT NULL,
    CONSTRAINT pk_site PRIMARY KEY (id)
);

CREATE TABLE user
(
    id            BIGINT NOT NULL,
    first_name    VARCHAR(255) NULL,
    last_name     VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    `role`        VARCHAR(255) NULL,
    provider      VARCHAR(255) NULL,
    ws_session_id VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE chat
    ADD CONSTRAINT FK_CHAT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE site
    ADD CONSTRAINT FK_SITE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);