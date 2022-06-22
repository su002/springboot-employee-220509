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
@Data
public class Employee {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private String password;
    private String email;
    private boolean admin;

    private Integer count ;
    private int status;
    //关联属性
    private Department dept;
    private List<Role> roles;


}
