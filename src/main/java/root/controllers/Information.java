package root.controllers;

import net.sf.uadetector.ReadableUserAgent;
import root.Services.URLServices;
import root.Services.UserService;
import root.Services.VisitServices;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.URLShortener.Visit;
import spark.Session;
import root.controllers.UserAgent;
import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date.*;


import static spark.Spark.*;
public class Information {


    public void informationControllers(){
        get("/info/:id",(request, response) -> {
            Map<String,Object> urlMap = new HashMap<>();
            Session session = request.session(true);
            String hash = request.params("id");
            System.out.println(hash);
            User user = session.attribute("user");
            if (user != null){
                urlMap.put("demographicsURL",URLServices.getInstance().find(hash));
                return Template.renderFreemarker(urlMap,"/dashboard.ftl");
            }else{
                response.redirect("/");
            }
            return "";
        });
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
                URL url = URLServices.getInstance().find(hash);
                Date date = new Date((new java.util.Date()).getTime());
                UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
                UserAgent userAgent = new UserAgent();
                userAgent.printUa(parser.parse(request.userAgent()));
                Visit visit = new Visit(url,userAgent.getBrowser(),request.ip(),userAgent.getOs(),date,userAgent.getDeviceType());
                url.addVisits(visit);
                VisitServices.getInstance().create(visit);
                response.redirect(url.getUrl());
            return "";
        });

        get("/registerUser",(request, response) -> {
            Map<String, Object> values = new HashMap<>();
            return Template.renderFreemarker(values,"/registration.ftl");

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
