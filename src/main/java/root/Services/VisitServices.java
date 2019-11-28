package root.Services;

import root.URLShortener.Visit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
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


    public void deleteVisits(String hash){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
       try{
           entityManager.flush();
           entityManager.clear();
           Query query = entityManager.createQuery("DELETE FROM Visit  c WHERE c.url.hash = :hash ");
           query.setParameter("hash",hash);
           query.executeUpdate();
           entityManager.getTransaction().commit();
       }catch (Exception e){
           entityManager.getTransaction().rollback();
           throw e;
       }finally {
           entityManager.close();
       }

    }

    public List<Visit> visitsByDates(String hash, LocalDate date){
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM Visit c WHERE c.url.hash = :hash AND c.date = :date ORDER BY c.date ASC" );
        query.setParameter("hash",hash);
        query.setParameter("date",date);
        List<Visit> list = query.getResultList();
        entityManager.close();
        return list;
    }

    public boolean sanitizeDate(String date){
        try {
            Integer.parseInt(date.substring(6,10));
            Integer.parseInt(date.substring(0,2));
            Integer.parseInt(date.substring(3,5));
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
