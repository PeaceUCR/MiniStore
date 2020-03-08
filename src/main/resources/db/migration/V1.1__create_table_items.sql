CREATE TABLE IF NOT EXISTS mini_store.items
(
    item_id              INT unsigned NOT NULL AUTO_INCREMENT,
    item_name            VARCHAR(64) NOT NULL,
    item_price           DECIMAL NOT NULL,
    item_price_unit      VARCHAR(10) NOT NULL,
    item_description     VARCHAR(500) NOT NULL,
    item_sales           INT DEFAULT 0,                # sales count
    item_img_url         VARCHAR(100),
    user_id              INT unsigned,                 # creator user id
    create_date          DATETIME NOT NULL,
    update_date          DATETIME NOT NULL,
    PRIMARY KEY     (item_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
