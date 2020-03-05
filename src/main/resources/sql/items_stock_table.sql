CREATE TABLE IF NOT EXISTS mini_store.item_stock
(
    id              INT unsigned NOT NULL AUTO_INCREMENT, # Unique ID for the record
    stock           INT NOT NULL,
    item_id         INT unsigned NOT NULL,
    create_date     DATETIME NOT NULL,
    update_date     DATETIME NOT NULL,
    PRIMARY KEY     (id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);
