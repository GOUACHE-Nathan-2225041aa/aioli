package fr.univamu.iut.users;

import fr.univamu.iut.SqlRequests;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.sql.SQLException;

@Path("/utilisateurs")
public class Delete {
    @DELETE
    @Path("/{id}")
    public boolean deletePlatWithId(@PathParam("id")String id){
        try{
            SqlRequests.executeQuery("DELETE FROM Utilisateurs WHERE id='"+id+"'");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}
