package root.Services;

import root.URLShortener.TempURL;


import java.util.List;


public class TempURLServices extends GenericCRUD<TempURL>  {
    private static TempURLServices instance;
    public TempURLServices( ) { super(TempURL.class);}

    public static TempURLServices getInstance(){
        if (instance == null){
            instance = new TempURLServices();
        }
        return instance;
    }

    public boolean checkURLExistence(List<TempURL> urls, String url){
        boolean state = false;
        for (TempURL auxUrl: urls){
            if (auxUrl.getUrl().equals(url)){
                state = true;
                break;
            }
        }
        return state;
    }

    public boolean checkURL(String url){
        return (url.equals(""))?false:true;
    }
}
