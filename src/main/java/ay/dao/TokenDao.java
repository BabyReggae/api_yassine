package ay.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import ay.entity.Token;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public Token getToken(int id){
        return entityManager.createQuery("select c from Token c where c.id = " + id, Token.class).getSingleResult();
    }

    public Token addToken(Token token){
            try{
            userTransaction.begin();
            entityManager.persist(token);
            userTransaction.commit();

            return token;
        }catch (Exception e){
            // e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return null;
        }
    }

    public boolean editToken(Long id , Token token) {
        try {
        
            userTransaction.begin();
            entityManager.merge(token);
            userTransaction.commit();

            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean deleteToken(Long id ){

        try {
            userTransaction.begin();
            Token tokenEntity = entityManager.find(Token.class, id );

            if(tokenEntity == null)
                return false;

            entityManager.remove(tokenEntity);
            userTransaction.commit();
            return true;
        }catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "JPA Error" + e.getMessage());
            return false;
        }
    }
}
