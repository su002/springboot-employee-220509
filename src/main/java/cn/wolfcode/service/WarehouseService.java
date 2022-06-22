package cn.wolfcode.service;

import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Warehouse;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface WarehouseService {
    void saveOrUpdate(Warehouse Warehouse);
    void deleteById(Long id);
    Warehouse selectOne(Long id);
    List<Warehouse> selectAll();
    PageInfo<Warehouse> query(QueryObject queryObject);

    //根据商品Id查库存
    List<Warehouse> selectWarehouseByProduceId(List<Product> products);

}
