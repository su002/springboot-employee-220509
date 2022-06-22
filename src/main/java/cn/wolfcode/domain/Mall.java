package cn.wolfcode.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Mall {
    private Long id;
    private String name;
    private String sn;
    private int status;
    //查店铺
    private List<Shop> shops;
    //商品数量
   private int productCount;
}
