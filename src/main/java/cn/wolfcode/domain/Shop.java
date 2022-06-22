package cn.wolfcode.domain;


import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ToString
public class Shop {
    private Long id;
    private String name;
    private Employee employee;
    private String sn;
    private int status;
    private Mall mall;
    private int admin;
    private int mallId;
    //查商品
    private List<Product> products;
    //店铺的商品数量
    private Integer productCount ;
}
