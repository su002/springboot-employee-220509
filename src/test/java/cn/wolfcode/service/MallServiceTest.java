package cn.wolfcode.service;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.domain.Warehouse;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MallServiceTest {
    @Autowired
    private MallService mallServiceImpl;
    @Autowired
    private ProductService productServiceImpl;
    @Autowired
    private ShopService shopServiceImpl;
    @Test
    public void countTest(){

        List<Shop> shops = shopServiceImpl.selectAll();
        //查当前店铺下面的商品、
        List<Product> productList = productServiceImpl.selectProductByShopId(shops);
        //集合转map
        Map<Long,List<Product>> productMap = new HashMap<>();
        for (Product product:productList){
            Long shopId = product.getShop().getId();
            if (productMap.containsKey(shopId)) {
                List<Product> temProductList = productMap.get(shopId);
                temProductList.add(product);
                productMap.put(shopId,temProductList);
            }else {
                List<Product> temProductList = new ArrayList<>();
                temProductList.add(product);
                productMap.put(shopId,temProductList);
            }
        }

        //建立店铺和商品的关系
        for (Shop shop:shops){
            List<Product> temProductList = productMap.get(shop.getId());
            shop.setProducts(temProductList);
        }




        QueryObject queryObject = new QueryObject();
       PageInfo<Shop> shops1 = shopServiceImpl.query(queryObject);
       List<Shop> shops2 = shops1.getList();
        for (Shop shop:shops2){
            System.out.println(shop.getProducts());
        }




/*
        List<Shop> shops = shopServiceImpl.selectAll();
        for (Shop shop:shops){
            System.out.println(shop.getProductCount());
        }*/



       /* List<Shop> shopList = productServiceImpl.selectProductCountByShopId(shops);
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Shop shop:shopList){
            Long shopId = shop.getId();
            if (!productCountMap.containsKey(shopId)){
                Integer integer = shop.getProductCount();
                productCountMap.put(shopId,integer);
            }
        }

        for (Shop shop:shopList){
            Integer count = productCountMap.get(shop.getId());
            shop.setProductCount(count);
        }
*/


       /* List<Shop> shopList = productServiceImpl.selectProductCountByShopId(shops);
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Shop shop:shopList){
            Long shopId = shop.getId();
            if (!productCountMap.containsKey(shopId)){
                Integer integer = shop.getProductCount();
                productCountMap.put(shopId,integer);
            }
        }

        for (Shop shop:shopList){
            Integer count = productCountMap.get(shop.getId());
            shop.setProductCount(count);
        }*/



    }

}