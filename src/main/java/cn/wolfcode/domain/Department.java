package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Department {
    private Long id;
    private String name;
    private String sn;
    private String json;

    //覆写
    public String getJson() {
        Map<String,Object> data = new HashMap<>();
        data.put("id",this.id);
        data.put("name",this.name);
        data.put("sn",this.sn);
        return JSON.toJSONString(data);
    }
}
