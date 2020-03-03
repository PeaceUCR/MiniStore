CREATE TABLE IF NOT EXISTS mini_store.item
(
    id              INT unsigned NOT NULL AUTO_INCREMENT, # Unique ID for the record
    name            VARCHAR(64) NOT NULL,                # Name of the cat
    price           DECIMAL NOT NULL,
    price_unit      VARCHAR(10) NOT NULL,
    description     VARCHAR(500) NOT NULL,
    sales           INT DEFAULT 0,                # sales count
    img_url         VARCHAR(100),
    user_id         INT unsigned,
    PRIMARY KEY     (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
