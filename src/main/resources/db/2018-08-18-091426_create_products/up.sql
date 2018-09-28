CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  specification_id INTEGER NOT NULL,
  FOREIGN KEY (specification_id) REFERENCES specifications(id)
)
