package cn.wolfcode.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long id ;
    private String name;
    private String author;
    private Long  score;
    private List<Student> studentList = new ArrayList<>();
    private Integer stucount;
    private Integer num;
}
