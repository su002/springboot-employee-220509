package cn.wolfcode.mapper;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.query.QueryObject;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface MallMapper {
    void save(Mall mall);
    void update(Mall mall);
    void deleteById(Long id);
    Mall selectOne(Long id);
    List<Mall> selectAll();
    int selectForCount();

}
