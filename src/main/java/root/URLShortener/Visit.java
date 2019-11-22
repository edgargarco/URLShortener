package root.URLShortener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date date;
    private String device;

    public  Visit(){

    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Visit(URL url, String browser, String ip, String os,Date date,String device) {
        this.url = url;
        this.date = date;
        this.browser = browser;
        this.ip = ip;
        this.Os = os;
        this.device = device;

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
