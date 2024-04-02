package fr.univamu.iut.users;

import fr.univamu.iut.SqlRequests;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import java.sql.SQLException;

@Path("/utilisateurs")
public class Put {
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public boolean putPlat(@FormParam("nameToReplace")String name, @FormParam("id")String id){
        try{
            SqlRequests.executeQuery("UPDATE Utilisateurs SET nom='"+name+"' WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}
