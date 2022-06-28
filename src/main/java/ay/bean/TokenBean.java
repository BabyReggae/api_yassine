package ay.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import ay.dao.TokenDao;
import ay.entity.Token;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TokenBean {

    @Inject
    private static TokenDao tokenDao;

    
    public Token getTokens(int id) {
        return tokenDao.getToken( id );
    }

    public static Token addToken(Token token) {
        return tokenDao.addToken(token);
    }

    public boolean editToken(Long id, Token token) {
        token.setid(id);
        return tokenDao.editToken(id , token);
    }

    public boolean deleteToken(Long id) {
        return tokenDao.deleteToken(id);
    }
}