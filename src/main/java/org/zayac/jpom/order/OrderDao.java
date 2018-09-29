package org.zayac.jpom.order;

import com.github.jasync.sql.db.Connection;
import com.github.jasync.sql.db.QueryResult;

import java.util.Collection;
import java.util.stream.Collectors;

public class OrderDao {
    private static Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public static Collection<Order> getAll() {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM orders"
        ).join();
        return queryResult.getRows().parallelStream().map(Order::new).collect(Collectors.toList());
    }

    public static Order getById(Long id) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM orders WHERE id = " + id
        ).join();
        return new Order(
                queryResult.getRows().get(0)
        );
    }

    public static Order create(Order order) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "INSERT INTO orders (id, specification_id, product_id, quantity, status) VALUES" +
                        " (DEFAULT, " + order.getSpecificationId() + " , " + order.getProductId() + "," + order.getQuantity() + ", '" + order.getStatus().toString().toLowerCase() + "') RETURNING *"
        ).join();
        return new Order(
                queryResult.getRows().get(0)
        );
    }

    public static Order update(Long id, Order order) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "UPDATE orders SET status = '" + order.getStatus().toString().toLowerCase() + "' WHERE id =" + id + "RETURNING *"
        ).join();
        return new Order(
                queryResult.getRows().get(0)
        );
    }
}
