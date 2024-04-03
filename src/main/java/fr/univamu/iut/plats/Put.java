package fr.univamu.iut.plats;

import fr.univamu.iut.SqlRequests;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import java.sql.SQLException;

@Path("/plats")
public class Put {
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public boolean putPlat(@FormParam("nom") String nom, @FormParam("prix") String prix, @FormParam("description") String description, @FormParam("id")String id){
        try{
            SqlRequests.executeQuery("UPDATE Plats SET nom='"+nom+"', prix='"+prix+"',description='"+description+"', WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}
