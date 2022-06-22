package cn.wolfcode.service.impl;

import cn.wolfcode.domain.*;
import cn.wolfcode.mapper.MallMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.MallService;
import cn.wolfcode.service.ProductService;
import cn.wolfcode.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MallServiceImpl implements MallService {
    @Autowired
    private MallMapper mallMapperImpl;
    @Autowired
    private ShopService shopServiceImpl;
    @Autowired
    private ProductService productServiceImpl;



    @Override
    public void saveOrUpdate(Mall mall) {
        Long id = mall.getId();
        if(id == null){
            mallMapperImpl.save(mall);
        }else {
            mallMapperImpl.update(mall);
        }
    }

    @Override
    public void deleteById(Long id) {
        mallMapperImpl.deleteById(id);
    }

    @Override
    public Mall selectOne(Long id) {
        return mallMapperImpl.selectOne(id);
    }

    @Override
    public List<Mall> selectAll() {
        List<Mall> malls = mallMapperImpl.selectAll();
        //查询当前商城下面的店铺
        List<Shop> shops = shopServiceImpl.selectShopByMallId(malls);
        Map<Long,List<Shop>> shopMap = new HashMap<>();
        for (Shop shop:shops){
            Long mallId = shop.getMall().getId();
            if (!shopMap.containsKey(mallId)){
                List<Shop> temShopList = new ArrayList<>();
                temShopList.add(shop);
                shopMap.put(mallId,temShopList);
            }
            else {
                List<Shop> temShopList = shopMap.get(mallId);
                temShopList.add(shop);
                shopMap.put(mallId,temShopList);
            }
        }

        List<Mall> mallList = productServiceImpl.selectProductCountByMallId(malls);
        Map<Long, Integer> productCountMap = new HashMap<>();
        for (Mall mall:mallList){
            Long mallId = mall.getId();
            if (!productCountMap.containsKey(mallId)){
                Integer integer = mall.getProductCount();
                productCountMap.put(mallId,integer);
            }
        }



        for (Mall mall:malls){
            List<Shop> temShopList = shopMap.get(mall.getId());
            mall.setShops(temShopList);
            Integer count = productCountMap.get(mall.getId());
            mall.setProductCount(count);
        }
        return malls;

    }

    @Override
    public PageInfo<Mall> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Mall> malls = selectAll();
         return new PageInfo<>(malls);
    }


}
