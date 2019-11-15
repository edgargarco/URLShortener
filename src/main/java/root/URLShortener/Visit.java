package root.URLShortener;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private URL url;
    private String browser;
    private String ip;
    private String Os;

    public Visit(URL url, String browser, String ip, String os) {
        this.url = url;

        this.browser = browser;
        this.ip = ip;
        Os = os;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return Os;
    }

    public void setOs(String os) {
        Os = os;
    }
}
