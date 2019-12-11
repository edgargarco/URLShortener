package root.main;

import root.Services.BoostrapService;
import root.Services.URLServices;
import root.api.RestApi;
import root.controllers.Authentication;
import root.controllers.Filter;
import root.controllers.Information;

import static spark.Spark.staticFiles;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        BoostrapService.getInstance().startDB();
        BoostrapService.getInstance().defaultUser();
        //Public Resources
        staticFiles.location("/public");
        (new Filter()).filters();
        (new Authentication()).authentication();
        (new RestApi()).restApis();
        (new Information()).informationControllers();
    }
}
