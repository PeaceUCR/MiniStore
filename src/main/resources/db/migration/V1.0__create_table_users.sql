CREATE TABLE IF NOT EXISTS mini_store.users #mini_store.users
(
    user_id         INT unsigned NOT NULL AUTO_INCREMENT,
    user_role       VARCHAR(20),
    encrpt_password VARCHAR(128) NOT NULL,
    user_name       VARCHAR(20) NOT NULL,
    phone           VARCHAR(20) NOT NULL,
    wechat_open_id  VARCHAR(30),
    user_avatar_url VARCHAR(100),
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    PRIMARY KEY     (user_id)                                  # Make the id the primary key
);
