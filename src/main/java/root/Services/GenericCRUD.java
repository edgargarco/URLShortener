package root.Services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import root.URLShortener.URL;
import root.URLShortener.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

public class GenericCRUD<T> {
    private static EntityManagerFactory entityManagerFactory;
    private Class<T> entityClass;

    public GenericCRUD(Class<T> entityClass){
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("URL-Shortener");
        }
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager(){return entityManagerFactory.createEntityManager();}

    public T find(Object id) {
        EntityManager em = getEntityManager();
        try{
            return em.find(entityClass, id);
        } catch (Exception ex){
            throw  ex;
        } finally {
            em.close();
        }
    }

    private Object getCampValue(T entity){
        if (entity == null){
            return null;
        }
        for(Field field : entity.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Id.class)){
                try{
                    field.setAccessible(true);
                    Object campValue = field.get(entity);
                    return campValue;
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    public void create(T entity){
        EntityManager entityManager = getEntityManager();
        try {
            if (entityManager.find(entityClass, getCampValue(entity)) != null) {
                System.out.println("La entidad a guardar existe, no creada.");
                return;
            }
        }catch (IllegalArgumentException ie){
            System.out.println("Parametro ilegal.");
        }
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw  e;
        }finally {
            entityManager.close();
        }

    }

    public void update(T entity){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }

    public void delete(Object entityID){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            T entity = em.find(entityClass, entityID);
            em.remove(entity);
            em.getTransaction().commit();

        }catch (Exception ex){
            em.getTransaction().rollback();

            throw  ex;
        } finally {
            em.close();
        }
    }


    public List<T> findAll(){
        EntityManager entityManager = getEntityManager();
        try{
            CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
            criteriaQuery.select(criteriaQuery.from(entityClass));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }catch (Exception e){
            throw e;
        }finally {
            entityManager.close();
        }
    }







}
