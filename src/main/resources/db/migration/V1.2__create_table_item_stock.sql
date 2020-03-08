CREATE TABLE IF NOT EXISTS mini_store.item_stock
(
    item_stock      INT NOT NULL,
    item_id         INT unsigned NOT NULL,
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);
