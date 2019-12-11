package root.URLShortener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import root.Services.URLServices;

import javax.persistence.*;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    private String username;
    private String name;
    private String password;
    private boolean administrator;
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<URL> urlList = new ArrayList<>();


    public User(){

    }

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.administrator = false;
    }
    public User(String username, String name, String password,boolean admin) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.administrator = admin;
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

    public List<URL> urltoGet(User user){
        //This method was implemented to recover url using a lazy load
        return URLServices.getInstance().findAllUrlByHash(user);
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
