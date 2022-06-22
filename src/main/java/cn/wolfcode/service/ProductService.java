package cn.wolfcode.service;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.query.ProductQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void saveOrUpdate(Product Product);

    void deleteById(Long id);

    Product selectOne(Long id);

    List<Product> selectList();
    //分页查询数据
    PageInfo<Product> query(ProductQueryObject queryObject);

    //根据店铺的id查商品
    List<Product> selectProductByShopId(List<Shop> shop);

    //根据店铺的ID查商品数量
   /* Map<Long,Integer> selectProductCountByShopId(List<Shop> shops);*/
    List<Shop> selectProductCountByShopId(List<Shop> shops);

    List<Mall> selectProductCountByMallId(List<Mall> malls);
}
