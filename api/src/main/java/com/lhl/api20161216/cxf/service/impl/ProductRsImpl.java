package com.lhl.api20161216.cxf.service.impl;

import com.lhl.api20161216.cxf.service.ProductRs;
import com.lhl.api20161216.cxf.vo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lunhengle on 2016/12/22.
 */
@Service("productRs")
public class ProductRsImpl implements ProductRs {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(ProductRsImpl.class);
    private static Map<String, Product> map = new HashMap<>();

    static {
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setId("id" + i);
            product.setName("名字" + i);
            product.setLevel("等级" + i);
            product.setType("类型" + i);
            map.put(product.getId(), product);
        }

    }

    /**
     * 获取单个产品
     *
     * @param id id
     * @return 产品
     */
    @Override
    public Product getProductById(String id) {
        logger.info("根据id 获取产品！");
        return map.get(id);
    }

    /**
     * 根据id和名字获取产品.
     *
     * @param id   id
     * @param name 名字
     * @return 产品
     */
    @Override
    public Product getProductByIdAndName(String id, String name) {
        logger.info("根据id和name 获取产品");
        return map.get(id);
    }

    /**
     * 更新 产品.
     *
     * @param product 产品
     */
    @Override
    public void updateProduct(Product product) {
        logger.info("更新过后的资源：" + product.getId() + product.getName() + product.getLevel() + product.getType());
        map.put(product.getId(), product);
    }

    /**
     * 添加产品.
     *
     * @param product 产品
     */
    @Override
    public void addProduct(Product product) {
        logger.info("添加的资源：" + product.getId() + product.getName() + product.getLevel() + product.getType());
        map.put(product.getId(), product);
    }

    /**
     * 删除产品.
     *
     * @param id id
     */
    @Override
    public void deleteProductById(String id) {
        logger.info("删除资源：" + id);
        map.remove(id);
    }

    /**
     * 获取 所有产品.
     *
     * @return 产品
     */
    @Override
    public List<Product> getProduct() {
        logger.info("获取所有产品！");
        return new ArrayList<>(map.values());
    }
}
