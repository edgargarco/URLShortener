package root.Services;

import root.URLShortener.URL;

import java.util.List;

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
}
