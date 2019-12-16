package root.api;

import org.apache.commons.validator.routines.UrlValidator;
import root.URLShortener.URL;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class ShortenerWS {
    @WebMethod
    public String helloWorld() { return "Hello World!"; }

    @WebMethod
    public List<URL> getUrls(String username) {
        return Services.getInstance().getUrls(username);
    }

    @WebMethod
    public URL createUrl(String username, String url) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url)) {
            return Services.getInstance().createURL(username, url);
        } else {
            return null;
        }
    }
}
