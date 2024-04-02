package fr.univamu.iut.plats;

import jakarta.ws.rs.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.univamu.iut.SqlRequests;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/plat")
public class GetPlats {

    @GET
    @Produces("text/plain")
    @Path("/getIdByName/{name}")
    public String getIdByName(@PathParam("name") String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlRequests.executeQuery("SELECT id FROM Plats WHERE nom='" + name + "'");
        JSONArray result = new JSONArray();

        try {
            while (resultSet.next()) {
                JSONObject row = new JSONObject();
                // Récupérer la valeur de la colonne "id"
                int id = resultSet.getInt("id");
                // Stocker la valeur dans le JSONObject
                row.put("Id", id);
                // Ajouter l'objet à la JSONArray
                result.put(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}