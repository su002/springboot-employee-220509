package cn.wolfcode.service.impl;


import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.mapper.ShopMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.ProductService;
import cn.wolfcode.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapperImpl;
    @Autowired
    private ProductService productServiceImpl;


    @Override
    public void saveOrUpdate(Shop shop) {
        Long id = shop.getId();
        if(id == null){
            shopMapperImpl.save(shop);
        }else {
            shopMapperImpl.update(shop);
        }
    }

    @Override
    public void deleteById(Long id) {
        shopMapperImpl.deleteById(id);
    }

    @Override
    public Shop selectOne(Long id) {
        return shopMapperImpl.selectOne(id);
    }

    @Override
    public List<Shop> selectAll() {
        List<Shop> shops = shopMapperImpl.selectAll();
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

          List<Shop> shopList = productServiceImpl.selectProductCountByShopId(shops);
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Shop shop:shopList){
            Long shopId = shop.getId();
            Integer count = 0;
            if (!productCountMap.containsKey(shopId)){
                if (shop.getProductCount() != null){
                    count = shop.getProductCount();
                }
                productCountMap.put(shopId,count);
            }
        }

        //建立店铺和商品的关系
        for (Shop shop:shops){
            List<Product> temProductList = productMap.get(shop.getId());
            shop.setProducts(temProductList);
            Integer count = 0;
             count = productCountMap.get(shop.getId());
            shop.setProductCount(count);
        }
        return shops;
    }

    @Override
    public PageInfo<Shop> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Shop> shops = selectAll();
        return new PageInfo<>(shops);
    }

    @Override
    public List<Shop> selectShopByMallId(List<Mall> malls) {
        return shopMapperImpl.selectShopByMallId(malls);
    }

    // map
    // shopId : 商品数量

}
