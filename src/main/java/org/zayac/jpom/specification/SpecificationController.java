package org.zayac.jpom.specification;

import io.javalin.Handler;

public class SpecificationController {
    public static Handler getByName = context -> {
        final String name = context.queryParam("name");
        context.json(
                SpecificationDao.getByName(name)
        );
    };
    public static Handler delete = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        SpecificationDao.delete(id);
    };
    public static Handler update = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                SpecificationDao.update(
                        id,
                        context.bodyAsClass(Specification.class)
                )
        );
    };
    public static Handler get = context -> {
        final Long id = Long.valueOf(context.pathParam("id"));
        context.json(
                SpecificationDao.getById(id)
        );
    };
    public static Handler create = context -> {
        context.json(
                SpecificationDao.create(
                        context.bodyAsClass(Specification.class)
                )
        );
    };
    public static Handler getAll = context -> {
        context.json(
                SpecificationDao.getAll()
        );
    };
}
