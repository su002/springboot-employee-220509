package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
public class Role {
    private Long id;
    private String name;
    private String sn;
    private List<Permission> permissions;
}
