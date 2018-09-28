package org.zayac.jpom.order;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long specificationId;
    private Long quantity;
    private Status status;

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
