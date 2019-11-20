package root.controllers;
import org.jasypt.util.text.BasicTextEncryptor;
import root.Services.UserService;
import root.URLShortener.URL;
import root.URLShortener.User;
import spark.Session;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
public class Filter {
    public void filters(){
        before((request, response) -> {
            User user = request.session().attribute("user");
            if (user == null){
                if (request.cookie("user") != null){
                    System.out.println("Montro");
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

    }

}
