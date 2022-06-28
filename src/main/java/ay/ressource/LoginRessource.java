package ay.ressource;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ay.bean.TokenBean;
import ay.bean.UserBean;

@Path("login")
public class LoginRessource {

    @Inject
    private UserBean userBean;
    @Inject
    private TokenBean tokenBean;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLogged(String test ) throws ParseException, SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(test);

        String value = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");


        if (value == null || password == null) {
            return Response.status(403).build();
        }

        if( userBean.getUserTokenByLogs(value, password) != null  ){
            return Response.ok(userBean.getUserTokenByLogs(value , password).getValue()).build();
        }else{
            return Response.status(403).entity("Wrong Pass or Username").build();
    }


}

@DELETE
@Produces(MediaType.TEXT_PLAIN)
@Path("/logout")
public Response logout(@HeaderParam("Authorization") String bearerAuth) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {

    if (bearerAuth == null)
        return Response.status(401).entity("No Bearer provided, :s").build();
    String token_val = bearerAuth.split(" ")[1];
    var verifToken = tokenBean.getTokenByValue(token_val);
    if (verifToken == null)
        return Response.status(401).entity("Bearer not recognized").build();

    entityManager.remove(verifToken);
    userTransaction.commit();

    // exo: supprimer un crewMember avec une boucle for
    return Response.ok("done").build();
}



}