package my.qisen.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Singleton;
import com.google.inject.internal.SingletonScope;
import com.google.inject.name.Names;
import my.qisen.service.UserService;
import my.qisen.service.impl.UserServiceImpl;
import my.qisen.web.UserController;

/**
 * @description:
 * @author: angbeats
 * @create: 2020-12-10 10:38
 **/

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
        bind(UserController.class);
        bind(String.class)
                .annotatedWith(Names.named("JDBC URL"))
                .toInstance("jdbc:mysql://localhost:3306/test");
    }
}