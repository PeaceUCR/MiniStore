CREATE TABLE IF NOT EXISTS item_stock
(
    id              INT unsigned NOT NULL AUTO_INCREMENT, # Unique ID for the record
    stock           INT NOT NULL,
    item_id         INT NOT NULL,
    PRIMARY KEY     (id)
);
