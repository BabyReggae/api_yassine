package ay.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import ay.entity.Champion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChampionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public List<Champion> getChampions(){
        return entityManager.createQuery("select c from Champion c", Champion.class).getResultList();
    }

    public boolean addChampion(Champion champion){
            try{
            userTransaction.begin();
            entityManager.persist(champion);
            userTransaction.commit();

            return true;
        }catch (Exception e){
            // e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean editChampion(Long id , Champion champion) {
        try {
        
            userTransaction.begin();
            entityManager.merge(champion);
            userTransaction.commit();

            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean deleteChampion(Long id ){

        try {
            userTransaction.begin();
            Champion championEntity = entityManager.find(Champion.class, id );

            if(championEntity == null)
                return false;

            entityManager.remove(championEntity);
            userTransaction.commit();
            return true;
        }catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "JPA Error" + e.getMessage());
            return false;
        }
    }
}
