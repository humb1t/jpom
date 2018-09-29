package org.zayac.jpom.order;

import com.github.jasync.sql.db.RowData;
import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long specificationId;
    private Long quantity;
    private Status status;
    private Long productId;

    public Order() {
    }

    public Order(RowData rowData) {
        this.id = Long.valueOf((Integer) rowData.get("id"));
        this.specificationId = Long.valueOf((Integer) rowData.get("specification_id"));
        this.quantity = Long.valueOf((Integer) rowData.get("quantity"));
        this.status = Status.valueOf((String.valueOf(rowData.get("status"))).toUpperCase());
    }

    public Order start() {
        this.status = Status.IN_PROGRESS;
        return this;
    }

    public Order cancel() {
        this.status = Status.CANCELLED;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public enum Status {
        ENTERING, IN_PROGRESS, CANCELLED, COMPLETED
    }
}
