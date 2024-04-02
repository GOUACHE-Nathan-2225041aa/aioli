package fr.univamu.iut.users;

import fr.univamu.iut.SqlRequests;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.sql.SQLException;

@Path("/utilisateurs")
public class Post {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean createPlat(@FormParam("name") String name){

        try{
            SqlRequests.executeQuery("INSERT INTO Utilisateurs (nom) VALUES ('"+name+"')");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}