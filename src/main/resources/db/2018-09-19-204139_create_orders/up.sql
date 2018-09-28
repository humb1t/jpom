CREATE TYPE order_status AS ENUM (
    'entering', 'in_progress', 'cancelled', 'completed'
);

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  specification_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  quantity INTEGER NOT NULL,
  status order_status NOT NULL,
  FOREIGN KEY (specification_id) REFERENCES specifications(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);