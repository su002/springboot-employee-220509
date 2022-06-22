package cn.wolfcode.mapper;

import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Warehouse;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseMapper {
    void save(Warehouse Warehouse);
    void update(Warehouse Warehouse);
    void deleteById(Long id);
    Warehouse selectOne(Long id);
    List<Warehouse> selectAll();
    int selectForCount();


    //根据商品id查库存
    List<Warehouse> selectWarehouseByProduceId(@Param("products") List<Product> products);



}
