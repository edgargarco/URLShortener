package root.Services;

import org.h2.tools.Server;
import root.URLShortener.User;

import java.sql.SQLException;

public class BoostrapService {
    private static BoostrapService instance;
    private BoostrapService(){}

    public static BoostrapService getInstance(){
        if (instance == null){
            instance = new BoostrapService();
        }
        return instance;
    }

    public void startDB(){
        try{
            Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-tcpDaemon").start();
        }catch (SQLException e){
            System.out.println("Problem Starting DB...\n"+ e.getMessage());

        }
    }

    public void stopDB(){
        try{
            Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
        }catch (SQLException e){
            System.out.println("Problem stopping DB...\n" + e.getMessage());
        }
    }

    public void defaultUser(){
        User user = new User("admin","admin","admin");
        User auxUser = UserService.getInstance().find(user.getUsername());
        if(auxUser == null){
            UserService.getInstance().create(user);
        }
    }


}
