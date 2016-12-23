package com.lhl.api20161216.cxf.service;



import com.lhl.api20161216.cxf.vo.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by lunhengle on 2016/12/22.
 */
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public interface ProductRs {
    /**
     * 获取单个产品
     *
     * @param id id
     * @return 产品
     */
    @GET
    @Path(value = "/get/{id}")
    Product getProductById(@PathParam(value = "id") String id);

    /**
     * 根据id和名字获取产品.
     *
     * @param id   id
     * @param name 名字
     * @return 产品
     */
    @GET
    @Path(value = "/get/{id}/{name}")
    Product getProductByIdAndName(@PathParam(value = "id") String id, @PathParam(value = "name") String name);

    /**
     * 更新 产品.
     *
     * @param product 产品
     */
    @PUT
    @Path(value = "/update")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateProduct(Product product);

    /**
     * 添加产品.
     *
     * @param product 产品
     */
    @POST
    @Path(value = "/add")
    @Consumes(MediaType.APPLICATION_JSON)
    void addProduct(Product product);

    /**
     * 删除产品.
     *
     * @param id id
     */
    @DELETE
    @Path(value = "/delete/{id}")
    void deleteProductById(@PathParam(value = "id") String id);

    /**
     * 获取 所有产品.
     *
     * @return 产品
     */
    @GET
    @Path(value = "/get/products")
    List<Product> getProduct();
}
