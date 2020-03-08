CREATE TABLE IF NOT EXISTS mini_store.orders
(
    id              INT unsigned NOT NULL AUTO_INCREMENT,
    user_id         INT unsigned,
    total_amount    DECIMAL NOT NULL, #总金额
    discount        DECIMAL NOT NULL,
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    PRIMARY KEY     (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS mini_store.orders_items
(
    order_id        INT unsigned NOT NULL,
    item_id         INT unsigned NOT NULL,
    quantity        INT unsigned NOT NULL,
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);
