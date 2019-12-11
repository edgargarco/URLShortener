package root.api;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import root.Services.UserService;
import root.URLShortener.User;
import root.controllers.Information;


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



        });
    }
}
