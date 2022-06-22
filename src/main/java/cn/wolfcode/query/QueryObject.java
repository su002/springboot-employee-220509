package cn.wolfcode.query;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class QueryObject {
    private Integer num;
    //查询第几页
    private int currentPage = 1;
    //查询每页有多少条
    private int pageSize = 5;
    private int startIndex;

    public int getStartIndex(){
        return (currentPage - 1)*pageSize;
    }
}
