package org.zayac.jpom.product;

import com.github.jasync.sql.db.Connection;
import com.github.jasync.sql.db.QueryResult;

import java.util.Collection;
import java.util.stream.Collectors;

public class ProductDao {
    private static Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public static Collection<Product> getAll() {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM products"
        ).join();
        return queryResult.getRows().parallelStream().map(Product::new).collect(Collectors.toList());
    }

    public static Product getById(Long id) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM products WHERE id = " + id
        ).join();
        return new Product(queryResult.getRows().get(0));
    }

    public static Product create(Product product) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "INSERT INTO products (id, specification_id) VALUES (DEFAULT, '" + product.getSpecificationId() + "') RETURNING *"
        ).join();
        return new Product(
                queryResult.getRows().get(0)
        );
    }

}
