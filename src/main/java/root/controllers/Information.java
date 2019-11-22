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

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date.*;


import static spark.Spark.*;
public class Information {


    public void informationControllers(){
        get("/dashBoard", (request, response) -> {
            Session session = request.session(true);
            Map<String,Object> urlMap = new HashMap<>();
            User user = session.attribute("user");

            urlMap.put("user",user);
            if(user != null){
                urlMap.put("urls",user.getUrlList());
                return Template.renderFreemarker(urlMap,"/links.ftl");
            }else{
                response.redirect("/");
                return "";
            }
        });
        get("/",(request, response) -> {
            Session session = request.session(true);
            Map<String,Object> urlMap = new HashMap<>();
            urlMap.put("url",request.session().attribute("urls"));
            urlMap.put("user",request.session().attribute("user"));
            return Template.renderFreemarker(urlMap,"/index.ftl");
        });

        get("/link/:id",(request, response) -> {
            String hash = request.params("id");
            User user = request.session().attribute("user");
            List<URL> aux = request.session().attribute("urls");

            if(chechListSession(aux) && user == null){
                for(URL urlaux : aux){
                    if(urlaux.getHash().equals(hash)){
                        response.redirect(urlaux.getUrl());
                        break;
                    }
                }
            }else if(user != null){
                System.out.println("This is hash"+hash);
                URL url = URLServices.getInstance().find(hash);
                System.out.println("URL"+url.getUrl());
                Date date = new Date((new java.util.Date()).getTime());
                Visit visit = new Visit(url,request.userAgent(),request.ip(),request.userAgent(),date);
                url.addVisits(visit);
                UserService.getInstance().update(user);
                VisitServices.getInstance().create(visit);
                response.redirect(url.getUrl());
            }
            return "";

            //return Template.renderFreemarker(null,"/registration.ftl");
        });

        get("/registerUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            System.out.println("nema");
            return Template.renderFreemarker(values,"/registration.ftl");

        });
        post("/register-user", (request, response) -> {
            System.out.println("nema");
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
            User user = request.session().attribute("user");
            Map<String,Object> urlMap = new HashMap<>();
            String url = request.queryParams("url-to-shorter");
            List<URL> urlList = session.attribute("urls");

            if(urlList == null){
                urlList = new ArrayList<>();
                urlList.add((new URL(url)));
                request.session().attribute("urls",urlList);
                urlMap.put("url",request.session().attribute("urls"));
            }else{
                urlList.add((new URL(url)));
                request.session().attribute("urls",urlList);
                urlMap.put("url",request.session().attribute("urls"));
            }

            if(user != null){
                URL urlAux = new URL(url,user);
                user.addURL(urlAux);
                URLServices.getInstance().create(urlAux);
            }

         response.redirect("/");
            return "";


        });
    }


    public boolean chechListSession(List<URL> urls){
        return (urls == null) ? false:true;
    }


}
