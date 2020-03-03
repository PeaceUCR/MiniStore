CREATE TABLE IF NOT EXISTS users #mini_store.users
(
    id              INT unsigned NOT NULL AUTO_INCREMENT, # Unique ID for the record
    encrpt_password VARCHAR(128) NOT NULL,
    name            VARCHAR(20) NOT NULL,                # Name of the cat
    phone           VARCHAR(20) NOT NULL,                # Owner of the cat
    wechat_open_id  VARCHAR(30),                        # Birthday of the cat
    PRIMARY KEY     (id)                                  # Make the id the primary key
);
