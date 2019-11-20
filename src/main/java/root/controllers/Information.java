package root.controllers;

import root.Services.URLServices;
import root.Services.UserService;
import root.Services.VisitServices;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.URLShortener.Visit;
import spark.Session;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static spark.Spark.*;
public class Information {

    public void informationControllers(){
        get("/",(request, response) -> {
            Session session = request.session(true);
            return Template.renderFreemarker(null,"/index.ftl");
        });

        get("/:id",(request, response) -> {
            String hash = request.params("id");
            User user = request.session().attribute("user");
            List<URL> aux = request.session().attribute("urls");
            System.out.println(hash);
            System.out.println(aux.get(0).getUrl());

            if(chechListSession(aux) && user == null){
                for(URL urlaux : aux){
                    if(urlaux.getHash().equals(hash)){
                        response.redirect(urlaux.getUrl());
                        break;
                    }
                }
            }
            return "";

            //return Template.renderFreemarker(null,"/registration.ftl");
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
            Session session = request.session();
            Map<String,Object> urlMap = new HashMap<>();
            String url = request.queryParams("url-to-shorter");
            List<URL> urlList = session.attribute("urls");
//ya, sigiue
            if(urlList == null){
                urlList = new ArrayList<>();
                urlList.add((new URL(url)));
                request.session().attribute("urls",urlList);
                System.out.println("Lista"+request.session().attribute("urls"));
                urlMap.put("url",request.session().attribute("urls"));
                System.out.println("hello");
            }else{
                urlList.add((new URL(url)));
                request.session().attribute("urls",urlList);
                urlMap.put("url",request.session().attribute("urls"));
                System.out.println("hola");
            }

         return Template.renderFreemarker(urlMap,"/index.ftl");


        });
    }

    public URL record(User user,String url,String browser,String ip, String os){
        URL newUrl = new URL(url,user);
        URLServices.getInstance().create(newUrl);
        user.addURL(newUrl);
        VisitServices.getInstance().create((new Visit(newUrl,browser,ip,os)));
        return newUrl;
    }
    public boolean chechListSession(List<URL> urls){
        return (urls == null) ? false:true;
    }


}
