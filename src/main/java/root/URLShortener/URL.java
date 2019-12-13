package root.URLShortener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import root.Services.VisitServices;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class URL implements Serializable {
    @Id
    private String hash;
    @Type(type = "text")
    private String url;
    @ManyToOne
    @JsonIgnore
    @XmlTransient
    private User user;
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "url",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    @XmlTransient
    private List<Visit> visits = new ArrayList<>();
    @Transient
    private Statistics statistics;
    @Transient
    private String actualImage;

    public URL(){

    }

    public URL(String url,String diferentiator){
        this.url = url;
        this.hash = urlHash(this.url,diferentiator);
        this.creationDate = LocalDateTime.now();
    }

    public URL(String url, User user) {
        this.url = url;
        this.user = user;
        this.hash = urlHash(url, user.getUsername());
        this.creationDate = LocalDateTime.now();
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
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

    public List<Visit> getVisits() {return visits;}

    public void setVisits(List<Visit> visits) {this.visits = visits;}

    public void addVisits(Visit visit){
        visits.add(visit);}

    public String urlHash(String url,String diferentiator){
        String toHash = url+diferentiator;
        CRC32 crc32 = new CRC32();
        crc32.update(toHash.getBytes());
        return (Long.toHexString(crc32.getValue()));
    }

    public Statistics createStatistics(){
        return (new Statistics(VisitServices.getInstance().getByOs(hash,"Linux"),VisitServices.getInstance().getByOs(hash,"Windows"),VisitServices.getInstance().getByOs(hash,"iOS"),VisitServices.getInstance().getByOs(hash,"Android"),VisitServices.getInstance().getIPS(hash)));
    }

    public String getActualImage() {
        return actualImage;
    }

    public void setActualImage(String actualImage) {
        this.actualImage = actualImage;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }
}
