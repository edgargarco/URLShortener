package root.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jasypt.util.text.BasicTextEncryptor;
import root.Services.UserService;
import root.URLShortener.User;
import root.api.ErrorApi;
import root.api.JSONUtils;
import root.api.JWT;
import spark.Session;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Authentication {
    public static final String encryptPass = "admin";

    public void authentication(){
        get("/login",(request,response)->{
            if (request.session().attribute("user") == null){
                return Template.renderFreemarker(null,"/login.ftl");
            }
            response.redirect("/");
            return "";
        });

        // /login?username=xxxx&password=xxxx
        post("/login", (request, response) -> {
            response.type("application/json");
            String username = request.queryParamOrDefault("username", "");
            String password = request.queryParamOrDefault("password", "");
            User user = UserService.getInstance().find(username);
            if(user != null && user.getPassword().equals(password)) {
                return generateJWT(user);
            }else{
                response.status(Information.UNAUTHORIZED);
                return new ErrorApi(Information.UNAUTHORIZED, "Bad credentials, access deny!");
            }
        }, JSONUtils.json());

        post("/authenticate",(request, response) -> {
            Map<String, Object> val = new HashMap<>();
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean rememberMe = (request.queryParams("remember") == null) ?false:true;
            User user = UserService.getInstance().find(username);
            if (user != null){
                if (user.getPassword().equals(password)){
                    if (request.session() != null) {
                        request.session().invalidate();
                    }
                    Session session = request.session(true);
                    session.attribute("user", user);
                    if (rememberMe){
                        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
                        basicTextEncryptor.setPasswordCharArray(encryptPass.toCharArray());
                        response.cookie("user",basicTextEncryptor.encrypt(user.getUsername()),30,false);
                    }
                    System.out.println("Usuario " + user.getUsername() + " a entrado al sistema!");
                    response.redirect("/");
                }else {
                    System.out.println("Usuario " + user.getUsername() + " ingreso una contraseña incorrecta!");
                    val.put("error","Contraseña incorrecta!");
                }
            } else {
                System.out.println("Intento de iniciar sesion con usuario " + username + " y password " + password);
                val.put("error", "Nombre de usuario invalido!");
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

    private static JWT generateJWT(User user){
        //Generating the key
        SecretKey secretKey = Keys.hmacShaKeyFor(Information.KEY.getBytes());
        //Valid date
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);

        //Generate the JWT (frame)
        String jwt = Jwts.builder()
                .setIssuer("PUCMM-PW-GROUP-4")
                .setSubject("URL Shortener API's")
                .setExpiration(Date.from(localDateTime.toInstant(ZoneOffset.ofHours(-4))))
                .claim("username", user.getUsername())
                .claim("admin", user.isAdministrator())
                .signWith(secretKey)
                .compact();
        return new JWT(jwt);
    }
}