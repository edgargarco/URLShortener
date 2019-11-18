package root.controllers;
import com.google.gson.JsonObject;
import org.jasypt.util.text.BasicTextEncryptor;
import root.Services.URLServices;
import root.Services.UserService;
import root.Services.VisitServices;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.URLShortener.Visit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;

import static spark.Spark.*;
public class Information {

    public void informationControllers(){
        get("/",(request, response) -> {

            return Template.renderFreemarker(null,"/index.ftl");
        });

        get("/registerUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            return Template.renderFreemarker(values,"registration.ftl");
        });
        post("/register-user", (request, response) -> {
            Map<String, Object> values = new HashMap<>();
            User user = UserService.getInstance().find(request.queryParams("username"));
            if(user == null){
                UserService.getInstance().create((new User(request.queryParams("username"),request.queryParams("name"),request.queryParams("password"))));;
                response.redirect("/");
                return "";
            }else{
                values.put("error","User not found");
                return Template.renderFreemarker(values,"/registration.ftl");

            }

        });

        post("/url",(request, response) -> {
            Map<String,String> map = new HashMap<>();
            User user = request.session().attribute("user");
            String url = request.queryParams("url");

            if(user!= null){
                String browser = request.headers("User-Agent");
                String ip = request.ip();
                String[] os = request.headers("User-Agent").split(" ");
                URL url1 =  record(user,url,browser,ip,os[1]);
                return url1.getHash();

            }else{
                return (new URL(url)).getHash();
            }


        });
    }

    public URL record(User user,String url,String browser,String ip, String os){
        URL newUrl = new URL(url,user);
        URLServices.getInstance().create(newUrl);
        user.addURL(newUrl);
        VisitServices.getInstance().create((new Visit(newUrl,browser,ip,os)));
        return newUrl;
    }


}
