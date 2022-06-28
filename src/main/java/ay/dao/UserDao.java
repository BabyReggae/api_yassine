package ay.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ay.bean.TokenBean;
import ay.entity.Token;
import ay.entity.User;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    public List<User> getUsers() {
        return entityManager.createQuery("select c from User c", User.class).getResultList();
    }

    public boolean addUser(User user) {
        try {
            userTransaction.begin();
            entityManager.persist(user);
            userTransaction.commit();

            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean editUser(Long id, User user) {
        try {

            userTransaction.begin();
            entityManager.merge(user);
            userTransaction.commit();

            return true;
        } catch (Exception e) {
            // e.printStackTrace();
            Logger.getGlobal().log(Level.SEVERE, "JPA error" + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(Long id) {

        try {
            userTransaction.begin();
            User UserEntity = entityManager.find(User.class, id);

            if (UserEntity == null)
                return false;

            entityManager.remove(UserEntity);
            userTransaction.commit();
            return true;
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "JPA Error" + e.getMessage());
            return false;
        }
    }

    public Token getUserTokenByLogs( String username, String password ) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        try{
        User user = entityManager
        .createQuery("select c from User c where c.firstName = '"+ username  +"' AND c.pwd = '" + password+"'", User.class)
        .getSingleResult();

        if (user != null) {
            Token token = user.getToken();

            if (token != null) {
                return token;
            } else {

                Token gotcreated = new Token();
                String uuid = UUID.randomUUID().toString();
                gotcreated.setValue(uuid);
                // Token persitedOne = TokenBean.addToken( );
                user.settoken(gotcreated);

                userTransaction.begin();
                entityManager.persist(user);
                userTransaction.commit();

                return gotcreated;
            }
        }

        return null;


        }catch( NoResultException noResultException)
            {
                Logger.getGlobal().info("password OR username are wrong");
                return null;
            }


    }

}
