package fr.univamu.iut.plats;

import fr.univamu.iut.SqlRequests;
import jakarta.ws.rs.*;

import java.sql.SQLException;

@Path("/plats")
public class Delete {
    @DELETE
    @Path("/{id}")
    public boolean deletePlatWithId(@PathParam("id")String id){
        try{
            SqlRequests.executeQuery("DELETE FROM Plats WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}
