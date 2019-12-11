package root.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import root.Services.URLServices;
import root.Services.UserService;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.controllers.Information;

import javax.persistence.EntityManager;
import java.util.List;

import static spark.Spark.*;
import static spark.Spark.halt;

public class RestApi {
    public void restApis() {
        path("/rest", () -> {

            before("/*", (request, response) ->{
                //Header for the token
                String header = "Authorization";

                //Verifying if the header exists
                String jwt = request.headers(header);
                if(jwt == null){
                    halt(Information.FORBIDDEN, "Bad request! You can't access!");
                } else {
                    Claims claims = null;
                    try {
                        claims = Jwts.parser()
                                .setSigningKey(Keys.hmacShaKeyFor(Information.KEY.getBytes()))
                                .parseClaimsJws(jwt).getBody();
                    }catch (ExpiredJwtException | MalformedJwtException | SignatureException e){ //Excepciones comunes
                        halt(Information.FORBIDDEN, e.getMessage());
                    }
                    System.out.println("JWT received: " + claims.toString());
                    String username = claims.get("username").toString();
                    Boolean isAdmin = Boolean.parseBoolean(claims.get("admin").toString());
                }
            });

            after("/*", ((request, response) -> {
                response.type("application/json");
            }));

            path("/url", () -> {

                get("/:username", (request, response) -> {
                    String username = request.params("username");
                    User user = UserService.getInstance().find(username);
                    if (user != null) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        List<URL> urls = user.urltoGet(user);
                        String json = objectMapper.writeValueAsString(urls);
                        System.out.println(json);
                        return json;
                    } else {
                        return "Este usuario no existe!";
                    }
                });
            });
        });
    }
}
