package root.Services;

import root.URLShortener.User;



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
