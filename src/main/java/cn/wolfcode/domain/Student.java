package cn.wolfcode.domain;

import lombok.Data;

@Data
public class Student {
    private Long id ;
    private String name;
    private  Long age ;
    private String email;
    private Course course;
}
