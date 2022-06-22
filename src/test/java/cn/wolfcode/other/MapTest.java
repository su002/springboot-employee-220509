package cn.wolfcode.other;


import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.service.EmployeeService;
import cn.wolfcode.service.ProductService;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapTest {
    @Autowired
    private EmployeeService employeeServiceImpl;
    @Autowired
    private ProductService productServiceImpl;


    /**
     * 需求：一个Key对应多个员工
     */
    @Test
    public void testStaticLetterCount(){

        Map<String, List<Employee>> map = new HashMap<>();
        List<Employee> emp1 = new ArrayList<>();
        emp1.add(employeeServiceImpl.selectOne(2L));
        emp1.add(employeeServiceImpl.selectOne(3L));
        emp1.add(employeeServiceImpl.selectOne(4L));

        List<Employee> emp2 = new ArrayList<>();
        emp2.add(employeeServiceImpl.selectOne(5L));
        emp2.add(employeeServiceImpl.selectOne(6L));
        emp2.add(employeeServiceImpl.selectOne(7L));

        map.put("102",emp1);
        map.put("103",emp2);

        System.out.println(map);



    }

    /**
     * 统计字符串出现的次数
     *  String str = "abcabcasdasffffdaewre";
     */
    @Test
    public void test(){
        String str = "abcabcasdasffffdaewre";
        //将字符串转化成字符类型
        char[] chars = str.toCharArray();
        //字符串转化成数组[a,b,c,a,b........]
        //String[] strings = str.split("");
        Map<Character,Integer> map = new HashMap<>();
        for (char ch:chars){
            if (map.containsKey(ch)){
                Integer count = map.get(ch);
                map.put(ch,count+1);
            }
            else {
                map.put(ch,1);
            }
        }
        System.out.println(map);
    }

    /**
     * 需求：集合转Map
     * [Product1,Product2,good3]
     * key:shopId
     * vlaue:
     */

    public List<Product> initProduct(){
        //华为旗舰店。两个商品 。
        Shop hwShop = new Shop();
        hwShop.setId(1L);

        Product hwProduct1 = new Product();
        hwProduct1.setId(1L);
        hwProduct1.setName("hw手机");
        hwProduct1.setShop(hwShop);

        Product hwProduct2 = new Product();
        hwProduct2.setId(2L);
        hwProduct2.setName("hw手机");
        hwProduct2.setShop(hwShop);

        //小米旗舰店。两个商品。
        Shop xmShop = new Shop();
        xmShop.setId(2L);

        Product xmProduct1 = new Product();
        xmProduct1.setId(3L);
        xmProduct1.setName("xm手机1");
        xmProduct1.setShop(xmShop);

        Product xmProduct2 = new Product();
        xmProduct2.setId(4L);
        xmProduct2.setName("xm手机2");
        xmProduct2.setShop(xmShop);

        //添加到集合中
        List<Product> ProductList = new ArrayList<>();
        ProductList.add(hwProduct1);
        ProductList.add(hwProduct2);
        ProductList.add(xmProduct1);
        ProductList.add(xmProduct2);

        return ProductList;
    }
    
    @Test
    public void testStaticProductCount(){
        List<Product> productList = initProduct();
        Map<Long,List<Product>> map = new HashMap<>();
        List<Product> products;
        for (Product product:productList){
            if(map.containsKey(product.getShop().getId())){
                products = map.get(product.getShop().getId());
                products.add(product);
                map.put(product.getShop().getId(),products);
            }
            else {
                products = new ArrayList<>();
                products.add(product);
                map.put(product.getShop().getId(),products);
            }
        }
        System.out.println(map);
    }


}
