package org.zayac.jpom.order;

import lombok.Data;
import org.zayac.jpom.specification.Specification;

@Data
public class Order {
    private Long id;
    private Specification specification;
    private Long quantity;
    private Status status;

    public Order(Specification specification) {
        this.specification = specification;
    }

    public Order() {
    }

    public Order start() {
        this.status = Status.IN_PROGRESS;
        return this;
    }

    public Order cancel() {
        this.status = Status.CANCELLED;
        return this;
    }

    public enum Status {
        ENTERING, IN_PROGRESS, CANCELLED, COMPLETED
    }
}
