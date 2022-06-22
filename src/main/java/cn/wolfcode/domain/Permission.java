package cn.wolfcode.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Li hairui
 * @CreateTime: 2022/4/6 14:26
 */
@Setter
@Getter
@Component
public class Permission {
    private Long id;
    private String name;
    private String expression;
}
