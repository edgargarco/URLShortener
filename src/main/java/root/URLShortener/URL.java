package root.URLShortener;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import root.Services.URLServices;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class URL implements Serializable {
    @Id
    private String hash;
    @Type(type = "text")
    private String url;
    @ManyToOne
    private User user;
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "url")

    private List<Visit> visits;

    public URL(){

    }
    public URL(String url){
        this.url = url;
        this.setHash(URLServices.getInstance().urlHash(url));
    }

    public URL(String url, User user) {
        this.url = url;
        this.user = user;
        this.setHash(URLServices.getInstance().urlHash(url));
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

}
