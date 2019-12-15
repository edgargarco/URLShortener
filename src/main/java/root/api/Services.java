package root.api;

import root.Services.URLServices;
import root.Services.UserService;
import root.URLShortener.Statistics;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.controllers.Information;

import java.util.List;

public class Services {
    private static Services services = null;

    private Services() {}

    public static Services getInstance() {
        if (services == null) {
            services = new Services();
        }
        return services;
    }

    public List<URL> getUrls(String username){
        User user = UserService.getInstance().find(username.trim());
        List<URL> urls = null;
        if (user != null) {
            urls = user.urltoGet(user);
            urls.forEach((URL url) -> {
                URLServices.getInstance().getEntityManager().detach(url);
                Statistics statistics = url.createStatistics();
                url.setStatistics(statistics);
                url.setHash(Information.DOMAIN + "/" + url.getHash());
            });
        }
        return urls;
    }

    public URL createURL(String username, String url) {
        URL urlObj = null;
        User user = UserService.getInstance().find(username);
        if (user != null) {
            urlObj = new URL(url, user);
            URLServices.getInstance().create(urlObj);
            URLServices.getInstance().getEntityManager().detach(urlObj);
            Statistics statistics = urlObj.createStatistics();
            urlObj.setStatistics(statistics);
            urlObj.setHash(Information.DOMAIN + "/" + urlObj.getHash());
            urlObj.setActualImage(ActualPage.getInstance().takeScreenAsBase64(urlObj.getUrl()));
        }
        return urlObj;
    }
}
