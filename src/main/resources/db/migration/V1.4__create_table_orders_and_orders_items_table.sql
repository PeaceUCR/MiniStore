CREATE TABLE IF NOT EXISTS mini_store.orders
(
    order_id        INT unsigned NOT NULL AUTO_INCREMENT,
    user_id         INT unsigned,     #user id of create this order
    total_amount    DECIMAL NOT NULL, #总金额
    discount        DECIMAL NOT NULL,
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    PRIMARY KEY     (order_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS mini_store.orders_items
(
    order_id        INT unsigned NOT NULL,
    item_id         INT unsigned NOT NULL,
    quantity        INT unsigned NOT NULL,
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);
