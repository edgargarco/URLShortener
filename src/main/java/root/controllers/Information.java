package root.controllers;

import org.json.JSONArray;
import root.Services.*;
import root.URLShortener.*;
import spark.Session;
import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;

import java.time.LocalDate;
import java.util.*;

import static spark.Spark.*;

public class Information {
    public static final String DOMAIN = "localhost:4567";
    public final static int UNAUTHORIZED = 401;
    public final static int NOT_FOUND = 404;

    public void informationControllers(){

        get("/listAllUrl",(request, response) -> {
            if (request.session().attribute("user") != null){
                List<URL> urlList = URLServices.getInstance().findAll();
                Map<String,Object> urlMap = new HashMap<>();
                urlMap.put("urls",urlList);
                urlMap.put("domain", DOMAIN);
                urlMap.put("user", request.session().attribute("user")); 
                return Template.renderFreemarker(urlMap,"allURL.ftl");
            }else {
                response.redirect("/dashBoard");
                return "";
            }


        });


        get("/",(request, response) -> {
            Map<String, Object> urlMap = new HashMap<>();
            urlMap.put("domain", DOMAIN);
            urlMap.put("urls", request.session().attribute("urls"));
            urlMap.put("user", request.session().attribute("user"));
            return Template.renderFreemarker(urlMap,"/index.ftl");
        });

        post("/url",(request, response) -> {
            Session session = request.session();
            User user = session.attribute("user");
            String url = request.queryParams("url-to-shorter");
            List<Object> urlList = session.attribute("urls");
            boolean statusList = (urlList == null)?false:true;
            if (user != null){
                if (URLServices.getInstance().checkURL(url)){
                    URL urlAux = new URL(url, user);
                    URLServices.getInstance().create(urlAux);
                    if (statusList){
                        if (!URLServices.getInstance().checkURLExistence(urlList, url)){
                            urlList.add(urlAux);
                        }
                    } else {
                        urlList = new ArrayList<>();
                        urlList.add(urlAux);
                    }
                }
            } else {
                TempURL tempURL = new TempURL(url, request.cookies().toString());
                TempURLServices.getInstance().create(tempURL);
                if (statusList){
                    if (!URLServices.getInstance().checkURLExistence(urlList, url)){
                        urlList.add(tempURL);
                    }
                }else{
                    urlList = new ArrayList<>();
                    urlList.add(tempURL);
                }
            }
            session.attribute("urls", urlList);
            response.redirect("/");
            return "";
        });

        get("/deleteUser/:id", (request, response) -> {
            User user = request.session().attribute("user");
            if (user.isAdministrator()){
                User auxUser = UserService.getInstance().find(request.params("id"));
                if (auxUser.getUsername().equals("admin")){
                    request.session().attribute("error","Usuario administrador no puede ser eliminado");
                }else{
                    request.session().removeAttribute("error");
                    UserService.getInstance().delete(auxUser.getUsername());
                    System.out.println("Usuario " + auxUser.getUsername() + " eliminado del sistema por " + user.getUsername() +"!");
                }
                response.redirect("/listUsers");
            } else {
                //401 Unathorized
                response.status(UNAUTHORIZED);
                return Template.renderFreemarker(message("Stoop!", UNAUTHORIZED + "", "You aren't administrator!", "Go to homepage", "/"),"/message.ftl");
            }
            return "";
        });

        post("/editUser", (request, response) -> {
            String name = request.queryParams("name");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            Boolean isAdmin = (request.queryParams("materialUncheckedAdmin") == null)?false:Boolean.parseBoolean(request.queryParams("materialUncheckedAdmin"));
            if (!username.equals("admin")){
                User user =  UserService.getInstance().find(username);
                user.setName((!name.equals(""))?name:user.getName());
                user.setPassword((!password.equals(""))?password:user.getPassword());
                user.setAdministrator(isAdmin);
                UserService.getInstance().update(user);
            }
            response.redirect("/listUsers");
            return "";
        });

        get("/listUsers",(request, response) -> {
            User user = request.session().attribute("user");
            Map<String, Object> userMap = new HashMap<>();
            List<User> users = UserService.getInstance().findAll();
            userMap.put("users", users);
            userMap.put("user", user);
            userMap.put("error", request.session().attribute("error"));
            if (user != null){
                return Template.renderFreemarker(userMap,"/getUsers.ftl");
            }
            response.redirect("/dashBoard");
            return "";
        });

        get("/delete-link/:hash",(request, response) -> {
            User user = request.session().attribute("user");
            if (user != null && user.isAdministrator()){
                URL url = URLServices.getInstance().find(request.params("hash"));
                if (url != null){
                    VisitServices.getInstance().deleteVisits(request.params("hash"));
                    URLServices.getInstance().delete(request.params("hash"));
                    request.session().attribute("urls", user.urltoGet(user));
                }
            } else {
                //401 Unathorized
                response.status(UNAUTHORIZED);
                return Template.renderFreemarker(message("Stoop!", UNAUTHORIZED + "", "You aren't administrator!", "Go to dashboard", "/dashBoard"),"/message.ftl");
            }
            response.redirect("/dashBoard");
            return "";
        });

        post("/getStatisctics",(request, response) -> {
            Session session = request.session();
            User user = session.attribute("user");
            int[] visitsPerHour = new int[24];
           if (user != null){
               if (VisitServices.getInstance().sanitizeDate(request.queryParams("date"))){
                   List<Visit> visits = VisitServices.getInstance().visitsByDates(request.queryParams("hash"),dates(request.queryParams("date")));
                   if (visits != null){
                       int visitsCount = 0 ;
                       for(int i = 0 ; i < 24 ; i++){
                           for (int j = 0 ; j < visits.size() ; j++){
                               if (visits.get(j).getTime().getHour() == i ){
                                   visitsCount++;
                               }
                           }
                           visitsPerHour[i] = visitsCount;
                           visitsCount = 0;
                       }
                   }
               }
           }else{
               if (VisitServices.getInstance().sanitizeDate(request.queryParams("date"))){
                   List<TempVisits> tempVisits = TempVisitsServices.getInstance().visitsByDates(request.queryParams("hash"),dates(request.queryParams("date")));
                   if (tempVisits != null){
                       int visitsCount = 0 ;
                       for(int i = 0 ; i < 24 ; i++){
                           for (int j = 0 ; j < tempVisits.size() ; j++){
                               if (tempVisits.get(j).getTime().getHour() == i ){
                                   visitsCount++;
                               }
                           }
                           visitsPerHour[i] = visitsCount;
                           visitsCount = 0;
                       }
                   }
               }


           }
            JSONArray jsonArray = new JSONArray(visitsPerHour);
            return jsonArray;
        });

        get("/info/:id",(request, response) -> {
            Map<String,Object> urlMap = new HashMap<>();
            urlMap.put("domain", DOMAIN);
            Session session = request.session();
            String hash = request.params("id");
            User user = session.attribute("user");
            if (user != null){
                urlMap.put("demographicsURL",URLServices.getInstance().findURLCustomMethod(hash));
                urlMap.put("LinuxCant",VisitServices.getInstance().getByOs(hash,"Linux"));
                urlMap.put("WindowsCant",VisitServices.getInstance().getByOs(hash,"Windows"));
                urlMap.put("IOSCant",VisitServices.getInstance().getByOs(hash,"iOS"));
                urlMap.put("AndroidCant",VisitServices.getInstance().getByOs(hash,"Android"));
                urlMap.put("ips",VisitServices.getInstance().getIPS(hash));
                urlMap.put("user",user);
                return Template.renderFreemarker(urlMap,"/dashboard.ftl");
            }else{
                List<TempURL> tempURLS = request.session().attribute("urls");
                if (tempURLS != null){
                    for (TempURL tempURL : tempURLS){
                        if (tempURL.getHash().equals(hash)){
                            urlMap.put("demographicsURL",TempURLServices.getInstance().find(hash));
                            urlMap.put("LinuxCant",TempVisitsServices.getInstance().getByOs(hash,"Linux"));
                            urlMap.put("WindowsCant",TempVisitsServices.getInstance().getByOs(hash,"Windows"));
                            urlMap.put("IOSCant",TempVisitsServices.getInstance().getByOs(hash,"iOS"));
                            urlMap.put("AndroidCant",TempVisitsServices.getInstance().getByOs(hash,"Android"));
                            urlMap.put("ips",TempVisitsServices.getInstance().getIPS(hash));
                            return Template.renderFreemarker(urlMap,"/dashboard.ftl");
                        }
                    }
                }else{
                    return Template.renderFreemarker(urlMap,"/links.ftl");
                }
            }
            return "";
        });

        get("/dashBoard", (request, response) -> {
            Map<String, Object> urlMap = new HashMap<>();
            User user = request.session().attribute("user");
            urlMap.put("user",user);
            urlMap.put("domain", DOMAIN);
            if(user != null){
                List<URL> urlList = user.urltoGet(user);
                urlMap.put("urls", urlList);
            }else{
                urlMap.put("urls", request.session().attribute("urls"));
            }
            return Template.renderFreemarker(urlMap,"/links.ftl");
        });

        get("/registerUser",(request, response) -> Template.renderFreemarker(null,"/registration.ftl"));

        post("/register-user", (request, response) -> {
            Map<String, Object> values = new HashMap<>();
            String username = request.queryParams("username");
            String name = request.queryParams("name");
            String password = request.queryParams("password");
            values.put("username", username);
            values.put("name", name);
            values.put("password", password);
            User user = UserService.getInstance().find(username);
            if(user == null){
                if (!username.equals("") && !name.equals("") && !password.equals("")) {
                    UserService.getInstance().create((new User(username, name, password)));
                    user = UserService.getInstance().find(request.queryParams("username"));
                    request.session(true).attribute("user", user);
                    System.out.println("Usuario " + user.getUsername() + " a entrado al sistema!");
                    response.redirect("/");
                } else {
                    values.put("complete","Bad credentials");
                    return Template.renderFreemarker(values,"/registration.ftl");
                }
            } else {
                values.put("error","User found");
                return Template.renderFreemarker(values,"/registration.ftl");
            }
            return "";
        });

        //This controller needs to be the last one
        get("/:id",(request, response) -> {
            String hash = request.params("id");
            URL url = URLServices.getInstance().find(hash);
            UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
            UserAgent userAgent = new UserAgent();
            userAgent.printUa(parser.parse(request.userAgent()));
            String urlResponse = "";
            if (url == null) {
                TempURL tempURL = TempURLServices.getInstance().find(hash);
                if(tempURL != null){
                    TempVisits tempVisits = new TempVisits(tempURL,userAgent.getBrowser(),request.ip(),userAgent.getOs(),userAgent.getDeviceType());
                    TempVisitsServices.getInstance().create(tempVisits);
                    urlResponse = tempURL.getUrl();
                } else {
                    //404 NOT FOUND
                    response.status(NOT_FOUND);
                    return Template.renderFreemarker(message("Oops!", NOT_FOUND + "", "The Page can't be found", "Go to homepage", "/"),"/message.ftl");
                }
            } else {
                Visit visit = new Visit(url,userAgent.getBrowser(),request.ip(),userAgent.getOs(),userAgent.getDeviceType());
                VisitServices.getInstance().create(visit);
                urlResponse = url.getUrl();
            }
            response.redirect(urlResponse);
            return "";
        });
    }

    public LocalDate dates(String date){
        String formatDate = date.substring(6,10) + "-"+date.substring(0,2)+"-"+date.substring(3,5);
        LocalDate localDate = LocalDate.parse(formatDate);
        return localDate;
    }

    public Map<String, Object> message(String adv, String status, String errorMessage, String buttonMessage, String returnPath) {
        Map<String, Object> map = new HashMap<>();
        map.put("adv", adv);
        map.put("code", status);
        map.put("error", errorMessage);
        map.put("button", buttonMessage);
        map.put("returnPath", returnPath);
        return map;
    }
}
