package org.zayac.jpom.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.zayac.jpom.order.Order;
import org.zayac.jpom.specification.Specification;

import java.util.List;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private Specification specification;
    private List<Order> orders;

    public Product(Specification specification, List<Order> orders) {
        this.specification = specification;
        this.orders = orders;
    }
}
