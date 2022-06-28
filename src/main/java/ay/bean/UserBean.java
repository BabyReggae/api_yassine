package ay.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import ay.dao.UserDao;
import ay.entity.Token;
import ay.entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserBean {

    @Inject
    private UserDao userDao;

    // private List<User> users = userDao.getUsers();

    public List<User> getUsers() {
        var testLog =  userDao.getUsers();
        
        Logger.getGlobal().log(Level.SEVERE, "JPA error" + testLog.toString());

        return userDao.getUsers();
    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    public boolean editUser(Long id, User user) {
        user.setid(id);
        return userDao.editUser(id, user);
    }

    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    public Token getUserTokenByLogs( String username ,String password ) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        
        return userDao.getUserTokenByLogs(username, password);
    }
}