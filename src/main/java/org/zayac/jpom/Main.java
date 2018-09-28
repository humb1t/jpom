package org.zayac.jpom;

import static io.javalin.apibuilder.ApiBuilder.path;

import com.github.jasync.sql.db.Configuration;
import com.github.jasync.sql.db.Connection;
import com.github.jasync.sql.db.QueryResult;
import com.github.jasync.sql.db.pool.ConnectionPool;
import com.github.jasync.sql.db.pool.PoolConfiguration;
import com.github.jasync.sql.db.postgresql.pool.PostgreSQLConnectionFactory;
import io.javalin.Javalin;
import io.javalin.JavalinEvent;
import io.javalin.apibuilder.ApiBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zayac.jpom.order.OrderController;
import org.zayac.jpom.order.OrderDao;
import org.zayac.jpom.product.ProductController;
import org.zayac.jpom.product.ProductDao;
import org.zayac.jpom.specification.SpecificationController;
import org.zayac.jpom.specification.SpecificationDao;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        PoolConfiguration poolConfiguration = new PoolConfiguration(
                100,                            // maxObjects
                TimeUnit.MINUTES.toMillis(15),  // maxIdle
                10_000,                         // maxQueueSize
                TimeUnit.SECONDS.toMillis(30)   // validationInterval
        );
        Connection connection = new ConnectionPool<>(
                new PostgreSQLConnectionFactory(new Configuration(
                        "postgres",
                        "localhost",
                        5432,
                        "postgres",
                        "postgres"
                )), poolConfiguration);
        Javalin app = Javalin.create()
                .event(JavalinEvent.SERVER_STARTING, () -> {
                    logger.info("--- SERVER STARTING!");
                    connection.connect().get();
                    logger.info("--- connection STARTED!");
                })
                .event(JavalinEvent.SERVER_STOPPING, () -> {
                    logger.info("--- SERVER STOPPING!");
                    connection.disconnect().get();
                    logger.info("--- connection STOPPED!");
                })
                .start(7000);
        OrderDao orderDao = new OrderDao(connection);
        ProductDao productDao = new ProductDao(connection);
        SpecificationDao specificationDao = new SpecificationDao(connection);
        app.routes(() -> {
            path("orders", () -> {
                ApiBuilder.get(OrderController::getAll);
                ApiBuilder.post(OrderController::create);
                path(":id", () -> {
                    ApiBuilder.get(OrderController::get);
                    ApiBuilder.post("/start", OrderController::start);
                    ApiBuilder.post("/cancel", OrderController::cancel);
                    ApiBuilder.patch(OrderController::update);
                    ApiBuilder.delete(OrderController::delete);
                });
            });
            path("specifications", () -> {
                ApiBuilder.get(SpecificationController::getAll);
                ApiBuilder.post(SpecificationController::create);
                path(":id", () -> {
                    ApiBuilder.get(SpecificationController.get);
                    ApiBuilder.patch(SpecificationController.update);
                    ApiBuilder.delete(SpecificationController.delete);
                });
                path("search", () -> {
                    ApiBuilder.get(SpecificationController.getByName);
                });
            });
            path("products", () -> {
                ApiBuilder.get(ProductController.getAll);
                path(":id", () -> ApiBuilder.get(ProductController.get));
            });
        });
        app.get("/",
                ctx -> {
                    final CompletableFuture<QueryResult> queryResultCompletableFuture = connection.sendPreparedStatement("select 0");
                    ctx.result(
                            queryResultCompletableFuture
                                    .thenApply((t) -> "got result: " + t.getRows().get(0).get(0))
                    );
                }
        );
    }
}