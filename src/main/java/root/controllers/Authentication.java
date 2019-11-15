package root.controllers;
import org.jasypt.util.text.BasicTextEncryptor;
import root.Services.UserService;
import root.URLShortener.User;
import spark.Session;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Authentication {
    public static final String encryptPass = "admin";

    public void authentication(){
        get("/login",(request,response)->{
            return Template.renderFreemarker(null,"/login");
        });

        post("/authenticate",(request, response) -> {
            Map<String,Object> val = new HashMap<>();
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean rememberMe = (request.queryParams("remember") == null) ?false:true;
            User user = UserService.getInstance().find(username);
            if (user != null){
                if (user.getPassword().equals(password)){
                    Session session = request.session(true);
                    session.attribute("user",user);
                    if (rememberMe){
                        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                        basicTextEncryptor.setPasswordCharArray(encryptPass.toCharArray());
                        response.cookie("user",basicTextEncryptor.encrypt(user.getUsername()),600000,false);

                    }
                    response.redirect("/");
                    halt();
                }else {
                    val.put("incorrectPassword","Contraseña incorrecta!");
                }
            }else{
                val.put("userNotFound", "Nombre de usuario invalido!");
            }
            val.put("username", username);
            val.put("password", password);
            return Template.renderFreemarker(val,"/login.ftl");
        });

        get("/closeSession", (request, response) -> {
            Session session = request.session();
            if (session != null){
                session.invalidate();
                response.removeCookie("user");
                response.redirect("/");
            }
            return "";
        });
    }
}
