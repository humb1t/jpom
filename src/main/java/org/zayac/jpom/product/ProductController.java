package org.zayac.jpom.product;

import io.javalin.Handler;

public class ProductController {
    public static Handler getAll = context -> {
        context.json(ProductDao.getAll());
    };
    public static Handler get = context -> {
        Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                ProductDao.getById(id)
        );
    };
}
