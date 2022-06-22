package cn.wolfcode.query;


import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId;
}
