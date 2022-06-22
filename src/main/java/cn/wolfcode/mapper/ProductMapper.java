package cn.wolfcode.mapper;


import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.domain.Warehouse;
import cn.wolfcode.query.ProductQueryObject;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

  void save(Product Product);

  void update(Product Product);

  void delete(Long id);

  Product selectOne(Long id);

  List<Product> selectAll();

  int selectForCount();


  //根据店铺ID查商品
  List<Product> selectProductByShopId(@Param("shops")List<Shop> shops);

  //根据店铺id查商品数量
 /* @MapKey("id")
  Map<Long,Integer> selectProductCountByShopId(@Param("shops") List<Shop> shops);*/
 List<Shop> selectProductCountByShopId(@Param("shops") List<Shop> shops);
  List<Mall> selectProductCountByMallId(@Param("malls") List<Mall> malls);

}
