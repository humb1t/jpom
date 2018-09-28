package org.zayac.jpom.product;

import com.github.jasync.sql.db.Connection;

import java.util.Collection;

public class ProductDao {
    private final Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public static Collection<Product> getAll() {
        throw new UnsupportedOperationException();
    }

    public static Product getById(Long id) {
        throw new UnsupportedOperationException();
    }

    public static Product create(Product product) {
        throw new UnsupportedOperationException();
    }

}
