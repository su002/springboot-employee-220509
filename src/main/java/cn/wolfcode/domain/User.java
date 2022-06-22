package cn.wolfcode.domain;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class User {
    private Long id;
    private String username;
    private String password;
}
