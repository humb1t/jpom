package org.zayac.jpom.specification;

import com.github.jasync.sql.db.Connection;

import java.util.Collection;

public class SpecificationDao {
    private final Connection connection;

    public SpecificationDao(Connection connection) {
        this.connection = connection;
    }

    public static Collection<Specification> getById(Long id) {
        throw new UnsupportedOperationException();
    }

    public static Collection<Specification> getAll() {
        throw new UnsupportedOperationException();
    }

    public static Collection<Specification> getByName(String name) {
        throw new UnsupportedOperationException();
    }

    public static Specification create(Specification specification) {
        throw new UnsupportedOperationException();
    }

    public static Specification update(Long id, Specification specification) {
        throw new UnsupportedOperationException();
    }

    public static void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
