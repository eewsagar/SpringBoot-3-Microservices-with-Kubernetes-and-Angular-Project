CREATE TABLE t_inventory (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          sku_code VARCHAR(255),
                          quantity integer(20),
                          PRIMARY KEY (id)
);
-- fix cache.