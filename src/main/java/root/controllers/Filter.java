package root.controllers;
import org.jasypt.util.text.BasicTextEncryptor;
import root.Services.UserService;
import root.URLShortener.User;
import spark.Session;

import static spark.Spark.*;
public class Filter {
    public static void filters(){
        before((request, response) -> {
            User user = request.session().attribute("user");
            if (user == null){
                if (request.cookie("user") != null){
                    String username = request.cookie("user");
                    BasicTextEncryptor  basicTextEncryptor = new BasicTextEncryptor();
                    basicTextEncryptor.setPasswordCharArray(Authentication.encryptPass.toCharArray());
                    User aux = UserService.getInstance().find(basicTextEncryptor.decrypt(username));
                    if (aux != null){
                        Session session = request.session(true);
                        session.attribute("user",aux) ;
                    }else{
                        response.removeCookie("user");
                    }
                }
            }
        });

        before("/deleteUser/*", (request, response) -> {
            User user = request.session().attribute("user");
            if (user == null) {
                response.redirect("/login");
                halt(301);
            }
        });
    }

}
