package cn.wolfcode.service;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MallService {
    void saveOrUpdate(Mall mall);
    void deleteById(Long id);
    Mall selectOne(Long id);
    List<Mall> selectAll();

    PageInfo<Mall> query(QueryObject queryObject);







}
