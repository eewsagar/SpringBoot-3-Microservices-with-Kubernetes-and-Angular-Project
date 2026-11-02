CREATE TABLE t_orders (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        order_number VARCHAR(255),
                        skucode VARCHAR(255),
                        price DECIMAL(19, 2),
                        quantity INT(11),
                        PRIMARY KEY (id)
);
-- fix cache.