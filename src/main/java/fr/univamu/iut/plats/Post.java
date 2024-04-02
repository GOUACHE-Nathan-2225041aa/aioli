package fr.univamu.iut.plats;

import jakarta.ws.rs.*;
import java.sql.*;
import fr.univamu.iut.SqlRequests;

@Path("/plats")
public class Post {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public boolean createPlat(@FormParam("name") String name){

        try{
            SqlRequests.executeQuery("INSERT INTO Plats (nom) VALUES ('"+name+"')");
        }
        catch(SQLException | ClassNotFoundException e){
            return false;
        }
        return true;
    }
}