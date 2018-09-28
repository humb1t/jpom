package org.zayac.jpom.specification;

import com.github.jasync.sql.db.Connection;

import java.util.Collection;

public class SpecificationDao {
    private final Connection connection;

    public SpecificationDao(Connection connection) {
        this.connection = connection;
    }

    public Collection<Specification> getAll() {
        throw new UnsupportedOperationException();
    }

    public Collection<Specification> getByName() {
        throw new UnsupportedOperationException();
    }

    public Specification create(Specification specification) {
        throw new UnsupportedOperationException();
    }

    public Specification update(Specification specification) {
        throw new UnsupportedOperationException();
    }

    public void delete(Specification specification) {
        throw new UnsupportedOperationException();
    }
}
