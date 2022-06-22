package cn.wolfcode.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Warehouse {
    private Long id;
    private String address;
    private String tel;
    private int num;
    private Product product;
}
