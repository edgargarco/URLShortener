package root.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import root.URLShortener.URL;
import root.controllers.Information;

import java.util.List;

import static spark.Spark.*;
import static spark.Spark.halt;

public class RestApi {
    private static final int expiredJWT = 1000;

    public static void restApis() {
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
                        claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(Information.KEY.getBytes())).parseClaimsJws(jwt).getBody();
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
                    String json = "";
                    List<URL> urls = Services.getInstance().getUrls(username);
                    if (urls != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        json = objectMapper.writeValueAsString(urls);
                    } else {
                        json = JSONUtils.toJson(new ErrorApi(Information.NOT_FOUND,"This user doesn't exists!"));
                    }
                    return json;
                });

                put("/create",  (request, response) -> {
                    String username = request.queryParamOrDefault("username", "");
                    String url = request.queryParamOrDefault("url", "");
                    String json = "";
                    URL urlObj = Services.getInstance().createURL(username, url);
                    if (urlObj != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        json = objectMapper.writeValueAsString(urlObj);
                    } else {
                        json = JSONUtils.toJson(new ErrorApi(Information.NOT_FOUND,"This user doesn't exists!"));
                    }
                    return json;
                });
            });
        });
    }
}
