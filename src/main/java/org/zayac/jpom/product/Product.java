package org.zayac.jpom.product;

import com.github.jasync.sql.db.RowData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private Long specificationId;
    private List<Long> ordersIds;

    public Product(Long specificationId, List<Long> ordersIds) {
        this.specificationId = specificationId;
        this.ordersIds = ordersIds;
    }

    public Product(RowData rowData) {
        this.id = Long.valueOf((Integer) rowData.get("id"));
        this.specificationId = Long.valueOf((Integer) rowData.get("specification_id"));
        this.ordersIds = Collections.emptyList();
    }
}
