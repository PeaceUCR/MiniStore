CREATE TABLE IF NOT EXISTS mini_store.transactions
(
    transaction_id         INT unsigned NOT NULL AUTO_INCREMENT,
    transaction_agent      VARCHAR(10) NOT NULL,
    transaction_account    VARCHAR(50) NOT NULL,
    transaction_type       VARCHAR(10) NOT NULL,
    transaction_amount     DECIMAL NOT NULL,
    transaction_status     VARCHAR(10) NOT NULL,
    order_id               INT unsigned NOT NULL,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

ALTER TABLE mini_store.orders ADD COLUMN status VARCHAR(10);
ALTER TABLE mini_store.orders ADD COLUMN tracking_id VARCHAR(50);
