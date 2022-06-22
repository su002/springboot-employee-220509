package cn.wolfcode.service.impl;


import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Warehouse;
import cn.wolfcode.mapper.WarehouseMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.ProductService;
import cn.wolfcode.service.ShopService;
import cn.wolfcode.service.WarehouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapperImpl;
    @Autowired
    private ShopService shopServiceImpl;
    @Autowired
    ProductService productServiceImpl;


    @Override
    public void saveOrUpdate(Warehouse warehouse) {
        Long id = warehouse.getId();
        if(id == null){
            warehouseMapperImpl.save(warehouse);
        }else {
            warehouseMapperImpl.update(warehouse);
        }
    }

    @Override
    public void deleteById(Long id) {
        warehouseMapperImpl.deleteById(id);
    }

    @Override
    public Warehouse selectOne(Long id) {
        return warehouseMapperImpl.selectOne(id);
    }

    @Override
    public List<Warehouse> selectAll() {
        return warehouseMapperImpl.selectAll();
    }

    @Override
    public PageInfo<Warehouse> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Warehouse> warehouses = warehouseMapperImpl.selectAll();
        return new PageInfo<>(warehouses);
    }

    @Override
    public List<Warehouse> selectWarehouseByProduceId(List<Product> products) {
        return null;
    }


}
