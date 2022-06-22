package cn.wolfcode.query;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductQueryObject extends QueryObject {
    private String keyword;
    private Integer minSales;
    private Integer maxSales;
    private Long shop_id;
    private int status;

}
