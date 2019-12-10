package root.Services;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import root.URLShortener.TempURL;
import root.URLShortener.URL;
import root.URLShortener.User;
import root.URLShortener.Visit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

public class URLServices extends GenericCRUD<URL> {
    private static URLServices instance;
    public URLServices( ) { super(URL.class);}

    public static URLServices getInstance(){
        if (instance == null){
            instance = new URLServices();
        }
        return instance;
    }

    @Override
    public URL find(Object id) {
        return super.find(id);
    }

    @Override
    public void create(URL entity) {
        super.create(entity);
    }

    @Override
    public void update(URL entity) {
        super.update(entity);
    }

    @Override
    public void delete(Object entityID) {
        super.delete(entityID);
    }

    @Override
    public List<URL> findAll() {
        return super.findAll();
    }

    public List<URL> findAllUrlByHash(User user){
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM URL u WHERE u.user.username = :username");
        query.setParameter("username",user.getUsername());
        return query.getResultList();
    }



    public void deleteURL(String hash){
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("DELETE FROM URL  c WHERE c.hash = :hash ");
        query.setParameter("hash",hash);
        entityManager.close();
    }


    public boolean checkURLExistence(List<Object> urls, String url){
        boolean state = false;
        for (Object auxUrl: urls){
            if (auxUrl instanceof URL){
                if (((URL)auxUrl).getUrl().equals(url)){
                    state = true;
                    break;
                }
            }else {
                if (((TempURL)auxUrl).getUrl().equals(url)){
                    state = true;
                    break;
                }
            }
        }
        return state;
    }

    public boolean checkURL(String url){
        return (url.equals(""))?false:true;
    }

    public User deleteURLFromUsern(User user, URL url){
        for (int i = 0; i< user.getUrlList().size(); i++){
            if (user.getUrlList().get(i).getHash().equals(url.getHash())){
                user.getUrlList().remove(i);
                break;
            }
        }
        return user;
    }

    public URL findURLCustomMethod(String hash){
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("SELECT u FROM URL u WHERE hash = :hash");
        query.setParameter("hash",hash);
        List<URL> urlList = query.getResultList();

        Query query2 = entityManager.createQuery("SELECT v FROM Visit v WHERE v.url.hash = :hash");
        query2.setParameter("hash",hash);
        List<Visit> visits = query2.getResultList();
        if (urlList.size() != 0){
                urlList.get(0).setVisits(visits);
        }
        entityManager.close();
        return (urlList.size() == 0)?null:urlList.get(0);
    }










}
