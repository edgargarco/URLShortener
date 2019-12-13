package root.URLShortener;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Browser implements Serializable {
    private int cant;
    private String browser;
    private Integer id;

    public Browser(){}

    public Browser(int cant, String browser) {
        this.cant = cant;
        this.browser = browser;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
