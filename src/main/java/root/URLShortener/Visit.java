package root.URLShortener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
    private LocalDate date;
    private LocalTime time;
    private String device;

    public  Visit(){

    }
    public Visit(URL url, String browser, String ip, String os, String device) throws ParseException {
        this.url = url;
        this.date = date();
        this.time = time();
        this.browser = browser;
        this.ip = ip;
        this.Os = os;
        this.device = device;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
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

    public LocalTime time( ) throws ParseException {
        System.out.println(LocalTime.now());
        return LocalTime.now();
    }
    public LocalDate date() throws ParseException {
        return LocalDate.now();
    }


}
