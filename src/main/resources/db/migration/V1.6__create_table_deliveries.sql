CREATE TABLE IF NOT EXISTS mini_store.deliveries
(
    delivery_id          INT unsigned NOT NULL AUTO_INCREMENT,
    delivery_name        VARCHAR(10) NOT NULL,
    delivery_phone       VARCHAR(20) NOT NULL,
    delivery_address     VARCHAR(50) NOT NULL,
    delivery_province    VARCHAR(10) NOT NULL,
    delivery_city        VARCHAR(10) NOT NULL,
    delivery_district    VARCHAR(10) NOT NULL,
    order_id             INT unsigned NOT NULL,
    PRIMARY KEY (delivery_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

