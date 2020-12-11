package my.qisen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import my.qisen.config.GuiceModule;
import my.qisen.web.UserController;

/**
 * Hello world!
 *
 */
public class GuiceApp
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new GuiceModule());
        UserController userController = injector.getInstance(UserController.class);
        System.out.println(userController.getUser());
    }
}
