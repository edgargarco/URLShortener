package root.Services;

import org.hibernate.Session;
import root.URLShortener.URL;
import root.URLShortener.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserService extends GenericCRUD<User> {
    private static UserService instance;
    private UserService(){ super(User.class); }

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }





}
