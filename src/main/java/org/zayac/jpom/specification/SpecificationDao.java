package org.zayac.jpom.specification;

import com.github.jasync.sql.db.Connection;
import com.github.jasync.sql.db.QueryResult;

import java.util.Collection;
import java.util.stream.Collectors;

public class SpecificationDao {
    private static Connection connection;

    public SpecificationDao(Connection connection) {
        this.connection = connection;
    }

    public static Specification getById(Long id) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM specifications WHERE id = " + id
        ).join();
        return new Specification(
                queryResult.getRows().get(0)
        );
    }

    public static Collection<Specification> getAll() {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM specifications"
        ).join();
        return queryResult.getRows().parallelStream().map(Specification::new).collect(Collectors.toList());
    }

    public static Collection<Specification> getByName(String name) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "SELECT * FROM specifications WHERE name LIKE '" + name + "'"
        ).join();
        return queryResult.getRows().parallelStream().map(Specification::new).collect(Collectors.toList());
    }

    public static Specification create(Specification specification) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "INSERT INTO specifications (id, name) VALUES (DEFAULT, '" + specification.getName() + "') RETURNING *"
        ).join();
        return new Specification(
                queryResult.getRows().get(0)
        );
    }

    public static Specification update(Long id, Specification specification) {
        QueryResult queryResult = connection.sendPreparedStatement(
                "UPDATE specifications SET name = '" + specification.getName() + "' WHERE id =" + id + "RETURNING *"
        ).join();
        return new Specification(
                queryResult.getRows().get(0)
        );
    }

    public static void delete(Long id) {
        connection.sendPreparedStatement("DELETE FROM specifications WHERE id = " + id).join();
    }
}
