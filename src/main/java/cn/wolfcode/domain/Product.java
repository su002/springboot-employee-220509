package cn.wolfcode.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Product {
    private Long id;
    private String name;
    private String sn;
    private int num;
    private int status;
    private Shop shop;
}
