package org.zayac.jpom.order;

import io.javalin.Handler;

public class OrderController {
    public static Handler getAll = context -> {
        context.json(
                OrderDao.getAll()
        );
    };
    public static Handler create = context -> {
        context.json(
                OrderDao.create(
                        context.bodyAsClass(Order.class)
                )
        );
    };
    public static Handler get = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                OrderDao.getById(id)
        );
    };
    public static Handler start = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                OrderDao.update(
                        id, OrderDao.getById(id).start()
                )
        );
    };
    public static Handler cancel = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                OrderDao.update(
                        id, OrderDao.getById(id).cancel()
                )
        );
    };
    public static Handler update = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                OrderDao.update(
                        id,
                        context.bodyAsClass(Order.class)
                )
        );
    };
}
