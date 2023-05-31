-- Create the orders table
DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id VARCHAR(255),
    customer_id BIGINT,
    product_id BIGINT,
    quantity INT,
    order_date TIMESTAMP,
    order_status VARCHAR(255)
);

/*
         CREATED,
        PROCESSING,
        COMPLETED,
        CANCELLED,
        FAILED
 */

-- Insert sample data
INSERT INTO orders (order_id, customer_id, product_id, quantity, order_date, order_status)
VALUES ('ORD001', 1, 101, 2, '2022-01-01 10:00:00', 'CREATED'),
       ('ORD002', 2, 102, 3, '2022-02-02 12:00:00', 'PROCESSING'),
       ('ORD003', 2, 104, 3, '2022-02-02 12:00:00', 'COMPLETED'),
       ('ORD004', 3, 103, 1, '2022-03-03 14:00:00', 'CANCELLED'),
       ('ORD005', 3, 103, 1, '2022-03-03 14:00:00', 'FAILED');
