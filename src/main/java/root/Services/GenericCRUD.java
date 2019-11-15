package root.Services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

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

    public T find(Object id){
        EntityManager entityManager = getEntityManager();
        try{
            return entityManager.find(entityClass,id);
        }catch (Exception e){
            throw e;
        }finally {
            entityManager.close();
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


}
