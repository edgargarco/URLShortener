package root.api;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Base64;

public class ActualPage {
    private final String apiURL = "https://api.apiflash.com/v1/urltoimage";
    private final String accesKey = "f0234af8291249d687600f199eaef3f7";
    private final String responseType = "image";
    private static ActualPage actualPage = null;

    private ActualPage() { }

    public static ActualPage getInstance() {
        if (actualPage == null) {
            actualPage = new ActualPage();
        }
        return actualPage;
    }

    private byte[] takeScreenAsBytes(String url) {
        HttpResponse<byte[]> response = Unirest.get(apiURL)
                .queryString("access_key", accesKey)
                .queryString("response_type", responseType)
                .queryString("url", url).asBytes();
        return response.getBody();
    }

    public String takeScreenAsBase64(String url) {
        String result = "";
        result = Base64.getEncoder().encodeToString(takeScreenAsBytes(url));
        return result;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getAccesKey() {
        return accesKey;
    }

    public String getResponseType() {
        return responseType;
    }
}
