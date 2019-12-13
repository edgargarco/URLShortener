package root.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import root.Services.URLServices;
import root.Services.UserService;
import root.URLShortener.Statistics;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.controllers.Information;

import java.util.List;

import static spark.Spark.*;
import static spark.Spark.halt;

public class RestApi {
    private final int expiredJWT = 1000;

    public void restApis() {
        path("/rest", () -> {

            before("/*", (request, response) ->{
                //Header for the token
                String header = "Authorization";
                String prefix = "Bearer ";

                //Verifying if the header exists
                String jwt = request.headers(header);
                if(jwt == null || !jwt.startsWith(prefix)){
                    halt(Information.FORBIDDEN, JSONUtils.toJson(new ErrorApi(Information.FORBIDDEN, "Bad request! You can't access!")));
                } else {
                    jwt = jwt.replace(prefix, "");
                    Claims claims = null;
                    try {
                        claims = Jwts.parser()
                                .setSigningKey(Keys.hmacShaKeyFor(Information.KEY.getBytes()))
                                .parseClaimsJws(jwt).getBody();
                    }catch (ExpiredJwtException | MalformedJwtException | SignatureException e){ //Excepciones comunes
                        halt(Information.FORBIDDEN, JSONUtils.toJson(new ErrorApi(expiredJWT, e.getMessage())));
                    }
                    System.out.println("JWT received: " + claims.toString());
                    String username = claims.get("username").toString();
                    Boolean isAdmin = Boolean.parseBoolean(claims.get("admin").toString());
                    if (!isAdmin) {
                        String usernameParam = request.queryParams("username");
                        if (!usernameParam.equals(username)) {
                            halt(Information.UNAUTHORIZED, JSONUtils.toJson(new ErrorApi(Information.UNAUTHORIZED, "You aren't administrator, you can only consume your own data.")));
                        }
                    }
                }
            });

            after("/*", ((request, response) -> {
                response.type("application/json");
            }));

            path("/url", () -> {

                get("/", (request, response) -> {
                    String username = request.queryParamOrDefault("username", "");
                    User user = UserService.getInstance().find(username.trim());
                    if (user != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        List<URL> urls = user.urltoGet(user);
                        urls.forEach((URL url) -> {
                            URLServices.getInstance().getEntityManager().detach(url);
                            Statistics statistics = url.createStatistics();
                            url.setStatistics(statistics);
                            url.setHash(Information.DOMAIN + "/" + url.getHash());
                        });
                        String json = objectMapper.writeValueAsString(urls);
                        return json;
                    } else {
                        return JSONUtils.toJson(new ErrorApi(Information.NOT_FOUND,"Este usuario no existe!"));
                    }
                });

                // /create?username=xxxx&url=xxxx
                put("/create",  (request, response) -> {
                    String username = request.queryParamOrDefault("username", "");
                    String url = request.queryParamOrDefault("url", "");
                    String json = "";
                    User user = UserService.getInstance().find(username);
                    if (user != null) {
                        URL urlObj = new URL(url, user);
                        URLServices.getInstance().create(urlObj);
                        ObjectMapper objectMapper = new ObjectMapper();
                        URLServices.getInstance().getEntityManager().detach(urlObj);
                        Statistics statistics = urlObj.createStatistics();
                        urlObj.setStatistics(statistics);
                        urlObj.setHash(Information.DOMAIN + "/" + urlObj.getHash());
                        urlObj.setActualImage(ActualPage.getInstance().takeScreenAsBase64(urlObj.getUrl()));
                        json = objectMapper.writeValueAsString(urlObj);
                    } else {
                        json = JSONUtils.toJson(new ErrorApi(Information.NOT_FOUND,"Usuario no existe!"));
                    }
                    return json;
                });
            });
        });
    }
}
