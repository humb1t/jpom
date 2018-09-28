package org.zayac.jpom.order;

import com.github.jasync.sql.db.Connection;

import java.util.Collection;

public class OrderDao {
    private final Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public Collection<Order> getAll() {
        throw new UnsupportedOperationException();
    }

    public Order getById(Long id) {
        throw new UnsupportedOperationException();
    }

    public Order create(Order specification) {
        throw new UnsupportedOperationException();
    }

    public Order update(Order specification) {
        throw new UnsupportedOperationException();
    }
}
