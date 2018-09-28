package org.zayac.jpom.specification;

import com.github.jasync.sql.db.RowData;
import lombok.Data;

@Data
public class Specification {
    private Long id;
    private String name;

    public Specification() {
    }

    public Specification(RowData rowData) {
        this.id = Long.valueOf((Integer) rowData.get("id"));
        this.name = (String) rowData.get("name");
    }

}
