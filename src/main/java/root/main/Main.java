package root.main;

import root.Services.BoostrapService;

public class Main {
    public static void main(String[] args) {
        BoostrapService.getInstance().startDB();
        BoostrapService.getInstance().defaultUser();
    }
}
