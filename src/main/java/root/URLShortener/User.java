package root.URLShortener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    private String username;
    private String name;
    private String password;
    private boolean administrator;
    @OneToMany(fetch=FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL)
    private List<URL> urlList = new ArrayList<>();


    public User(){

    }

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public List<URL> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<URL> urlList) {
        this.urlList = urlList;
    }



    public void addURL(URL url){
        urlList.add(url);
    }

    public URL searchURL(User user,String hash){
        URL urlAux = new URL();
        for(URL url : user.getUrlList()){
            if (url.getHash().equals(hash)){
                urlAux = url;
                break;
            }
        }
        return urlAux;
    }

}
