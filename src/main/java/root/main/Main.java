package root.main;

import root.Services.BoostrapService;
import root.api.InitSoap;
import root.api.RestApi;
import root.controllers.Authentication;
import root.controllers.Filter;
import root.controllers.Information;

import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) throws Exception {
        //Starting H2 database in server mode
        BoostrapService.getInstance().startDB();

        //Creating a default user if not exits
        BoostrapService.getInstance().defaultUser();

        //Public Resources
        staticFiles.location("/public");

        //Starting Filters
        Filter.filters();

        //Authentication Controllers
        Authentication.authentication();

        //Starting RestFul Services
        RestApi.restApis();

        //Application general controllers
        Information.informationControllers();

        //Starting Soap Server
        InitSoap.init();
    }
}
