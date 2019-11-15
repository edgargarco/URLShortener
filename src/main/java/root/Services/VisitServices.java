package root.Services;

import root.URLShortener.Visit;

import java.util.List;

public class VisitServices extends GenericCRUD<Visit> {
    public static VisitServices instance;
    public VisitServices() {super(Visit.class); }

    public static VisitServices getInstance(){
        if (instance == null){
            instance = new VisitServices();
        }
        return instance;
    }

    @Override
    public Visit find(Object id) {
        return super.find(id);
    }

    @Override
    public void create(Visit entity) {
        super.create(entity);
    }

    @Override
    public void update(Visit entity) {
        super.update(entity);
    }

    @Override
    public void delete(Object entityID) {
        super.delete(entityID);
    }

    @Override
    public List<Visit> findAll() {
        return super.findAll();
    }
}
