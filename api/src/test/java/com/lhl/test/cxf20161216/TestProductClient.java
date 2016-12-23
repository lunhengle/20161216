package com.lhl.test.cxf20161216;

import com.lhl.api20161216.cxf.vo.Product;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunhengle on 2016/12/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/rs/cxf/applicationContext-rs-client.xml"})
public class TestProductClient {
    private static Logger logger = LoggerFactory.getLogger(TestPersonClient.class);

    private static String baseAddress = "http://localhost:8080/webservice";
    private static List<Object> providerList = new ArrayList();

    @Before
    public void setup() {
        providerList.add(new JacksonJsonProvider());
    }


    /**
     * 根据id 获取产品.
     */
    @Test
    public void testProductById() {
        Product product = WebClient.create(baseAddress, providerList).path("/product/get/id2").accept(MediaType.APPLICATION_JSON).get(Product.class);
        logger.info(" id = " + product.getId() + " name = " + product.getName() + " level = " + product.getLevel() + " type = " + product.getType());
    }

    /**
     * 根据id和name获取产品.
     */
    @Test
    public void testProductByIdAndName() {
        Product product = WebClient.create(baseAddress, providerList).path("/product/get/id2/姓名2").accept(MediaType.APPLICATION_JSON).get(Product.class);
        logger.info(" id = " + product.getId() + " name = " + product.getName() + " level = " + product.getLevel() + " type = " + product.getType());
    }

    /**
     * 获取产品列表.
     */
    @Test
    public void testProducts() {
        List<Product> products = WebClient.create(baseAddress, providerList).path("/product/get/products").accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Product>>() {
        });
        for (Product product : products) {
            logger.info(" id = " + product.getId() + " name = " + product.getName() + " level = " + product.getLevel() + " type = " + product.getType());
        }
    }

    /**
     * 测试更新.
     */
    @Test
    public void testUpdateProduct() {
        Product product = WebClient.create(baseAddress, providerList).path("/product/get/id2").accept(MediaType.APPLICATION_JSON).get(Product.class);
        logger.info("更新前！");
        logger.info(" id = " + product.getId() + " name = " + product.getName() + " level = " + product.getLevel() + " type = " + product.getType());
        product.setName("lunhengle");
        WebClient.create(baseAddress, providerList).path("/product/update").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).put(product, Product.class);
        product = WebClient.create(baseAddress, providerList).path("/product/get/id2").accept(MediaType.APPLICATION_JSON).get(Product.class);
        logger.info("更新后！");
        logger.info(" id = " + product.getId() + " name = " + product.getName() + " level = " + product.getLevel() + " type = " + product.getType());
    }

    /**
     * 测试增加.
     */
    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setId("id5");
        product.setName("名字5");
        product.setLevel("等级5");
        product.setType("类型5");
        WebClient.create(baseAddress, providerList).path("/product/add").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(product, Product.class);
        product = WebClient.create(baseAddress, providerList).path("/product/get/id5").accept(MediaType.APPLICATION_JSON).get(Product.class);
        logger.info("更新后！");
        logger.info(" id = " + product.getId() + " name = " + product.getName() + " level = " + product.getLevel() + " type = " + product.getType());
    }

    /**
     * 删除资源.
     */
    @Test
    public void testDeleteProdcut() {
        WebClient.create(baseAddress, providerList).path("/product/delete/id1").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).delete();
        Product product = WebClient.create(baseAddress, providerList).path("/product/get/id1").accept(MediaType.APPLICATION_JSON).get(Product.class);
        Assert.assertEquals("删除成功!", null, product);
    }

}
