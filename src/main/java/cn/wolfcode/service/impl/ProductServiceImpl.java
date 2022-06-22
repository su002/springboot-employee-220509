package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.mapper.ProductMapper;
import cn.wolfcode.mapper.WarehouseMapper;
import cn.wolfcode.query.ProductQueryObject;
import cn.wolfcode.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapperImpl;
    @Autowired
    private WarehouseMapper warehouseMapperImpl;


    @Override
    public void saveOrUpdate(Product product) {
        Long id = product.getId();
        if (id == null){
            //添加
            productMapperImpl.save(product);
        }else {
            //更新
            productMapperImpl.update(product);
        }
    }

    @Override
    public Product selectOne(Long id) {
        return productMapperImpl.selectOne(id);
    }

    @Override
    public void deleteById(Long id) {
        productMapperImpl.delete(id);
    }

    @Override
    public List<Product> selectList() {
        return productMapperImpl.selectAll();
    }

    @Override
    public PageInfo<Product> query(ProductQueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Product> productList = productMapperImpl.selectAll();
        return new PageInfo<>(productList);
    }

    @Override
    public List<Product> selectProductByShopId(List<Shop> shop) {
        return productMapperImpl.selectProductByShopId(shop);
    }

    @Override
    public List<Shop> selectProductCountByShopId(List<Shop> shops) {
        return productMapperImpl.selectProductCountByShopId(shops);
    }

    @Override
    public List<Mall> selectProductCountByMallId(List<Mall> malls) {
        return productMapperImpl.selectProductCountByMallId(malls);
    }


}
