package cn.wolfcode.service;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ShopService {
    void saveOrUpdate(Shop Shop);
    void deleteById(Long id);
    Shop selectOne(Long id);
    List<Shop> selectAll();

    PageInfo<Shop> query(QueryObject queryObject);

    List<Shop> selectShopByMallId(List<Mall> malls);

}
