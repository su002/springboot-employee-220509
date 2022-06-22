package cn.wolfcode.mapper;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {
    void save(Shop shop);
    void update(Shop Shop);
    void deleteById(Long id);
    Shop selectOne(Long id);
    List<Shop> selectAll();
    int selectForCount();


    //根据商城id查店铺
    List<Shop> selectShopByMallId(@Param("malls")List<Mall> malls);





}
