package ay.ressource;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ay.bean.UserBean;

@Path("login")
public class LoginRessource {

    @Inject
    private UserBean userBean;


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

}}